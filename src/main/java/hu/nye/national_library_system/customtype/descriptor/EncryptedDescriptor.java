package hu.nye.national_library_system.customtype.descriptor;

import static hu.nye.national_library_system.key.KeyTypeConstants.*;

import hu.nye.national_library_system.key.KeyHolder;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.ImmutableMutabilityPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Properties;

public abstract class EncryptedDescriptor<T> extends AbstractTypeDescriptor<T> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(EncryptedDescriptor.class);

    private static transient ApplicationContext applicationContext;

    private transient String keyType;

    @SuppressWarnings("unchecked")
    protected EncryptedDescriptor(Class<T> type) {
        super(type, ImmutableMutabilityPlan.INSTANCE);
    }

    @Autowired
    public final void setApplicationContext(ApplicationContext applicationContext) {
        EncryptedDescriptor.applicationContext = applicationContext;
    }

    public String getKey() {
        KeyHolder keyHolder = applicationContext.getBean(KeyHolder.class);
        String key = null;
        switch(keyType) {
            case SYSTEM_WIDE:
                key = keyHolder.getSystemWideKey();
                break;
            case USER_WIDE:
                key = keyHolder.getUserWideKey();
                break;
            case GROUP_WIDE:
                key = keyHolder.getGroupWideKey();
                break;
            default:
                break;
        }
        return key;
    }

    public final void setParameters(Properties parameters) {
        this.keyType = parameters.getProperty(KEY_TYPE);
    }

}
