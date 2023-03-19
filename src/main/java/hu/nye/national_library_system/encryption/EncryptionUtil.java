package hu.nye.national_library_system.encryption;

import org.hibernate.engine.jdbc.internal.BinaryStreamImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class EncryptionUtil implements ApplicationContextAware {

    private static AESUtil aesUtil;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        EncryptionUtil.aesUtil = context.getBean(AESUtil.class);
    }

    public static BinaryStreamImpl encrypt(String key, String input)  throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        byte[] inputBytes = EncryptionConverter.toBytes(input);
        return encrypt(key, inputBytes);
    }

    public static BinaryStreamImpl encrypt(String key, Long input)  throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        byte[] inputBytes = EncryptionConverter.toBytes(input);
        return encrypt(key, inputBytes);
    }

    public static BinaryStreamImpl encrypt(String key, BigDecimal input)  throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        byte[] inputBytes = EncryptionConverter.toBytes(input);
        return encrypt(key, inputBytes);
    }

    public static BinaryStreamImpl encrypt(String key, Boolean input)  throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        byte[] inputBytes = EncryptionConverter.toBytes(input);
        return encrypt(key, inputBytes);
    }

    public static BinaryStreamImpl encrypt(String key, LocalDate input)  throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        byte[] inputBytes = EncryptionConverter.toBytes(input);
        return encrypt(key, inputBytes);
    }

    public static BinaryStreamImpl encrypt(String key, LocalTime input)  throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        byte[] inputBytes = EncryptionConverter.toBytes(input);
        return encrypt(key, inputBytes);
    }

    public static BinaryStreamImpl encrypt(String key, LocalDateTime input)  throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        byte[] inputBytes = EncryptionConverter.toBytes(input);
        return encrypt(key, inputBytes);
    }

    public static String decryptToString(String key, Blob cipher) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException, SQLException{
        byte[] decryptedBytes = decrypt(key, cipher);
        return EncryptionConverter.toString(decryptedBytes);
    }

    public static Long decryptToLong(String key, Blob cipher) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException, SQLException{
        byte[] decryptedBytes = decrypt(key, cipher);
        return EncryptionConverter.toLong(decryptedBytes);
    }

    public static BigDecimal decryptToBigDecimal(String key, Blob cipher) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException, SQLException{
        byte[] decryptedBytes = decrypt(key, cipher);
        return EncryptionConverter.toBigDecimal(decryptedBytes);
    }

    public static Boolean decryptToBoolean(String key, Blob cipher) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException, SQLException{
        byte[] decryptedBytes = decrypt(key, cipher);
        return EncryptionConverter.toBoolean(decryptedBytes);
    }

    public static LocalDate decryptToLocalDate(String key, Blob cipher) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException, SQLException{
        byte[] decryptedBytes = decrypt(key, cipher);
        return EncryptionConverter.toLocalDate(decryptedBytes);
    }

    public static LocalTime decryptToLocalTime(String key, Blob cipher) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException, SQLException{
        byte[] decryptedBytes = decrypt(key, cipher);
        return EncryptionConverter.toLocalTime(decryptedBytes);
    }

    public static LocalDateTime decryptToLocalDateTime(String key, Blob cipher) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException, SQLException{
        byte[] decryptedBytes = decrypt(key, cipher);
        return EncryptionConverter.toLocalDateTime(decryptedBytes);
    }

    private static BinaryStreamImpl encrypt(String key, byte[] input)  throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        byte[] encryptedInput = aesUtil.encrypt(key, input);
        return new BinaryStreamImpl(encryptedInput);
    }

    private static byte[] decrypt(String key, Blob cipher) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException, SQLException {
        byte[] cipherBytes = cipher.getBytes(1, (int) cipher.length());
        cipher.free();
        return aesUtil.decrypt(key, cipherBytes);
    }

}
