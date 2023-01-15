package hu.nye.national_library_system.customtype.type;

import hu.nye.national_library_system.customtype.descriptor.EncryptedDescriptor;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.java.JavaTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;
import org.hibernate.usertype.DynamicParameterizedType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public abstract class Encrypted<T> extends AbstractSingleColumnStandardBasicType<T> implements DynamicParameterizedType {

    protected static final Logger LOGGER = LoggerFactory.getLogger(Encrypted.class);

    protected Encrypted(SqlTypeDescriptor sqlTypeDescriptor, JavaTypeDescriptor<T> javaTypeDescriptor) {
        super(sqlTypeDescriptor, javaTypeDescriptor);
    }

    protected <U extends EncryptedDescriptor<T>> void setParameterValues(Class<U> descriptorType, Properties parameters) {
        U descriptor = descriptorType.cast(getJavaTypeDescriptor());
        descriptor.setParameters(parameters);
    }
}
