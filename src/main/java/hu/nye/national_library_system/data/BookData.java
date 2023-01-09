package hu.nye.national_library_system.data;

import hu.nye.national_library_system.entity.Book;
import hu.nye.national_library_system.entity.BookLibraryRef;
import hu.nye.national_library_system.util.JSONConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    private List<BookLibraryRefData> bookLibraryRefDataList;

    public BookData(Book book) {
        this.isbn = JSONConverter.getString(book.getIsbn());
        this.eto = JSONConverter.getFraction(book.getEto());
        this.author = JSONConverter.getString(book.getAuthor());
        this.releaseDate = JSONConverter.localDateToString(book.getReleaseDate());
        this.numberOfPages = JSONConverter.getNumber(book.getNumberOfPages());
        this.price = JSONConverter.getNumber(book.getPrice());
        this.description = JSONConverter.getString(book.getDescription());
        this.bookLibraryRefDataList = JSONConverter.getBookLibraryRef(book.getBookLibraryRefList());
    }

}
