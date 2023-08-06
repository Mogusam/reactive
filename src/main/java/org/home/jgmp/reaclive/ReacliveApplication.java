package org.home.jgmp.reaclive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import lombok.extern.log4j.Log4j2;

@Log4j2
@EnableR2dbcRepositories
@SpringBootApplication
public class ReacliveApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReacliveApplication.class, args);
    }

}
