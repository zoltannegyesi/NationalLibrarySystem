package hu.nye.national_library_system.customtype.type;

import hu.nye.national_library_system.customtype.descriptor.EncryptedStringDescriptor;
import org.hibernate.type.descriptor.sql.BlobTypeDescriptor;

import java.util.Properties;

public class EncryptedString extends  Encrypted<String> {

    public EncryptedString() {
        super(BlobTypeDescriptor.DEFAULT, new EncryptedStringDescriptor());
    }

    @Override
    public String getName() {
        return "EncryptedString";
    }

    @Override
    public void setParameterValues(Properties parameters) {
        setParameterValues(EncryptedStringDescriptor.class, parameters);
    }

}
