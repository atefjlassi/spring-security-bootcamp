package spring.sec.bootcamp.e5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {


  @Bean
  public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .httpBasic(Customizer.withDefaults())
      // matcher method + authorization rule
      // 1. which matcher methods should you use and how (anyRequest(), requestMatchers() , the rest is @Deprecated: mvcMatchers(), antMatchers(), regexMatchers())
      // 2. how to apply different authorization rules
      .authorizeHttpRequests(authManager
          -> authManager
          .requestMatchers("/demo").hasRole("MANAGER")
          .requestMatchers("/hello").denyAll()
          .anyRequest().hasAnyAuthority("write", "read")
//        .anyRequest().permitAll()
//          .anyRequest().denyAll()
//          .anyRequest().hasAuthority("read")
//          .anyRequest().hasAnyAuthority("read", "write")
//          .anyRequest().hasRole("MANAGER")
//          .anyRequest().hasAnyRole("ADMIN", "MANAGER")
//          .anyRequest().access("tbd")
      ) // endpoint level authorization

      .build();


  }


  @Bean
  public UserDetailsService userDetailsService() {

    var uds = new InMemoryUserDetailsManager();

    var u1 = User.withUsername("bill")
      .password(passwordEncoder().encode("password"))
      .authorities("read")
      .build();
    var u2 = User.withUsername("sam")
      .password(passwordEncoder().encode("password"))
      .authorities("delete")
      .build();
    var u3 = User.withUsername("jhon")
      .password(passwordEncoder().encode("password"))
      .authorities("write")
      .build();
    var u4 = User.withUsername("jhon")
      .password(passwordEncoder().encode("password"))
      .roles("ADMIN") // equivalent with an authority named ROLE_ADMIN
      .build();
    // .authorities("ROLE_ADMIN")
    // technically role and authority are the same thing, both use GrantedAuthority Interface
    // best practice: use one of them, roles Or authority.

    uds.createUser(u1);
    uds.createUser(u2);
    uds.createUser(u3);

    return uds;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
