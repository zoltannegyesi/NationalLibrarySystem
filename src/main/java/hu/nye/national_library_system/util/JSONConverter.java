package hu.nye.national_library_system.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.nye.national_library_system.constant.Constants;
import hu.nye.national_library_system.data.BookData;
import hu.nye.national_library_system.data.LibraryBookData;
import hu.nye.national_library_system.data.LibraryData;
import hu.nye.national_library_system.entity.Book;
import hu.nye.national_library_system.entity.LibraryBook;
import hu.nye.national_library_system.entity.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public static List<LibraryBookData> getBookLibraryRef(List<LibraryBook> array) {
        return array.stream().map(LibraryBookData::new).collect(Collectors.toList());
    }

    public static BookData getBook(Book book) {
        return new BookData(book);
    }

    public static LibraryData getLibrary(Library library) {
        return new LibraryData(library);
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

    public static <T> List<T> getArray(JsonNode array, Function<JsonNode, T> parse) {
        if (array == null) {
            return null;
        }

        return StreamSupport.stream(array.spliterator(), false).map(parse).collect(Collectors.toList());
    }

    public static List<LibraryBookData> getBookLibraryRefArray(JsonNode array) {
        if (array == null) {
            return null;
        }
        return StreamSupport.stream(array.spliterator(), false).map(JSONConverter::getBookLibraryRef).collect(Collectors.toList());
    }

    public static LibraryBookData getBookLibraryRef(JsonNode value) {
        if (value == null) {
            return null;
        }
        return getObject(value, LibraryBookData.class);
    }

    private static <T> T getObject(JsonNode value, Class<T> type) {
        try {
            T object = mapper.treeToValue(value, type);
            return object;
        } catch (JsonProcessingException e) {
            LOGGER.error("Error during JSON conversion ", e);
            return null;
        }
    }
}
