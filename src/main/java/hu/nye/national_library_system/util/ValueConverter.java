package hu.nye.national_library_system.util;

import hu.nye.national_library_system.data.BookData;
import hu.nye.national_library_system.data.LibraryBookData;
import hu.nye.national_library_system.data.LibraryData;
import hu.nye.national_library_system.entity.Book;
import hu.nye.national_library_system.entity.LibraryBook;
import hu.nye.national_library_system.entity.Library;
import hu.nye.national_library_system.entity.pk.LibraryBookPK;
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
        //TODO: ha a frontendből timestampkét jön, visszaállítani
        // LocalDateTimes are 16 characters long.
        //return LocalDateTime.parse(correctDateString(value.substring(0, 16)));
        return LocalDateTime.of(2000, 1, 1, 1, 1);
    }

    private static String correctDateString(String original) {
        if (original.contains(" ")) {
            return original.replace(" ", "T");
        }
        return original;
    }

    public static List<LibraryBook> setBookLibraryRefListIds(List<LibraryBook> libraryBookList, String bookIsbn, Long libraryId) {
        libraryBookList.forEach(ref -> {
            ref.getId().setBookIsbn(bookIsbn);
            ref.getId().setLibraryId(libraryId);
        });
        return libraryBookList;
    }


}
