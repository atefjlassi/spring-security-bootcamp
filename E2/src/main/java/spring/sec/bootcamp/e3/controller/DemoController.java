package spring.sec.bootcamp.e3.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  @GetMapping("/demo")
  public String hello() {
    var u = SecurityContextHolder.getContext().getAuthentication();
    u.getAuthorities().forEach(System.out::println);
    return "Demo";
  }
}
