package spring.sec.bootcamp.e16.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.oauth2Client(client -> client.init(httpSecurity));

    httpSecurity.authorizeHttpRequests(authorizedRequest ->
      authorizedRequest.anyRequest().permitAll());
    return httpSecurity.build();
  }


  @Bean
  public OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager(
    ClientRegistrationRepository clientRegistrationRepository,
    OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository
  ) {
    OAuth2AuthorizedClientProvider provider =
      OAuth2AuthorizedClientProviderBuilder.builder()
        .authorizationCode()
        .refreshToken()
        .clientCredentials()
        .build();

    DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager
      = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, auth2AuthorizedClientRepository);
    defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);

    return defaultOAuth2AuthorizedClientManager;
  }


  @Bean
  public ClientRegistrationRepository clientRegistrationRepository() {
    // whey can we have multiple client registration ?
    // because you can have multiple authorization server
    ClientRegistration c1 = ClientRegistration.withRegistrationId("1")
      .clientId("myclient")
      .clientSecret("mysecret")
      // because we use client_credentials we dont have redirect uri
      .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      .tokenUri("http://localhost:8080/oauth2/token")
      .scope(OidcScopes.OPENID)
      .build();
    InMemoryClientRegistrationRepository clientRegistrations = new InMemoryClientRegistrationRepository(c1);

    return clientRegistrations;
  }

}
