package test.top.infra.web.servlet.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

  @RequestMapping("/")
  @ResponseBody
  private String greeting() {
    return "Hello World";
  }
}
