package hu.nye.national_library_system.data;

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
}
