package hu.nye.national_library_system.entity;

import static hu.nye.national_library_system.key.KeyTypeConstants.*;
import static javax.persistence.GenerationType.IDENTITY;

import hu.nye.national_library_system.data.LibraryBookData;
import hu.nye.national_library_system.service.BookService;
import hu.nye.national_library_system.service.LibraryService;
import hu.nye.national_library_system.util.LibraryBookReferenceConverter;
import hu.nye.national_library_system.util.ValueConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryBook implements Serializable {

    public static final String TYPE_NAME = "LibraryBook";

    public static final String FIELD_NAME_ID = "id";

    public static final String FIELD_NAME_BOOK = "book";

    public static final String FIELD_NAME_LIBRARY = "library";
    public static final String FIELD_NAME_AVAILABLE = "available";
    public static final String FIELD_NAME_LENDING_DATE = "lendingDate";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Transient
    private String bookIsbn;

    @Transient
    private Long libraryId;

    @ManyToOne
    @JoinColumn(name = "book_isbn", columnDefinition = "VARCHAR(13)")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    @Column(name = "available")
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedBoolean", parameters = {
            @org.hibernate.annotations.Parameter(name = KEY_TYPE, value = GROUP_WIDE)})
    private Boolean available;

    @Column(name = "lending_date")
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedTimestamp", parameters = {
            @org.hibernate.annotations.Parameter(name = KEY_TYPE, value = USER_WIDE)})
    private LocalDateTime lending_date;

    public LibraryBook(LibraryBookData libraryBookData, BookService bookService, LibraryService libraryService) {
        apply(libraryBookData, bookService, libraryService);
    }

    public void apply(LibraryBookData libraryBookData,  BookService bookService, LibraryService libraryService) {
        this.id = ValueConverter.getNumberValue(libraryBookData.getId(), this.id);
        this.book = LibraryBookReferenceConverter.getBook(bookService, libraryBookData.getBookReference(), this.book);
        this.library = LibraryBookReferenceConverter.getLibrary(libraryService, libraryBookData.getLibraryReference(), this.library);
        this.available = ValueConverter.getBooleanValue(libraryBookData.getAvailable(), this.available);
        this.lending_date = ValueConverter.getTimestampValue(ValueConverter.stringToLocalDateTime(libraryBookData.getLendingDate()), this.lending_date);
    }
}
