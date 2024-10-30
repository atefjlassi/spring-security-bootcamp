package spring.sec.bootcamp.e7.security;

import org.springframework.stereotype.Component;

@Component
public class AccessControlEvaluator {

  public static boolean condition() {
    // your complex condition evaluator
    return true;
  }

}
