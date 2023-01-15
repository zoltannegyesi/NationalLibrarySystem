package hu.nye.national_library_system.customtype.type;

import hu.nye.national_library_system.customtype.descriptor.EncryptedTimeDescriptor;
import org.hibernate.type.descriptor.sql.BlobTypeDescriptor;

import java.time.LocalTime;
import java.util.Properties;

public class EncryptedTime extends  Encrypted<LocalTime> {

    public EncryptedTime() {
        super(BlobTypeDescriptor.DEFAULT, new EncryptedTimeDescriptor());
    }

    @Override
    public String getName() {
        return "EncryptedTime";
    }

    @Override
    public void setParameterValues(Properties parameters) {
        setParameterValues(EncryptedTimeDescriptor.class, parameters);
    }

}
