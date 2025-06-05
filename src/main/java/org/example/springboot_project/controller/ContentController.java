package org.example.springboot_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ContentController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/admin/home")
    public String adminHome(){
        return "admin";
    }

    @GetMapping("/user/home")
    public String userHome(){
        return "user";
    }

}
