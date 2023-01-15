package hu.nye.national_library_system.customtype.type;

import hu.nye.national_library_system.customtype.descriptor.EncryptedLongStringDescriptor;
import org.hibernate.type.descriptor.sql.BlobTypeDescriptor;

import java.util.Properties;

public class EncryptedLongString extends  Encrypted<String> {

    public EncryptedLongString() {
        super(BlobTypeDescriptor.DEFAULT, new EncryptedLongStringDescriptor());
    }

    @Override
    public String getName() {
        return "EncryptedLongString";
    }

    @Override
    public void setParameterValues(Properties parameters) {
        setParameterValues(EncryptedLongStringDescriptor.class, parameters);
    }

}
