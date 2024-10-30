package spring.sec.bootcamp.e14.resourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import spring.sec.bootcamp.e14.resourceserver.CustomJwtAuthenticationTokenConverter;

@Configuration
public class SecurityConfig {

  @Value("${jwksUri}")
  private String jwksUri;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.oauth2ResourceServer(rs ->
      // tell the resource server from were getting the public key to verfier the token (singing action)
      rs.jwt(jwtConfigurer -> jwtConfigurer.jwkSetUri(jwksUri)
        // convert the authentication Object
        .jwtAuthenticationConverter(new CustomJwtAuthenticationTokenConverter())));

    httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
    return httpSecurity.build();
  }
}
