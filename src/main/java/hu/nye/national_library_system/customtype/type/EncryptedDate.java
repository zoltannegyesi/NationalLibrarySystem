package hu.nye.national_library_system.customtype.type;

import hu.nye.national_library_system.customtype.descriptor.EncryptedDateDescriptor;
import org.hibernate.type.descriptor.sql.BlobTypeDescriptor;

import java.time.LocalDate;
import java.util.Properties;

public class EncryptedDate extends  Encrypted<LocalDate> {

    public EncryptedDate() {
        super(BlobTypeDescriptor.DEFAULT, new EncryptedDateDescriptor());
    }

    @Override
    public String getName() {
        return "EncryptedDate";
    }

    @Override
    public void setParameterValues(Properties parameters) {
        setParameterValues(EncryptedDateDescriptor.class, parameters);
    }

}
