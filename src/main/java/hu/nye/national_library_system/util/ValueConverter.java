package hu.nye.national_library_system.util;

import org.hibernate.engine.jdbc.internal.BinaryStreamImpl;

import java.sql.Blob;
import java.sql.SQLException;

public class ValueConverter {

    private ValueConverter() {
    }

    public static BinaryStreamImpl toBinaryStream(String value) {
        return new BinaryStreamImpl(value.getBytes());
    }

    public static String toString(Blob value) {
        try {
            return new String(value.getBytes(1, (int) value.length()));
        } catch (SQLException e) {
            //TODO: logger
            // LOGGER.error("Error durring convertion: ", e);
            return null;
        }
    }
}
