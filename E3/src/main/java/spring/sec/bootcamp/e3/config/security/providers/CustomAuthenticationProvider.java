package spring.sec.bootcamp.e3.config.security.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import spring.sec.bootcamp.e3.config.security.authentication.CustomAuthentication;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


  @Value("${our.very.very.secret.key}")
  private String secretKey;
  /**
   * Question: how does AuthenticationManger know that specific provider is the one to be used
   * => related to the authentication type in the supports method
   *
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
    String headerKey = String.valueOf(customAuthentication.getKey());

    if (headerKey.equals(secretKey)) {
      return new CustomAuthentication(true, null);
    }

    throw new BadCredentialsException("Oh no!");
  }

  /**
   *
   */
  @Override
  public boolean supports(Class<?> authentication) {
    return CustomAuthentication.class.equals(authentication);
  }
}
