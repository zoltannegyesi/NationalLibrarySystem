package hu.nye.national_library_system.customtype.type;

import hu.nye.national_library_system.customtype.descriptor.EncryptedNumberDescriptor;
import org.hibernate.type.descriptor.sql.BlobTypeDescriptor;

import java.util.Properties;

public class EncryptedNumber extends  Encrypted<Long> {

    public EncryptedNumber() {
        super(BlobTypeDescriptor.DEFAULT, new EncryptedNumberDescriptor());
    }

    @Override
    public String getName() {
        return "EncryptedNumber";
    }

    @Override
    public void setParameterValues(Properties parameters) {
        setParameterValues(EncryptedNumberDescriptor.class, parameters);
    }

}
