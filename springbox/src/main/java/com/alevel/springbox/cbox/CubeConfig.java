package com.alevel.springbox.cbox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CubeConfig {

    @Bean
    public Incinerator incinerator() {
        return new FirePit();
    }

}
