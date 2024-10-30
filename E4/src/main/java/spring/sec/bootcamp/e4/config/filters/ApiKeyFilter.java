package spring.sec.bootcamp.e4.config.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import spring.sec.bootcamp.e4.config.authentications.ApiKeyAuthentication;
import spring.sec.bootcamp.e4.config.manager.CustomAuthenticationManger;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

    var requestKey = request.getHeader("x-api-key");
    if ("null".equals(requestKey) || requestKey == null) {
      filterChain.doFilter(request, response);
    }

    CustomAuthenticationManger manager = new CustomAuthenticationManger(requestKey);
    var authObject = new ApiKeyAuthentication(requestKey);

    try {
      Authentication authentication = manager.authenticate(authObject);
      if (authentication.isAuthenticated()) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
      } else {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      }
    } catch (AuthenticationException e) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }



  }
}
