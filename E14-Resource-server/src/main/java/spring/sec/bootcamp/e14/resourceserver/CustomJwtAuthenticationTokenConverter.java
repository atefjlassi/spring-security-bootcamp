package spring.sec.bootcamp.e14.resourceserver;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class CustomJwtAuthenticationTokenConverter implements Converter<Jwt, JwtAuthenticationToken> {

  @Override
  public JwtAuthenticationToken convert(Jwt source) {
    List<String> authorities = (List<String>) source.getClaims().get("authorities");

    JwtAuthenticationToken authenticationObj = new JwtAuthenticationToken(source, authorities.stream().map(
      SimpleGrantedAuthority::new).collect(Collectors.toSet()));
    return authenticationObj;
  }
}
