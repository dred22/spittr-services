package spittr.dbproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("spittr.data")
@EntityScan("spittr.domain.model")
public class DbProxyApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DbProxyApp.class, args);
    }
}
