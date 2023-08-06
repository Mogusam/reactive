package org.home.jgmp.reaclive;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.home.jgmp.reaclive.domain.books.Book;
import org.home.jgmp.reaclive.domain.books.BookGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@EnableR2dbcRepositories
@SpringBootApplication
public class ReacliveApplication {


    @Autowired
    private Mono<BookGen> fetchBooks;

    public static void main(String[] args) {
        SpringApplication.run(ReacliveApplication.class, args);
    }


//	@Bean
//	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
//
//		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
//		initializer.setConnectionFactory(connectionFactory);
//		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
//
//		return initializer;
//	}

//    @Bean
//    public CommandLineRunner bookDemo(BookRepository repository) {
//
//        return (args) -> {
//            // save a few customers
////			Integer limit = 200;
//
//
//            repository.saveAll(readFromFile() ).subscribe();
//
//            fetchBooks.subscribe(s -> s.getData().forEach(repository::save));
////			repository.saveAll(listOfBook)
////					.blockLast(Duration.ofSeconds(10));
//
//            // fetch all customers
//            log.info("Sport found with findAll():");
//            log.info("-------------------------------");
//            repository.findAll()
//                    .doOnNext(customer -> log.info(customer.toString()))
//                    .blockLast(Duration.ofSeconds(10));
//
//
//            // fetch an individual customer by ID
//            repository.findById(1).doOnNext(customer -> {
//                log.info("Customer found with findById(1L):");
//                log.info("--------------------------------");
//                log.info(customer.toString());
//                log.info("");
//            }).block(Duration.ofSeconds(10));
//
//
////			// fetch customers by last name
////			log.info("--------------------------------------------");
////			repository.findById(1).doOnNext(bauer -> {
////				log.info(bauer.toString());
////			}).block(Duration.ofSeconds(10));
////			log.info("");
//        };
//    }

//@Bean
//	public CommandLineRunner demo(SportRepository repository) {
//
//		return (args) -> {
//			// save a few customers
//			repository.saveAll(Arrays.asList(new Sport(1,"Soccer"),
//							new Sport(2, "Chess"),
//							new Sport(3,"baseboll"),
//							new Sport(4,"Tennis"),
//							new Sport(5, "bicycle racing")))
//					.blockLast(Duration.ofSeconds(10));
//
//			// fetch all customers
//			log.info("Sport found with findAll():");
//			log.info("-------------------------------");
//			repository.findAll().doOnNext(customer -> {
//				log.info(customer.toString());
//			}).blockLast(Duration.ofSeconds(10));
//
//			log.info("");
//
//			// fetch an individual customer by ID
//			repository.findById(1).doOnNext(customer -> {
//				log.info("Customer found with findById(1L):");
//				log.info("--------------------------------");
//				log.info(customer.toString());
//				log.info("");
//			}).block(Duration.ofSeconds(10));
//
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):");
//			log.info("--------------------------------------------");
//			repository.findByName("Chess").doOnNext(bauer -> {
//				log.info(bauer.toString());
//			}).blockLast(Duration.ofSeconds(10));
//			log.info("");
//		};
//	}

    private List<Book> readFromFile() {

        List<Book> books;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File src = new ClassPathResource("books.json").getFile();
            books = objectMapper.readValue(src, new TypeReference<List<Book>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return books;

    }


}
