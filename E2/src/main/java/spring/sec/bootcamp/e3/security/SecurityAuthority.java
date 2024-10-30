package spring.sec.bootcamp.e3.security;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import spring.sec.bootcamp.e3.entities.Authority;

@AllArgsConstructor
@ToString
public class SecurityAuthority implements GrantedAuthority {

  private final Authority authority;

  @Override
  public String getAuthority() {
    return authority.getName();
  }
}
