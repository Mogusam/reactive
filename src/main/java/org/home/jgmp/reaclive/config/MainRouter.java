package org.home.jgmp.reaclive.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.home.jgmp.reaclive.domain.decatlon.Sport;
import org.home.jgmp.reaclive.repo.BookRepository;
import org.home.jgmp.reaclive.repo.SportRepository;
import org.home.jgmp.reaclive.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class MainRouter {
    @Autowired
    private SportRepository repository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;


    @Bean
    public RouterFunction<ServerResponse> route() {
//        WebClient client = WebClient.create("https://sports.api.decathlon.com/sports");
        RequestPredicate route = RequestPredicates
                .GET("/hello")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        RequestPredicate route1 = RequestPredicates
                .GET("/1")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        RequestPredicate sportRoute = RequestPredicates
                .GET("/sport")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        RequestPredicate dataRoute = RequestPredicates
                .GET("/data")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        RequestPredicate findBookPathParamRoute = RequestPredicates
                .GET("/book/{title}")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        RequestPredicate findBookTitleRoute = RequestPredicates
                .GET("/book")
                .and(queryParam("title", t -> true))
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        RequestPredicate save5BookRoute = RequestPredicates
                .POST("/book5");
        RequestPredicate newBookRoute = RequestPredicates
                .POST("/book");
        RequestPredicate saveAllBookRoute = RequestPredicates
                .POST("/allbook");
        RequestPredicate saveAllBatchBookRoute = RequestPredicates
                .POST("/all-book-batch");


        return RouterFunctions.nest(path("/api/v1"),
                RouterFunctions.route(route, request -> ok().body(repository.findAll(), Sport.class))
                        .andRoute(route1, request -> ok().body(repository.findById(1), Sport.class))
                        .andRoute(findBookPathParamRoute, bookService::findByTitlePathParams)
                        .andRoute(findBookTitleRoute, bookService::findByTitleQueryParams)
                        .andRoute(newBookRoute, bookService::createBook)
                        .andRoute(save5BookRoute, bookService::save5FromFile)
                        .andRoute(saveAllBookRoute, bookService::saveAll)
                        .andRoute(saveAllBatchBookRoute, bookService::saveAllBatch)


                                   );
    }
}



