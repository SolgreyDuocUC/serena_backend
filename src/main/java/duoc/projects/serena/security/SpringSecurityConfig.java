package duoc.projects.serena.security;

import duoc.projects.serena.security.filter.JwtAuthenticationFilter;
import duoc.projects.serena.security.filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return this.authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authz -> {
                    authz
                            // Endpoints de usuarios (públicos)
                            .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/users/{id}").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/users/email/{email}").permitAll()
                            .requestMatchers(HttpMethod.PUT, "/api/users/{id}").permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").permitAll()

                            // Endpoints de emociones (todos públicos)
                            .requestMatchers(HttpMethod.GET, "/api/v1/emotions").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/emotions/{id}").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/emotions").permitAll()
                            .requestMatchers(HttpMethod.PUT, "/api/v1/emotions/{id}").permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/api/v1/emotions/{id}").permitAll()

                            // Endpoints de sesiones activas (públicos)
                            .requestMatchers(HttpMethod.GET, "/api/v1/user-active-sessions").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/user-active-sessions/{id}").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/user-active-sessions").permitAll()
                            .requestMatchers(HttpMethod.PUT, "/api/v1/user-active-sessions/{id}").permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/api/v1/user-active-sessions/{id}").permitAll()

                            // Endpoints de registro emocional (públicos)
                            .requestMatchers(HttpMethod.GET, "/api/v1/emotional-registers").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/emotional-registers/{id}").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/emotional-registers").permitAll()
                            .requestMatchers(HttpMethod.PUT, "/api/v1/emotional-registers/{id}").permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/api/v1/emotional-registers/{id}").permitAll()

                            // Cualquier otra petición requiere autenticación
                            .anyRequest().authenticated();
                })
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
