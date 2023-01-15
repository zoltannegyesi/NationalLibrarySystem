package hu.nye.national_library_system.customtype.type;

import hu.nye.national_library_system.customtype.descriptor.EncryptedTimestampDescriptor;
import org.hibernate.type.descriptor.sql.BlobTypeDescriptor;

import java.time.LocalDateTime;
import java.util.Properties;

public class EncryptedTimestamp extends  Encrypted<LocalDateTime> {

    public EncryptedTimestamp() {
        super(BlobTypeDescriptor.DEFAULT, new EncryptedTimestampDescriptor());
    }

    @Override
    public String getName() {
        return "EncryptedTimestamp";
    }

    @Override
    public void setParameterValues(Properties parameters) {
        setParameterValues(EncryptedTimestampDescriptor.class, parameters);
    }

}
