package hu.nye.national_library_system.entity;

import hu.nye.national_library_system.data.BookData;
import hu.nye.national_library_system.util.ValueConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    public static final String TYPE_NAME = "Book";
    public static final String FIELD_NAME_ISBN = "isbn";
    public static final String FIELD_NAME_ETO = "eto";
    public static final String FIELD_NAME_TITLE = "title";
    public static final String FIELD_NAME_AUTHOR = "author";
    public static final String FIELD_NAME_RELEASE_DATE = "releaseDate";
    public static final String FIELD_NAME_NUMBER_OF_PAGES = "numberOfPages";
    public static final String FIELD_NAME_PRICE = "price";
    public static final String FIELD_NAME_DESCRIPTION = "description";
    public static final String FIELD_NAME_BOOK_LIBRARY_REF_LIST = "bookLibraryRefList";

    @Id
    @Column(name = "isbn", length = 13)
    private String isbn;

    @Column(name = "eto")
    private BigDecimal eto;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "author", length = 100)
    private String author;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "number_of_pages")
    private Long numberOfPages;

    @Column(name = "price")
    private Long price;

    @Column(name = "description")
    @Type(type = "hu.nye.national_library_system.customtype.type.LongString")
    private String description;

    @OneToMany(mappedBy = "library")
    private List<BookLibraryRef> bookLibraryRefList;


    public Book(BookData bookData) {
        apply(bookData);
    }

    public void apply(BookData bookData) {
        this.isbn = ValueConverter.getStringValue(bookData.getIsbn(), this.isbn);
        this.eto = ValueConverter.getFractionValue(bookData.getEto(), this.eto);
        this.title = ValueConverter.getStringValue(bookData.getTitle(), this.title);
        this.author = ValueConverter.getStringValue(bookData.getAuthor(), this.title);
        this.releaseDate = ValueConverter.getDateValue(ValueConverter.stringToLocalDate(bookData.getReleaseDate()), this.releaseDate);
        this.numberOfPages = ValueConverter.getNumberValue(bookData.getNumberOfPages(), this.numberOfPages);
        this.price = ValueConverter.getNumberValue(bookData.getPrice(), this.price);
        this.description = ValueConverter.getLongStringValue(bookData.getDescription(), this.description);
        this.bookLibraryRefList = ValueConverter.getBookLibraryRefList(bookData.getBookLibraryRefDataList(), this.bookLibraryRefList);
    }

    public void setArrayFieldIds(String isbn) {
        this.bookLibraryRefList = ValueConverter.setBookLibraryRefListIds(bookLibraryRefList, isbn, null);
    }
}
