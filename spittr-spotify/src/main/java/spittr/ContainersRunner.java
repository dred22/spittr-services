package spittr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spittr.controller.impl.SpotifyDockerControllerImpl;

@Slf4j
@SpringBootApplication
public class ContainersRunner implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ContainersRunner.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("Initiating..........");
        SpotifyDockerControllerImpl spotifyDockerController = new SpotifyDockerControllerImpl();
        long dockerId = spotifyDockerController.launchAndWaitDocker();
        log.info("Container [{}] stop", dockerId);

    }
}
