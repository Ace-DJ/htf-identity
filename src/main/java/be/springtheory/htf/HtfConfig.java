package be.springtheory.htf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HtfConfig {

    @Bean
    public RestTemplate webClient() {
        return new RestTemplate();
    }

    @Bean
    public String teamId() {
        return "739ca66d-984a-4712-b1a9-0f8b535da7c8";
    }
}
