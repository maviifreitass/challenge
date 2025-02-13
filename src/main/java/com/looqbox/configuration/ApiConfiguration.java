package com.looqbox.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApiConfiguration {

  @Value("${pokemon.url}")
  private String pokemonApiUrl;

  @Bean
  public WebClient webClient(final WebClient.Builder builder) {      
    return builder.baseUrl(pokemonApiUrl != null ? pokemonApiUrl : "https://pokeapi.co/api/v2/pokemon?limit=11000")  
        .build();
  }
  
}