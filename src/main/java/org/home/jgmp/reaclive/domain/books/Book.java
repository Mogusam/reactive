package org.home.jgmp.reaclive.domain.books;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Book {
    @Column
    private String author;
    @Column(length = 550)
    private String description;
    @Column
    private String genre;
    @Column
    private Integer id;
    @Column
    private String image;
    @Column
    private BigInteger isbn;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate published;
    @Column
    private String publisher;
    @Column
    private String title;

}
