package org.home.jgmp.reaclive.service;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.home.jgmp.reaclive.domain.books.Book;
import org.home.jgmp.reaclive.domain.books.BookGen;
import org.home.jgmp.reaclive.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Service
@Log4j2
public class BookService {
    @Autowired
    private BookRepository repository;


    public Mono<ServerResponse> save5FromFile(ServerRequest request) {

        List<Book> books = readFromFile();
        Flux<Book> res = repository.saveAll(books);

        res.subscribe(System.out::println);
        return ok().bodyValue("hello");
    }


    public Mono<ServerResponse> saveAll(ServerRequest request) {
        return saveBooks2Table(readBooks())
                .then(ok().build())
                .onErrorResume(error -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> saveAllBatch(ServerRequest request) {
        return repository.deleteAll().then(saveBooks2TableBatch(readBooks())
                .then(ok().build())
                .onErrorResume(error -> ServerResponse.badRequest().build()));
    }

    private List<Book> readFromFile() {
        List<Book> books;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        try {
            File src = new ClassPathResource("books.json").getFile();
            books = objectMapper.readValue(src, new TypeReference<List<Book>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        books.forEach(System.out::println);
        return books;
    }

    public Mono<ServerResponse> findByTitleQueryParams(ServerRequest serverRequest) {
        final Optional<String> title = serverRequest.queryParam("title");
        Flux<Book> result = repository.findByTitle(title.orElse(""));
        return ok().contentType(MediaType.APPLICATION_JSON).body(result, Book.class);
    }

    public Mono<ServerResponse> findByTitlePathParams(ServerRequest serverRequest) {
        String title = serverRequest.pathVariable("title");
        Flux<Book> result = repository.findByTitle(title);

        return ok().contentType(MediaType.APPLICATION_JSON).body(result, Book.class);
    }

    public Mono<BookGen> readBooks() {
        final int size = 8 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        WebClient client = WebClient.builder()
                .exchangeStrategies(strategies)
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().followRedirect(true)
                ))

                .baseUrl("http://fakerapi.it/api/v1/books?_quantity=800")
                .build();
        Mono<BookGen> result = client
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BookGen.class);
        return result;

    }

    public Mono<Void> saveBooks2Table(Mono<BookGen> bookGenMono) {
        Mono<List<Book>> res = bookGenMono
                .map(BookGen::getData);
        Flux<Book> monoBook = res.flatMapMany(Flux::fromIterable);
        System.out.println("=-=-=-= Books=-=-=-==-");
        monoBook.subscribe(System.out::println);
        return repository.deleteAll()
                .and(repository.saveAll(monoBook));
    }

    public Mono<Void> saveBooks2TableBatch(Mono<BookGen> bookGenMono) {
        Mono<List<Book>> res = bookGenMono
                .map(BookGen::getData);
        Flux<Book> fluxBooks = res.flatMapMany(Flux::fromIterable);

        AtomicInteger i = new AtomicInteger(1);
        return fluxBooks
                .buffer(20)
                .flatMap(books -> {
                    log.info("save batch #{} with  {}  books", i, books.size());
                    log.info("first book in chunk ===> {}", books.get(0).toString().substring(0, 50));
                    i.getAndIncrement();
                    return repository.saveAll(books);
                }).then();
    }

    public Mono<ServerResponse> createBook(ServerRequest serverRequest) {
        Mono<Book> bookMono = serverRequest.bodyToMono(Book.class);
        Mono<ServerResponse> isbnCannotBeDuplicated = ServerResponse.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("Isbn cannot be duplicated");
        return bookMono.flatMap(s -> repository.findByIsbn(s.getIsbn())
                .collectList()
                .flatMap(booklist -> booklist.isEmpty()
                        ? getServerResponseMono(s)
                        : isbnCannotBeDuplicated));
    }

    private Mono<ServerResponse> getServerResponseMono(Book book) {
        return repository.save(book).then(
                        ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue("Book saved: " + book))
                .onErrorResume(ex -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue("An error occurred while saving the data"));
    }
}
