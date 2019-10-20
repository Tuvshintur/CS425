package edu.mum.cs.cs425.demowebapps.elibrary.elibrary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/elibrary", "/elibrary/home"})
    public String displayHomePage() {
        return "home/index";
    }
}
