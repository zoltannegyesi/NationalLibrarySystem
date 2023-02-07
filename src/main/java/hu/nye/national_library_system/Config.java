package hu.nye.national_library_system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class Config {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests().anyRequest().permitAll().and().csrf().disable().cors().configurationSource(req-> con());
    return http.build();
    }

    private CorsConfiguration con() {
        CorsConfiguration con = new CorsConfiguration();
        con.setAllowedOrigins(Arrays.asList("https://nls.xoaf.eu"));
        con.setAllowedMethods(Arrays.asList("GET", "POST"));
        con.setAllowCredentials(true);
        con.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        con.setExposedHeaders(Arrays.asList("Authorization"));
        return con;
    }
}
