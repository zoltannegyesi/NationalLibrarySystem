package hu.nye.national_library_system.util;

import hu.nye.national_library_system.Constants;
import hu.nye.national_library_system.data.BookData;
import hu.nye.national_library_system.data.BookLibraryRefData;
import hu.nye.national_library_system.data.LibraryData;
import hu.nye.national_library_system.entity.Book;
import hu.nye.national_library_system.entity.BookLibraryRef;
import hu.nye.national_library_system.entity.Library;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class JSONConverter {

    private JSONConverter() {
    }

    public static String getString(String value) {
        return value;
    }

    public static Long getNumber(Long value) {
        return value;
    }

    public static BigDecimal getFraction(BigDecimal value) {
        return value;
    }

    public static Boolean getBoolean(Boolean value) {
        return value;
    }

    public static String localDateToString(LocalDate value) {
        return value.toString() + Constants.END_OF_DATE_ISO_FORMAT;
    }

    public static String localTimeToString(LocalTime value) {
        return value.toString();
    }

    public static String localDateTimeToString(LocalDateTime value) {
        return value.toString() + Constants.END_OF_TIMESTAMP_ISO;
    }

    public static List<BookLibraryRefData> getBookLibraryRef(List<BookLibraryRef> array) {
        return array.stream().map(BookLibraryRefData::new).collect(Collectors.toList());
    }

    public static BookData getBook(Book book) {
        return new BookData(book);
    }

    public static LibraryData getLibrary(Library library) {
        return new LibraryData(library);
    }
}
