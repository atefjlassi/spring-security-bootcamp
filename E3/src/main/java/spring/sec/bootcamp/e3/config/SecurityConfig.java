package spring.sec.bootcamp.e3.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import spring.sec.bootcamp.e3.config.security.filters.CustomAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

  private final CustomAuthenticationFilter customAuthenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
      .addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
      .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()) // its about authorization
      .build();
  }
}
