package com.delta.rental.deltarental.core.configurations;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfiguration {
    private final String CLOUD_NAME = "dxav6uhnu";
    private final String API_KEY = "142596394598749";
    private final String API_SECRET = "VnJPV_h8phX1WikmCyVDJ431dQo";

    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name",CLOUD_NAME);
        config.put("api_key",API_KEY);
        config.put("api_secret",API_SECRET);

        return new Cloudinary(config);
    }
}
