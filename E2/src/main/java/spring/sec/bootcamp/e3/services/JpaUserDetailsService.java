package spring.sec.bootcamp.e3.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.sec.bootcamp.e3.repositories.UserRepository;
import spring.sec.bootcamp.e3.security.UserSecurity;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    var u = userRepository.findUserByUsername(username);
    return u.map(UserSecurity::new).orElseThrow(() ->
              new UsernameNotFoundException("Username not found " + username));
  }
}
