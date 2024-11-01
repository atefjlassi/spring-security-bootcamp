package spring.sec.bootcamp.e3.config.security.manager;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import spring.sec.bootcamp.e3.config.security.providers.CustomAuthenticationProvider;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

  private final CustomAuthenticationProvider provider;
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (provider.supports(authentication.getClass())) {
      return provider.authenticate(authentication);
    }

//    if (provider_2.supports(authentication.getClass())) {
//      return provider.authenticate(authentication);
//    }

    throw new BadCredentialsException("Oh no!");

  }
}
