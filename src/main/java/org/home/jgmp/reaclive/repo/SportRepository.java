package org.home.jgmp.reaclive.repo;

import org.home.jgmp.reaclive.domain.decatlon.Sport;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface SportRepository extends R2dbcRepository<Sport, Integer> {
    @Query("SELECT * FROM sport WHERE name = :name")
    Flux<Sport> findByName(String lastName);

}
