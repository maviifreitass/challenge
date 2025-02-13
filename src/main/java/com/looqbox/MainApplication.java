package com.looqbox;

import com.looqbox.cache.PokemonCache;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
