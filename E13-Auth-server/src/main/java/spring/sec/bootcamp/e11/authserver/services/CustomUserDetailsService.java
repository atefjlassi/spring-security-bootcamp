package spring.sec.bootcamp.e11.authserver.services;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.sec.bootcamp.e11.authserver.entities.User;
import spring.sec.bootcamp.e11.authserver.model.SecurityUser;
import spring.sec.bootcamp.e11.authserver.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findUserByUsername(username);
    return user.map(SecurityUser::new).orElseThrow(() -> new UsernameNotFoundException(":("));
  }
}