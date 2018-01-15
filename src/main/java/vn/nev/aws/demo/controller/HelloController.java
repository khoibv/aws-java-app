package vn.nev.aws.demo.controller;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hello")
public class HelloController {

  @GetMapping
  public String index(Model model) {

    model.addAttribute("currentDateTime", new Date());
    model.addAttribute("message", "Hello world");

    return "hello/index";
  }


}
