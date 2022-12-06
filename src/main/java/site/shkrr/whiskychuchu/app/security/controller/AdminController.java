package site.shkrr.whiskychuchu.app.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import site.shkrr.whiskychuchu.app.rank.whisky.service.WhiskyService;
import site.shkrr.whiskychuchu.app.rank.whisky.service.dto.AdminWhisky;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final WhiskyService whiskyService;
    //관리자 로그인 페이지로 이동
    @GetMapping("/login")
    public String loginPage(){
        return "admin/login";
    }
    //관리자 회원가입페이지로 이동
    @GetMapping("/signup")
    public String signupPage(){
        return "admin/signup";
    }
    //관리자 메인페이지로 이동
    @GetMapping("/main")
    public String adminMainPage(){
        return "admin/main";
    }

    @GetMapping("/whiskys")
    public  String adminWhiskysPage(Model model){
        List<AdminWhisky> list=whiskyService.getAdminWhiskyList();
        model.addAttribute("whiskyList",list);
        return "admin/whiskys";
    }

}
