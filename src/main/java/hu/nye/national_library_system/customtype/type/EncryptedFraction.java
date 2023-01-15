package hu.nye.national_library_system.customtype.type;

import hu.nye.national_library_system.customtype.descriptor.EncryptedFractionDescriptor;
import org.hibernate.type.descriptor.sql.BlobTypeDescriptor;

import java.math.BigDecimal;
import java.util.Properties;

public class EncryptedFraction extends  Encrypted<BigDecimal> {

    public EncryptedFraction() {
        super(BlobTypeDescriptor.DEFAULT, new EncryptedFractionDescriptor());
    }

    @Override
    public String getName() {
        return "EncryptedFraction";
    }

    @Override
    public void setParameterValues(Properties parameters) {
        setParameterValues(EncryptedFractionDescriptor.class, parameters);
    }

}
