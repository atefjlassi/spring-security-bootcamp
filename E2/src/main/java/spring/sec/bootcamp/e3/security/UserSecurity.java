package spring.sec.bootcamp.e3.security;

import java.util.Collection;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring.sec.bootcamp.e3.entities.User;

/**
 * Decorator pattern
 */
@AllArgsConstructor
@ToString
public class UserSecurity implements UserDetails {

  private final User user;
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return user.getAuthorities().stream().map(SecurityAuthority::new).collect(Collectors.toSet());
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }
}
