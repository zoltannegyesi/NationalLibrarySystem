package hu.nye.national_library_system.key;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyHolder {

    @Value("${security.customtypes.securitywritekey}")
    private String systemWideKey;

    private String userWideKey;

    private String groupWideKey;
}
