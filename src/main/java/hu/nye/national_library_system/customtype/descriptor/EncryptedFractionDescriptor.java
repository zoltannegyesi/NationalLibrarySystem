package hu.nye.national_library_system.customtype.descriptor;

import hu.nye.national_library_system.encryption.EncryptionUtil;
import org.hibernate.type.descriptor.WrapperOptions;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Blob;
import java.sql.SQLException;

@Component
public class EncryptedFractionDescriptor extends EncryptedDescriptor<BigDecimal> {

    public EncryptedFractionDescriptor() {
        super(BigDecimal.class);
    }

    @Override
    public BigDecimal fromString (String string) {
        return new BigDecimal(string);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <X> X unwrap(BigDecimal value, Class<X> type, WrapperOptions options) {
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
    public <X> BigDecimal wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        try {
            return EncryptionUtil.decryptToBigDecimal(getKey(), (Blob) value);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                 BadPaddingException | IllegalBlockSizeException | InvalidKeyException | InvalidKeySpecException | SQLException e) {
            LOGGER.error("Error during decryption", e);
            return null;
        }
    }

}
