package org.home.jgmp.reaclive.config;

import org.home.jgmp.reaclive.domain.books.BookGen;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {


    @Bean
    public Mono<BookGen> fetchBooks() {

        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        var client = WebClient.builder()
                .exchangeStrategies(strategies)
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().followRedirect(true)
                ))
                .baseUrl("http://fakerapi.it/api/v1/books?_quantity=5000")
                .build();

        return client
                .get()
//                .uri("/sports")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BookGen.class);


    }
}
