package org.home.jgmp.reaclive.repo;

import java.math.BigInteger;

import org.home.jgmp.reaclive.domain.books.Book;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends R2dbcRepository<Book, Integer> {

    Flux<Book> findByTitle(@PathVariable("title") String title);

    Flux<Book> findByIsbn(@PathVariable("isbn") BigInteger title);
}
