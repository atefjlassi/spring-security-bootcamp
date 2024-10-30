package spring.sec.bootcamp.e4.config.providers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import spring.sec.bootcamp.e4.config.authentications.ApiKeyAuthentication;

@RequiredArgsConstructor
public class ApiKeyProvider implements AuthenticationProvider {

  @Value("${the.secret}")
  private final String key;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    ApiKeyAuthentication apiKeyAuthentication = (ApiKeyAuthentication) authentication;
    if (apiKeyAuthentication.getKey().equals(key)) {
      apiKeyAuthentication.setAuthenticated(true);
      return apiKeyAuthentication;
    }

    throw new BadCredentialsException("Oh no");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return ApiKeyAuthentication.class.equals(authentication);
  }
}
