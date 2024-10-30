package spring.sec.bootcamp.e4.config.manager;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import spring.sec.bootcamp.e4.config.providers.ApiKeyProvider;

@AllArgsConstructor
public class CustomAuthenticationManger implements AuthenticationManager {

  @Value("${the.secret}")
  private final String key;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    var provider = new ApiKeyProvider(key);
    if (provider.supports(authentication.getClass())) {
     return provider.authenticate(authentication);
    }

    return authentication;
  }


}
