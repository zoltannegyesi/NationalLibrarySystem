package hu.nye.national_library_system.data;

import static hu.nye.national_library_system.entity.Book.*;

import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.nye.national_library_system.entity.Book;
import hu.nye.national_library_system.util.JSONConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class BookData {

    private String isbn;

    private BigDecimal eto;

    private String title;

    private String author;

    private String releaseDate;

    private Long numberOfPages;

    private Long price;

    private String description;

    public BookData(Book book) {
        this.isbn = JSONConverter.getString(book.getIsbn());
        this.eto = JSONConverter.getFraction(book.getEto());
        this.title = JSONConverter.getString(book.getTitle());
        this.author = JSONConverter.getString(book.getAuthor());
        this.releaseDate = JSONConverter.localDateToString(book.getReleaseDate());
        this.numberOfPages = JSONConverter.getNumber(book.getNumberOfPages());
        this.price = JSONConverter.getNumber(book.getPrice());
        this.description = JSONConverter.getString(book.getDescription());
    }

    public BookData(ObjectNode changes) {
        this.isbn = JSONConverter.getString(changes.get(FIELD_NAME_ISBN));
        this.eto = JSONConverter.getFraction(changes.get(FIELD_NAME_ETO));
        this.title = JSONConverter.getString(changes.get(FIELD_NAME_TITLE));
        this.author = JSONConverter.getString(changes.get(FIELD_NAME_AUTHOR));
        this.releaseDate = JSONConverter.getDate(changes.get(FIELD_NAME_RELEASE_DATE));
        this.numberOfPages = JSONConverter.getNumber(changes.get(FIELD_NAME_NUMBER_OF_PAGES));
        this.price = JSONConverter.getNumber(changes.get(FIELD_NAME_PRICE));
        this.description = JSONConverter.getString(changes.get(FIELD_NAME_DESCRIPTION));
    }

}
