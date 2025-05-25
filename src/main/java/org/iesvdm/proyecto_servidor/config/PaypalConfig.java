package org.iesvdm.proyecto_servidor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "paypal")
public class PaypalConfig {

    private String baseUrl;
    private String clientId;
    private String clientSecret;

    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

}
