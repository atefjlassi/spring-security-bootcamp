package spring.sec.bootcamp.e15.resourceserver.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


  @GetMapping("/demo")
  public String demo() {
    var auth = SecurityContextHolder.getContext().getAuthentication();
    return "demo";
  }
}
