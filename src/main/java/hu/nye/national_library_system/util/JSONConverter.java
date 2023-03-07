package hu.nye.national_library_system.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.nye.national_library_system.constant.Constants;
import hu.nye.national_library_system.data.*;
import hu.nye.national_library_system.entity.Book;
import hu.nye.national_library_system.entity.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class JSONConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONConverter.class);
    private static final ObjectMapper mapper = new ObjectMapper();

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

    public static BookData getBook(Book book) {
        return new BookData(book);
    }

    public static LibraryData getLibrary(Library library) {
        return new LibraryData(library);
    }

    public static BookReference getBookLabel(Book book) {
        return new BookReference(book.getIsbn(), book.getTitle());
    }

    public static LibraryReference getLibraryLabel(Library library) {
        return new LibraryReference(library.getId(), library.getName());
    }

    public static String getString(JsonNode value) {
        if (value == null) {
            return null;
        }
        return value.asText();
    }

    public static Long getNumber(JsonNode value) {
        if (value == null) {
            return null;
        }
        return value.asLong();
    }

    public static BigDecimal getFraction(JsonNode value) {
        if (value == null) {
            return null;
        }
        return BigDecimal.valueOf(value.asDouble()).stripTrailingZeros();
    }

    public static Boolean getBoolean(JsonNode value) {
        if (value == null) {
            return null;
        }
        return value.asBoolean();
    }

    public static String getDate(JsonNode value) {
        if (value == null) {
            return null;
        }
        return getString(value);
    }

    public static String getTime(JsonNode value) {
        if (value == null) {
            return null;
        }
        return getString(value);
    }

    public static String getTimestamp(JsonNode value) {
        if (value == null) {
            return null;
        }
        return getString(value);
    }

    public static BookReference getBookLabel(JsonNode value) {
        return getLabelObject(value, BookReference.class, BookReference.TYPE_NAME);
    }

    public static LibraryReference getLibraryLabel(JsonNode value) {
        return getLabelObject(value, LibraryReference.class, LibraryReference.TYPE_NAME);
    }

    private static <T> T getLabelObject(JsonNode value, Class<T> objectType, String typeName) {
        if (value == null) {
            return null;
        }
        try {
            return mapper.treeToValue(value, objectType);
        } catch (JsonProcessingException e) {
            LOGGER.error(String.format("Cannot get %s from JsonNode", typeName));
            return null;
        }
    }

}
