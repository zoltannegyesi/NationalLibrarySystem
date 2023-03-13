package hu.nye.national_library_system.entity;

import static hu.nye.national_library_system.etc.KeyTypeConstants.*;

import hu.nye.national_library_system.data.BookData;
import hu.nye.national_library_system.util.ValueConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.Parameter;
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

    @Id
    @Column(name = "isbn", length = 13)
    private String isbn;

    @Column(name = "eto")
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedFraction", parameters = {
            @Parameter(name = KEY_TYPE, value = GROUP_WIDE)})
    private BigDecimal eto;

    @Column(name = "title", length = 100)
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedString", parameters = {
            @Parameter(name = KEY_TYPE, value = USER_WIDE)})
    private String title;

    @Column(name = "author", length = 100)
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedString", parameters = {
            @Parameter(name = KEY_TYPE, value = GROUP_WIDE)})
    private String author;

    @Column(name = "release_date")
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedDate", parameters = {
            @Parameter(name = KEY_TYPE, value = USER_WIDE)})
    private LocalDate releaseDate;

    @Column(name = "number_of_pages")
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedNumber", parameters = {
            @Parameter(name = KEY_TYPE, value = SYSTEM_WIDE)})
    private Long numberOfPages;

    @Column(name = "price")
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedNumber", parameters = {
            @Parameter(name = KEY_TYPE, value = USER_WIDE)})
    private Long price;

    @Column(name = "description")
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedLongString", parameters = {
            @Parameter(name = KEY_TYPE, value = SYSTEM_WIDE)})
    private String description;

    @OneToMany(mappedBy = "book", orphanRemoval = true)
    @ToString.Exclude private List<LibraryBook> libraryBooks;

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
    }
}
