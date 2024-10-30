package spring.sec.bootcamp.e11.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 http://localhost:8080/oauth2/authorize?response_type=code&client_id=myclient&scope=openid&redirect_uri=https://springone.io/authorized&code_challenge=_JVJ_MQOXqihvQFK_XA5cQLz1dRQtJ85dPXANeDLXUA&code_challenge_method=S256
 */
@SpringBootApplication
public class E12AuthServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(E12AuthServerApplication.class, args);
  }

}

