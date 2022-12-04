package site.shkrr.whiskychuchu.app.security.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UserController {
    @GetMapping("/login")
    public String loginPage(){
        return "admin/login";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "admin/signup";
    }

    @GetMapping("/main")
    public String adminPage(){
        return "admin/main";
    }
}
