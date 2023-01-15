package hu.nye.national_library_system.constant;

public class Constants {
    private Constants() {
    }

    public static final String END_OF_DATE_ISO_FORMAT = "T00:00:00.000Z";

    public static final String END_OF_TIMESTAMP_ISO = ":00.000Z";

    public static final String SET_AUTOINCREMENT = "ALTER TABLE %s AUTO_INCREMENT=%d";

    public static final String LOGGER_FORMAT = "{}. FieldName: {}, message: {}, value: {}";

    public static final String INVALID_MESSAGE = "Invalid %s";

}
