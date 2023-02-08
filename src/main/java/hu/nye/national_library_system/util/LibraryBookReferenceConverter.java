package hu.nye.national_library_system.util;

import com.fasterxml.jackson.databind.JsonNode;
import hu.nye.national_library_system.constant.Constants;
import hu.nye.national_library_system.data.BookReference;
import hu.nye.national_library_system.data.LibraryReference;
import hu.nye.national_library_system.entity.Book;
import hu.nye.national_library_system.entity.Library;
import hu.nye.national_library_system.entity.pk.LibraryBookPK;
import hu.nye.national_library_system.service.BookService;
import hu.nye.national_library_system.service.LibraryService;

import java.time.LocalDateTime;

public class LibraryBookReferenceConverter {

    public static final ReferenceFactory BOOK_REFERENCE_FACTORY;

    public static final ReferenceFactory LIBRARY_REFERENCE_FACTORY;

    static {
        BOOK_REFERENCE_FACTORY = new ReferenceFactory(Book.FIELD_NAME_ISBN, Book.FIELD_NAME_TITLE);
        LIBRARY_REFERENCE_FACTORY = new ReferenceFactory(Library.FIELD_NAME_ID, Library.FIELD_NAME_NAME);
    }

    private LibraryBookReferenceConverter () {

    }

    public static BookReference toBookReference(Book book) {
        return new BookReference(book.getIsbn(), book.getTitle());
    }

    public static LibraryReference toLibraryReference(Library library) {
        return new LibraryReference(library.getId(), library.getName());
    }

    public static Boolean getBoolean(Boolean value) {
        return JSONConverter.getBoolean(value);
    }

    public static String getLocalDateTime(LocalDateTime value) {
        return JSONConverter.localDateTimeToString(value);
    }

    public static Boolean getBoolean(JsonNode value) {
        return JSONConverter.getBoolean(value);
    }

    public static String getLocalDateTime(JsonNode value) {
        return JSONConverter.getTimestamp(value);
    }


    public static LibraryBookPK getLibraryBookId(LibraryBookPK value, LibraryBookPK defaultValue) {
        return ValueConverter.getValue(value, defaultValue);
    }

    public static Book getBook(BookService bookService, BookReference bookReference, Book defaultValue) {
        if (bookReference == null) {
            return defaultValue;
        }
        return bookService.load(bookReference.getIsbn()).block();
    }

    public static Library getLibrary(LibraryService libraryService, LibraryReference libraryReference, Library defaultValue) {
        if (libraryReference == null) {
            return defaultValue;
        }
        return libraryService.load(libraryReference.getId()).block();
    }

}
