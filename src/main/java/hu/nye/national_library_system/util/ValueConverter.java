package hu.nye.national_library_system.util;

import hu.nye.national_library_system.data.BookData;
import hu.nye.national_library_system.data.BookLibraryRefData;
import hu.nye.national_library_system.data.LibraryData;
import hu.nye.national_library_system.entity.Book;
import hu.nye.national_library_system.entity.BookLibraryRef;
import hu.nye.national_library_system.entity.Library;
import org.hibernate.engine.jdbc.internal.BinaryStreamImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ValueConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValueConverter.class);

    private ValueConverter() {
    }

    public static BinaryStreamImpl toBinaryStream(String value) {
        return new BinaryStreamImpl(value.getBytes());
    }

    public static String toString(Blob value) {
        try {
            return new String(value.getBytes(1, (int) value.length()));
        } catch (SQLException e) {
             LOGGER.error("Error durring convertion: ", e);
            return null;
        }
    }

    public static <T> T getValue(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static String getStringValue(String value, String defaultValue) {
        return getValue(value, defaultValue);
    }

    public static String getLongStringValue(String value, String defaultValue) {
        return getValue(value, defaultValue);
    }

    public static Long getNumberValue(Long value, Long defaultValue) {
        return getValue(value, defaultValue);
    }

    public static BigDecimal getFractionValue(BigDecimal value, BigDecimal defaultValue) {
        return getValue(value, defaultValue).stripTrailingZeros();
    }

    public static Boolean getBooleanValue(Boolean value, Boolean defaultValue) {
        return getValue(value, defaultValue);
    }

    public static LocalDate getDateValue(LocalDate value, LocalDate defaultValue) {
        return getValue(value, defaultValue);
    }

    public static LocalTime getTimeValue(LocalTime value, LocalTime defaultValue) {
        return getValue(value, defaultValue);
    }

    public static LocalDateTime getTimestampValue(LocalDateTime value, LocalDateTime defaultValue) {
        return getValue(value, defaultValue);
    }

    public static Book getBookValue(BookData value, Book defaultValue) {
        return value == null ?  defaultValue :  new Book(value);
    }

    public static Library getLibraryValue(LibraryData value, Library defaultValue) {
        return value == null ?  defaultValue :  new Library(value);
    }

    public static List<BookLibraryRef> getBookLibraryRefList(List<BookLibraryRefData> dataArrayField, List<BookLibraryRef> arrayField) {
        return null;
    }

    public static LocalDate stringToLocalDate(String value) {
        if (value == null) {
            return null;
        }
        // LocalDates are 10 characters long.
        return LocalDate.parse(value.substring(0, 10));
    }

    public static LocalTime stringToLocalTime(String value) {
        if (value == null) {
            return null;
        }
        return LocalTime.parse(value);
    }

    public static LocalDateTime stringToLocalDateTime(String value) {
        if (value == null) {
            return null;
        }
        // LocalDateTimes are 16 characters long.
        return LocalDateTime.parse(correctDateString(value.substring(0, 16)));
    }

    private static String correctDateString(String original) {
        if (original.contains(" ")) {
            return original.replace(" ", "T");
        }
        return original;
    }

    public static List<BookLibraryRef> setBookLibraryRefListIds(List<BookLibraryRef> bookLibraryRefList, String bookIsbn, Long libraryId) {
        bookLibraryRefList.forEach(ref -> {
            ref.getId().setBookIsbn(bookIsbn);
            ref.getId().setLibraryId(libraryId);
        });
        return bookLibraryRefList;
    }
}
