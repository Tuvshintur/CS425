package mum.edu.cs.cs425.etdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public String index() {
        return "home/index";
    }
}
