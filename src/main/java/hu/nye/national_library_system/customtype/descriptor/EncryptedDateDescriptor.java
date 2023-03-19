package hu.nye.national_library_system.customtype.descriptor;

import hu.nye.national_library_system.encryption.EncryptionUtil;
import hu.nye.national_library_system.util.ValueConverter;
import org.hibernate.type.descriptor.WrapperOptions;
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
import java.time.LocalDate;

@Component
public class EncryptedDateDescriptor extends EncryptedDescriptor<LocalDate> {

    public EncryptedDateDescriptor() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate fromString (String string) {
        return ValueConverter.stringToLocalDate(string);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <X> X unwrap(LocalDate value, Class<X> type, WrapperOptions options) {
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
    public <X> LocalDate wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        try {
            return EncryptionUtil.decryptToLocalDate(getKey(), (Blob) value);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                BadPaddingException | IllegalBlockSizeException | InvalidKeyException | InvalidKeySpecException | SQLException e) {
            LOGGER.error("Error during decryption", e);
            return null;
        }
    }

}
