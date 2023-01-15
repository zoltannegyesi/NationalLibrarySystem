package hu.nye.national_library_system.util;

import javax.sql.rowset.serial.SerialBlob;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EncryptionConverter {

    private EncryptionConverter() {

    }

    public static byte[] toBytes(String input) {
        return input.getBytes();
    }

    public static byte[] toBytes(Long input) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(input);
        return buffer.array();
    }

    public static byte[] toBytes(BigDecimal input) {
        String stringInput = input.toString();
        return stringInput.getBytes();
    }

    public static byte[] toBytes(Boolean input) {
        String stringInput = input.toString();
        return stringInput.getBytes();
    }

    public static byte[] toBytes(LocalDate input) {
        String stringInput = input.toString();
        return stringInput.getBytes();
    }

    public static byte[] toBytes(LocalTime input) {
        String stringInput = input.toString();
        return stringInput.getBytes();
    }

    public static byte[] toBytes(LocalDateTime input) {
        String stringInput = input.toString();
        return stringInput.getBytes();
    }

    public static byte[] toBytes(Blob input) throws SQLException {
        return input.getBytes(1L, (int) input.length());
    }

    public static String toString(byte[] bytes) {
        return new String(bytes);
    }

    public static Long toLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getLong();
    }

    public static BigDecimal toBigDecimal(byte[] bytes) {
        String stringBytes = new String(bytes);
        return new BigDecimal(stringBytes);
    }

    public static Boolean toBoolean(byte[] bytes) {
        String stringBytes = new String(bytes);
        return Boolean.valueOf(stringBytes);
    }

    public static LocalDate toLocalDate(byte[] bytes) {
        String stringBytes = new String(bytes);
        return ValueConverter.stringToLocalDate(stringBytes);
    }

    public static LocalTime toLocalTime(byte[] bytes) {
        String stringBytes = new String(bytes);
        return ValueConverter.stringToLocalTime(stringBytes);
    }

    public static LocalDateTime toLocalDateTime(byte[] bytes) {
        String stringBytes = new String(bytes);
        return ValueConverter.stringToLocalDateTime(stringBytes);
    }

    public static Blob toBlob(byte[] bytes) throws SQLException {
        return new SerialBlob(bytes);
    }
}
