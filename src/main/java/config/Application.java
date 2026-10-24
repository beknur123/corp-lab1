package config;

import domain.Rule;
import domain.TransitionRule;
import domain.StopFactorRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"config", "persistence"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Rule transitionRule() {
        return new TransitionRule();
    }

    @Bean
    public Rule stopFactorRule() {
        return new StopFactorRule();
    }
}