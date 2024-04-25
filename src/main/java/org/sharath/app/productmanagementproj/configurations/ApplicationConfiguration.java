package org.sharath.app.productmanagementproj.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


//We're using this configuration to create a bean of type RestTemplate as of now
@Configuration
public class ApplicationConfiguration {

//    This creates a bean of type RestTemplate to use in ServiceImpl class
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
