package hu.nye.national_library_system.entity;

import hu.nye.national_library_system.data.BookLibraryRefData;
import hu.nye.national_library_system.entity.pk.BookLibraryRefPK;
import hu.nye.national_library_system.util.ValueConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookLibraryRef implements Serializable {

    public static final String FIELD_NAME_BOOK_ISBN = "bookIsbn";
    public static final String FIELD_NAME_LIBRARY_ID = "libraryId";
    public static final String FIELD_NAME_AVAILABLE = "available";
    public static final String FIELD_NAME_LENDING_DATE = "lendingDate";

    @EmbeddedId
    private BookLibraryRefPK id;

    @ManyToOne
    @MapsId("bookIsbn")
    @JoinColumn(name = "book_isbn", columnDefinition = "VARCHAR(13)")
    private Book book;

    @ManyToOne
    @MapsId("libraryId")
    @JoinColumn(name = "library_id")
    private Library library;

    @Column(name = "available",  columnDefinition = "TINYINT(1)")
    private Boolean available;

    @Column(name = "lending_date")
    private LocalDateTime lending_date;

    public BookLibraryRef(BookLibraryRefData bookLibraryRefData) {
        apply(bookLibraryRefData);
    }

    public void apply(BookLibraryRefData bookLibraryRefData) {
        this.id.setBookIsbn(ValueConverter.getStringValue(bookLibraryRefData.getBookIsbn(), this.id.getBookIsbn()));
        this.id.setLibraryId(ValueConverter.getNumberValue(bookLibraryRefData.getLibraryId(), this.id.getLibraryId()));
        this.book = ValueConverter.getBookValue(bookLibraryRefData.getBookData(), this.book);
        this.library = ValueConverter.getLibraryValue(bookLibraryRefData.getLibraryData(), this.library);
        this.available = ValueConverter.getBooleanValue(bookLibraryRefData.getAvailable(), this.available);
        this.lending_date = ValueConverter.getTimestampValue(ValueConverter.stringToLocalDateTime(bookLibraryRefData.getLending_date()), this.lending_date);
    }
}
