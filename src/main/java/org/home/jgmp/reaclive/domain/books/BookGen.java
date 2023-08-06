package org.home.jgmp.reaclive.domain.books;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookGen {
    private String status;
    private Integer code;
    private Integer total;
    private List<Book> data;

}
