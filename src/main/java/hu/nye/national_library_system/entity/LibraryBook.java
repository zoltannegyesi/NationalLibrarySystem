package hu.nye.national_library_system.entity;

import static hu.nye.national_library_system.key.KeyTypeConstants.*;

import hu.nye.national_library_system.data.LibraryBookData;
import hu.nye.national_library_system.entity.pk.BookLibraryRefPK;
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
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedBoolean", parameters = {
            @org.hibernate.annotations.Parameter(name = KEY_TYPE, value = GROUP_WIDE)})
    private Boolean available;

    @Column(name = "lending_date")
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedTimestamp", parameters = {
            @org.hibernate.annotations.Parameter(name = KEY_TYPE, value = USER_WIDE)})
    private LocalDateTime lending_date;

    public LibraryBook(LibraryBookData libraryBookData) {
        apply(libraryBookData);
    }

    public void apply(LibraryBookData libraryBookData) {
        this.id.setBookIsbn(ValueConverter.getStringValue(libraryBookData.getBookIsbn(), this.id.getBookIsbn()));
        this.id.setLibraryId(ValueConverter.getNumberValue(libraryBookData.getLibraryId(), this.id.getLibraryId()));
        this.book = ValueConverter.getBookValue(libraryBookData.getBookData(), this.book);
        this.library = ValueConverter.getLibraryValue(libraryBookData.getLibraryData(), this.library);
        this.available = ValueConverter.getBooleanValue(libraryBookData.getAvailable(), this.available);
        this.lending_date = ValueConverter.getTimestampValue(ValueConverter.stringToLocalDateTime(libraryBookData.getLending_date()), this.lending_date);
    }
}
