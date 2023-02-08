package meRybaczek.orderApp;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderAppConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // CR: po co to tu jest? w aplikacji raczej nie jest to potrzebne
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
