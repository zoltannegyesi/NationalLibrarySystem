package hu.nye.national_library_system.data;

import static hu.nye.national_library_system.entity.BookLibraryRef.*;

import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.nye.national_library_system.entity.BookLibraryRef;
import hu.nye.national_library_system.util.JSONConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookLibraryRefData {

    private String bookIsbn;

    private Long libraryId;

    private BookData bookData;

    private LibraryData libraryData;

    private Boolean available;

    private String lending_date;

    public BookLibraryRefData(BookLibraryRef bookLibraryRef) {
        this.bookIsbn = JSONConverter.getString(bookLibraryRef.getId().getBookIsbn());
        this.libraryId = JSONConverter.getNumber(bookLibraryRef.getId().getLibraryId());
        this.bookData = JSONConverter.getBook(bookLibraryRef.getBook());
        this.libraryData = JSONConverter.getLibrary(bookLibraryRef.getLibrary());
        this.available = JSONConverter.getBoolean(bookLibraryRef.getAvailable());
        this.lending_date = JSONConverter.localDateTimeToString(bookLibraryRef.getLending_date());
    }

    public BookLibraryRefData(ObjectNode changes) {
        this.bookIsbn = JSONConverter.getString(changes.get(FIELD_NAME_BOOK_ISBN));
        this.libraryId = JSONConverter.getNumber(changes.get(FIELD_NAME_LIBRARY_ID));
        this.available = JSONConverter.getBoolean(changes.get(FIELD_NAME_AVAILABLE));
        this.lending_date = JSONConverter.getTimestamp(changes.get(FIELD_NAME_LENDING_DATE));
    }
}
