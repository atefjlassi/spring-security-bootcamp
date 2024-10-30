package spring.sec.bootcamp.e7.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController {


  @GetMapping("/demo1")
  @PreAuthorize("hasAuthority('read')")
  public String demo1() {
    return "Demo 1";
  }

  @GetMapping("/demo2")
  @PreAuthorize("hasAnyAuthority('read', 'write')")
  public String demo2() {
    return "Demo 2";
  }

  /**
   * using method level security, you can access to the data in the request and use it for filtering etc.
   */
  @GetMapping("/demo3/{smth}")
  @PreAuthorize("""
    (#something == authentication.name) or 
    hasAnyAuthority('read', 'write')
    """) // authentication from securityContext
  public String demo3(@PathVariable("smth") String something) {
    var a = SecurityContextHolder.getContext().getAuthentication();
    log.info("authorities : {}", a.getAuthorities());
    return "Demo 3";
  }

  @GetMapping("/demo4/{smth}")
  @PreAuthorize("@accessControlEvaluator.condition()") // authentication from securityContext
  public String demo4(@PathVariable("smth") String something) {
    return "Demo 4";
  }

  /**
   * don't use postauthorize with request that changing data. eg of use: use it if the return decision if based on data
   * that returned in both cases the method will be executed never use postAuthorize with method that change data
   */
  @GetMapping("/demo5")
  @PostAuthorize("returnObject != 'DEMO 5'") // authentication from securityContext
  public String demo5() {
    System.out.println(":)");
    return "DEMO 9";
  }

  /**
   * PreFilter / PostFilter: restrict the access based on some collections of data
   */
  @GetMapping("/demo6")
  @PreFilter("filterObject.contains('a')")
  public String demo6(@RequestBody List<String> values) {
    System.out.println("values: " + values);
    return "Demo 6";
  }

  // the returned type must be either a collection or an array
  @GetMapping("/demo7")
  @PostFilter("filterObject.contains('a')")
  public List<String> demo7() {
    var list = new ArrayList<String>();
    list.add("abc");
    list.add("wert");
    list.add("posi");
    return list;
  }

}
