package hu.nye.national_library_system.util;

import com.fasterxml.jackson.databind.JsonNode;
import hu.nye.national_library_system.etc.DateConstants;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
        return value.toString() + DateConstants.END_OF_DATE_ISO_FORMAT;
    }

    public static String localTimeToString(LocalTime value) {
        return value.toString();
    }

    public static String localDateTimeToString(LocalDateTime value) {
        return value.toString() + DateConstants.END_OF_TIMESTAMP_ISO;
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
}
