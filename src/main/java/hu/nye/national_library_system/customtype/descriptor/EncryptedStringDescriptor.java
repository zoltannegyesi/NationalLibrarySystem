package hu.nye.national_library_system.customtype.descriptor;

import hu.nye.national_library_system.util.EncryptionUtil;
import hu.nye.national_library_system.util.ValueConverter;
import org.hibernate.type.descriptor.WrapperOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Blob;
import java.sql.SQLException;

@Component
public class EncryptedStringDescriptor extends EncryptedDescriptor<String> {

    public EncryptedStringDescriptor() {
        super(String.class);
    }

    @Override
    public String fromString (String string) {
        return string;
    }

    @Override
    public <X> X unwrap(String value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        try {
            return (X) EncryptionUtil.encrypt(getKey(), value);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                 BadPaddingException | IllegalBlockSizeException | InvalidKeyException | InvalidKeySpecException e) {
            LOGGER.error("Error during encryption", e);
            return null;
        }
    }

    @Override
    public <X> String wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        try {
            return EncryptionUtil.decryptToString(getKey(), (Blob) value);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                 BadPaddingException | IllegalBlockSizeException | InvalidKeyException | InvalidKeySpecException | SQLException e) {
            LOGGER.error("Error during decryption", e);
            return null;
        }
    }

}
