package hu.nye.national_library_system.data;

import static hu.nye.national_library_system.entity.LibraryBook.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.nye.national_library_system.entity.LibraryBook;
import hu.nye.national_library_system.util.JSONConverter;
import hu.nye.national_library_system.util.LibraryBookReferenceConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LibraryBookData {

    @JsonProperty("book")
    private BookReference bookReference;

    @JsonProperty("library")
    private LibraryReference libraryReference;

    private Boolean available;

    private String lendingDate;

    public LibraryBookData(LibraryBook libraryBook) {
        this.bookReference = LibraryBookReferenceConverter.toBookReference(libraryBook.getBook());
        this.libraryReference = LibraryBookReferenceConverter.toLibraryReference(libraryBook.getLibrary());
        this.available = LibraryBookReferenceConverter.getBoolean(libraryBook.getAvailable());
        this.lendingDate = LibraryBookReferenceConverter.getLocalDateTime(libraryBook.getLending_date());
    }

    public LibraryBookData(ObjectNode changes) {
        this.bookReference = LibraryBookReferenceConverter.BOOK_REFERENCE_FACTORY.getBookReference(changes.get(FIELD_NAME_BOOK));
        this.libraryReference = LibraryBookReferenceConverter.LIBRARY_REFERENCE_FACTORY.getLibraryReference(changes.get(FIELD_NAME_LIBRARY));
        this.available = LibraryBookReferenceConverter.getBoolean(changes.get(FIELD_NAME_AVAILABLE));
        this.lendingDate = LibraryBookReferenceConverter.getLocalDateTime(changes.get(FIELD_NAME_LENDING_DATE));
    }
}
