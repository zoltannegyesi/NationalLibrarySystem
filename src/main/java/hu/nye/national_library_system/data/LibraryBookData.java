package hu.nye.national_library_system.data;

import static hu.nye.national_library_system.entity.LibraryBook.*;

import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.nye.national_library_system.entity.LibraryBook;
import hu.nye.national_library_system.util.JSONConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LibraryBookData {

    private String bookIsbn;

    private Long libraryId;

    private BookData bookData;

    private LibraryData libraryData;

    private Boolean available;

    private String lending_date;

    public LibraryBookData(LibraryBook libraryBook) {
        this.bookIsbn = JSONConverter.getString(libraryBook.getId().getBookIsbn());
        this.libraryId = JSONConverter.getNumber(libraryBook.getId().getLibraryId());
        this.bookData = JSONConverter.getBook(libraryBook.getBook());
        this.libraryData = JSONConverter.getLibrary(libraryBook.getLibrary());
        this.available = JSONConverter.getBoolean(libraryBook.getAvailable());
        this.lending_date = JSONConverter.localDateTimeToString(libraryBook.getLending_date());
    }

    public LibraryBookData(ObjectNode changes) {
        this.bookIsbn = JSONConverter.getString(changes.get(FIELD_NAME_BOOK_ISBN));
        this.libraryId = JSONConverter.getNumber(changes.get(FIELD_NAME_LIBRARY_ID));
        this.available = JSONConverter.getBoolean(changes.get(FIELD_NAME_AVAILABLE));
        this.lending_date = JSONConverter.getTimestamp(changes.get(FIELD_NAME_LENDING_DATE));
    }
}
