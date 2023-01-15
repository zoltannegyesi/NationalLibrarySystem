package hu.nye.national_library_system.customtype.type;

import hu.nye.national_library_system.customtype.descriptor.EncryptedBooleanDescriptor;
import org.hibernate.type.descriptor.sql.BlobTypeDescriptor;

import java.util.Properties;

public class EncryptedBoolean extends  Encrypted<Boolean> {

    public EncryptedBoolean() {
        super(BlobTypeDescriptor.DEFAULT, new EncryptedBooleanDescriptor());
    }

    @Override
    public String getName() {
        return "EncryptedBoolean";
    }

    @Override
    public void setParameterValues(Properties parameters) {
        setParameterValues(EncryptedBooleanDescriptor.class, parameters);
    }

}
