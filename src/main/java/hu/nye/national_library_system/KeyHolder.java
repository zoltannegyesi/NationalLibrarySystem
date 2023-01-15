package hu.nye.national_library_system;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Scope(value = "session", proxyMode = TARGET_CLASS)
public class KeyHolder {

    @Value("${security.customtypes.securitywritekey}")
    private String systemWideKey;

    private String userWideKey;

    private String groupWideKey;
}
