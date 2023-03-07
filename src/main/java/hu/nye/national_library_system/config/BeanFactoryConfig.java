package hu.nye.national_library_system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.spec.IvParameterSpec;
import java.util.Arrays;

@Configuration
public class BeanFactoryConfig {

    @Value("${security.customtypes.secretkeyfactory.algorithm}")
    private String secretKeyFactoryAlgorithm;

    @Value("${security.customtypes.algorithm}")
    private String algorithm;

    @Value("${security.customtypes.salt}")
    private String salt;

    @Value("${security.customtypes.iv}")
    private String iv;

    @Bean(name = "secretKeyFactoryAlgorithm")
    public String secretKeyFactoryAlgorithm() {
        return secretKeyFactoryAlgorithm;
    }

    @Bean(name = "algorithm")
    public String algorithm() {
        return algorithm;
    }

    @Bean(name = "salt")
    public String salt() {
        return salt;
    }

    @Bean(name = "ivParameterSpec")
    public IvParameterSpec ivParameterSpec() {
        return new IvParameterSpec(padIv(iv.getBytes()));
    }

    private byte[] padIv(byte[] iv) {
        byte[] result = iv;
        if (iv.length != 16) {
            result = Arrays.copyOfRange(iv, 0, 16);
        }
        return result;
    }
}
