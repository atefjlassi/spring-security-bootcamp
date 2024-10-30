package spring.sec.bootcamp.e15.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.oauth2ResourceServer(
      oauth2Config -> oauth2Config.opaqueToken(opaqueTokenConfigurer -> opaqueTokenConfigurer
        .introspectionUri("http://localhost:8080/oauth2/introspect")
        .introspectionClientCredentials("myclient", "mysecret")));

    httpSecurity.authorizeHttpRequests(requestConfig -> requestConfig.anyRequest().authenticated());
    return httpSecurity.build();
  }
}
