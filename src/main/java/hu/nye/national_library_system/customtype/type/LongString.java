package hu.nye.national_library_system.customtype.type;

import hu.nye.national_library_system.customtype.descriptor.LongStringDescriptor;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.BlobTypeDescriptor;

public class LongString extends AbstractSingleColumnStandardBasicType<String> {

    public LongString() {
        super(BlobTypeDescriptor.DEFAULT, new LongStringDescriptor());
    }

    @Override
    public String getName() {
        return null;
    }
}
