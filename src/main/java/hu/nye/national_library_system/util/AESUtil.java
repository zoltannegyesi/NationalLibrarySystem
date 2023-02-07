package hu.nye.national_library_system.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class AESUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AESUtil.class);

    private String secretKeyFactoryAlgorithm;

    private String algorithm;

    private String salt;

    private IvParameterSpec iv;

    @Autowired
    public AESUtil(String secretKeyFactoryAlgorithm, String algorithm, String salt,
                    IvParameterSpec iv) {
        this.secretKeyFactoryAlgorithm = secretKeyFactoryAlgorithm;
        this.algorithm = algorithm;
        this.salt = salt;
        this.iv = iv;
    }

    public byte[] encrypt(String key, byte[] input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        SecretKey secretKey = createSecretKey(key);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] cipherText = cipher.doFinal(input);
        return Base64.getEncoder().encode(cipherText);
    }

    public byte[] decrypt(String key, byte[] cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        SecretKey secretKey = null;
        try {
            secretKey = createSecretKey(key);
        } catch (NullPointerException e) {
            LOGGER.error("The given key does not match with the key used for encryption.", e);
            return new byte[0];
        }
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        return cipher.doFinal(Base64.getDecoder().decode(cipherText));
    }

    public SecretKey createSecretKey(String input) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance(secretKeyFactoryAlgorithm);
        KeySpec spec = new PBEKeySpec(input.toCharArray(), salt.getBytes(), 65536, 256);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
    }

}
