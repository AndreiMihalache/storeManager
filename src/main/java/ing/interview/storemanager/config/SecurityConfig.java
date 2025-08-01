package ing.interview.storemanager.config;

import ing.interview.storemanager.service.StoreUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    public static final String PRODUCTS_ENDPOINT = "/products/**";
    public static final String ADMIN = "ADMIN";
    private final StoreUserDetailService storeUserDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(storeUserDetailService)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, PRODUCTS_ENDPOINT).permitAll()
                        .requestMatchers(HttpMethod.POST, PRODUCTS_ENDPOINT).hasAuthority(ADMIN)
                        .requestMatchers(HttpMethod.PUT, PRODUCTS_ENDPOINT).hasAuthority(ADMIN)
                        .requestMatchers(HttpMethod.DELETE, PRODUCTS_ENDPOINT).hasAuthority(ADMIN)
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

}
