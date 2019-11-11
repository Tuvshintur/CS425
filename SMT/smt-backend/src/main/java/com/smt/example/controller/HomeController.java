package com.smt.example.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home Controller @author Turuu
 */

@Controller
@Api(tags = "HOME")
public class HomeController {



    /**
     * Mapping
     **/

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/**/{path:[^.]*}")
    public String any() {
        return "forward:/";
    }

}