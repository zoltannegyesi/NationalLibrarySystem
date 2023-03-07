package hu.nye.national_library_system.customtype.descriptor;

import hu.nye.national_library_system.util.ValueConverter;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.ImmutableMutabilityPlan;

import java.sql.Blob;

public class LongStringDescriptor extends AbstractTypeDescriptor<String> {

    @SuppressWarnings("unchecked")
    public LongStringDescriptor() {
        super(String.class, ImmutableMutabilityPlan.INSTANCE);
    }

    @Override
    public String fromString(String string) {
        return string;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <X> X unwrap(String value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        return (X) ValueConverter.toBinaryStream(value);
    }

    @Override
    public <X> String wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        return ValueConverter.toString((Blob) value);
    }
}
