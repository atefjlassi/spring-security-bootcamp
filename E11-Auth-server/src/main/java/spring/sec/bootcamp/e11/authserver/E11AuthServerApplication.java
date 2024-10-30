package spring.sec.bootcamp.e11.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 http://localhost:8080/oauth2/authorize?response_type=code&client_id=myclient&scope=openid&redirect_uri=https://springone.io/authorized&code_challenge=_JVJ_MQOXqihvQFK_XA5cQLz1dRQtJ85dPXANeDLXUA&code_challenge_method=S256
 http://localhost:8080/oauth2/token?client_id=client&redirect_uri=https://springone.io/authorized&grant_type=authorization_code&code=dWlJMGpGlUAPz0sRU1y8suXDyWejo0_B4-WrLP-ks5kSlcdvlGG-u1OxOORvvpm7IMJaC_lMqzTX2Oh6AKHGOb2J4-Hp6PVPvGjLeUQMnWzz6h3Xyy1D0S6czbiTeU8f&code_verifier=qPsH306-ZDDaOE8DFzVn05TkN3ZZoVmI_6x4LsVglQI

 */
@SpringBootApplication
public class E11AuthServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(E11AuthServerApplication.class, args);
  }

}

