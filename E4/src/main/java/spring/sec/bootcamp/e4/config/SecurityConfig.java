package spring.sec.bootcamp.e4.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import spring.sec.bootcamp.e4.config.filters.ApiKeyFilter;

@Configuration
public class SecurityConfig {

  @Value("${the.secret}")
  private String key;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
      .httpBasic(Customizer.withDefaults())
      .addFilterBefore(new ApiKeyFilter(), BasicAuthenticationFilter.class)
      .authorizeHttpRequests(authRequests-> authRequests.anyRequest().authenticated())
      .build();

//    return http
//      .httpBasic(Customizer.withDefaults())
//      .authenticationManager(), can override authentication manager here or by adding bean of type AuthenticationManager in context (see E3 project)
//      .authenticationProvider(), it does not override authentication provider, it adds one more to the collection
//      .build();
  }

}
