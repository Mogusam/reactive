package org.home.jgmp.reaclive;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.home.jgmp.reaclive.domain.books.Book;
import org.home.jgmp.reaclive.domain.books.BookGen;
import org.home.jgmp.reaclive.domain.decatlon.Datum;
import org.home.jgmp.reaclive.domain.decatlon.Sport;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

//@Log4j2
public class WebclientTest {


    @Test
    public void getSportTest() throws InterruptedException {

        WebClient client = WebClient.create();
        //"http://127.0.0.1:8080/hello");//"https://sports.api.decathlon.com/sports");
        Sport employeeFlux = client
                .get()
                .uri("http://localhost:8080/1")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Sport.class)
                .block();
        //subscribe(s->System.out.println("s="+ s));
//                block(Duration.of(20, ChronoUnit.SECONDS));
//                .bodyToFlux(Object.class);
//        employeeFlux.subscribe(s->s.toString());

        System.out.println(employeeFlux);

    }

    @Test
    public void getSportTest2() throws InterruptedException {

        WebClient client = WebClient.create("http://localhost:8080");
        //"http://127.0.0.1:8080/hello");//"https://sports.api.decathlon.com/sports");
        Flux<Sport> result = client
                .get()
                .uri("/hello")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Sport.class);
        result.subscribe(System.out::println, Throwable::printStackTrace);
//        Thread.sleep(4000);
    }

    @Test
    public void getSportTest_dek() throws InterruptedException {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        WebClient client = WebClient.builder()
                .exchangeStrategies(strategies)
                .baseUrl("http://sports.api.decathlon.com")
                .build();
        //"http://127.0.0.1:8080/hello");//"https://sports.api.decathlon.com/sports/sports");
        Mono<Datum> result = client
                .get()
                .uri("/sports")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Datum.class);

        Datum res = result.share().block();

        System.out.println(res.getDataList().size());
//            result.subscribe( System.out::println, Throwable::printStackTrace);
//            Thread.sleep(4000);
    }

    @Test
    public void getBookTest_book() throws InterruptedException {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        WebClient client = WebClient.builder()
                .exchangeStrategies(strategies)
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().followRedirect(true)
                ))

                .baseUrl("http://fakerapi.it/api/v1/books?_quantity=5000")
                .build();
        //"http://127.0.0.1:8080/hello");//"https://sports.api.decathlon.com/sports/sports");
        Mono<BookGen> result = client
                .get()
//                .uri("/sports")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BookGen.class);
        result.share().subscribe(s -> s.getData().forEach(System.out::println));

//        BookGen res = result.blockFirst();

//        System.out.println(res.getData().get(0).getAuthor());
//            result.subscribe( System.out::println, Throwable::printStackTrace);
        Thread.sleep(4000);
    }

    @Test
    public void blockLast() {
        WebClient client = WebClient.create("http://localhost:8080");
        Sport result = client
                .get()
                .uri("/hello")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Sport.class)
                .blockFirst();

        System.out.println(result);
    }

    @Test
    public void blockLast_3() {
        WebClient client = WebClient.create("http://localhost:8080");
        Sport result = client
                .get()
                .uri("/hello")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Sport.class)
                .elementAt(1)
                .block();

        System.out.println(result);
    }

    @Test
    public void test_1s() {

        List<Book> books;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File src = new ClassPathResource("books.json").getFile();
            books = objectMapper.readValue(src, new TypeReference<List<Book>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        books.forEach(System.out::println);
    }


}
