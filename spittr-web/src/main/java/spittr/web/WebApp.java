package spittr.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
/*@EnableJpaRepositories("spittr.data")
@EntityScan("spittr.domain.model")*/
public class WebApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }
}
