package spittr.controller.impl;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.HostConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

@Slf4j
public class SpotifyDockerControllerImpl {

    private static final String			DEFAULT_HOST_NAME	= "127.0.0.1";
    private DockerClient dockerClient;
    private ContainerConfig containerConfig;
    private final Map<String, String> timezone			= apTimezone();
    @Getter
    private String containerId = null;

    public SpotifyDockerControllerImpl() {
        // Create a client based on DOCKER_HOST and DOCKER_CERT_PATH env vars
        try {
            dockerClient = DefaultDockerClient.fromEnv().build();
        } catch (DockerCertificateException e) {
            log.error("Disable to create the docker client", e);
            System.exit(1);
        }

    }

    public void createDockerConfiguration(Map<String, String> volumeMapping, Map<String, String> envFromProperties) {

        //HostConfig
        List<HostConfig.Bind> hostConfigBindList = new ArrayList<>();
        volumeMapping.keySet().forEach(k -> hostConfigBindList.add(HostConfig.Bind.builder().from(k).to(volumeMapping.get(k)).build()));
        HostConfig hostConfig = HostConfig.builder().binds(hostConfigBindList.toArray(new HostConfig.Bind[0])).build();

        log.debug("HostConfig used : {}", hostConfig);
        containerConfig = ContainerConfig.builder()
                .image("spittr/db-proxy")
                .hostname(DEFAULT_HOST_NAME)
                .hostConfig(hostConfig)
                .env(mergeMapsToPropsArray(envFromProperties, timezone))
                .build();
    }

    private String[] mergeMapsToPropsArray(Map<String, String>... maps) {
        return Stream.of(maps)
                .flatMap(map -> map.entrySet().stream())
                .map(s -> s.getKey() + "=" + s.getValue())
                .toArray(String[]::new);
    }

    public long launchAndWaitDocker() {
        try {
            containerId = dockerClient.createContainer(containerConfig).id();
            log.info("---------- Launching application {} ----------", "spittr/db-proxy");
            attachToContainerLogs(containerId);
            dockerClient.startContainer(containerId);
            return dockerClient.waitContainer(containerId).statusCode();
        } catch (Exception e) {
            log.error("Failed to launch and wait docker container {} : {}", "spittr/db-proxy", e);
        } finally {
            removeContainer(containerId);
            log.info("---------- Application {} finished. ----------", "spittr/db-proxy");
        }
        return -1;
    }


    private void removeContainer(String containerId) {
        if (containerId != null) {
            try {
                dockerClient.removeContainer(containerId);
            } catch (DockerException | InterruptedException e) {
                log.error("Could not remove container {} : {}", containerId, e);
                // Restore interrupted state...
                Thread.currentThread().interrupt();
            }
        }
    }

    private void attachToContainerLogs(String containerId) throws IOException {
        try (OutputStream loggerStream = new LoggerStream("spittr/db-proxy")) {
            newSingleThreadExecutor().execute(() -> {
                try {
                    dockerClient.logs(containerId, DockerClient.LogsParam.follow(), DockerClient.LogsParam.stdout(), DockerClient.LogsParam.stderr()).attach(loggerStream, loggerStream);
                } catch (Exception e) {
                    log.warn("Failed to attach to container logs", e);
                }
            });
        }
    }

    private static final class LoggerStream extends OutputStream {

        private final ByteArrayOutputStream logLineStream	= new ByteArrayOutputStream(1000);
        private final String				applicationID;

        private LoggerStream(String applicationID) {
            this.applicationID = applicationID;
        }

        @Override
        public void write(int charToLog) {
            if (lineIsComplete(charToLog)) {
                logCompletedLine();
            } else {
                addCharToLogLine(charToLog);
            }
        }

        public void write(byte[] b, int off, int len) {
            for (int i = 0; i < len; i++) {
                write(b[off + i]);
            }
        }

        private void addCharToLogLine(int charToLog) {
            logLineStream.write(charToLog);
        }

        private void logCompletedLine() {
            log.info("{} - {}", applicationID, logLineStream);
            logLineStream.reset();
        }

        private boolean lineIsComplete(int charToLog) {
            return (System.lineSeparator().equals(String.valueOf((char) charToLog)));
        }
    }

    private Map<String, String> apTimezone() {
        String tz = System.getenv("TZ");
        return null == tz ? Collections.emptyMap() : Collections.singletonMap("TZ", tz);
    }
}

