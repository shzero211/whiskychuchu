package site.shkrr.whiskychuchu.app.security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.shkrr.whiskychuchu.app.rank.whisky.service.WhiskyService;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.AdminWhisky;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.AdminWhiskyDetail;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.AdminWhiskyDetailReq;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
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
    @GetMapping("/whisky/{id}")
    public  String adminWhiskysDetailPage(@PathVariable Long id, Model model){
    AdminWhiskyDetail adminWhiskyDetail=whiskyService.getAdminWhiskyDetail(id);
    model.addAttribute("whisky",adminWhiskyDetail);
    return "admin/whiskyDetail";
    }
    @PostMapping("/whisky/{id}")
    public  String adminWhiskysDetailUpdate(@Valid AdminWhiskyDetailReq req, BindingResult result, @RequestParam("file")  MultipartFile file, @PathVariable Long id , Model model){
    if(result.hasErrors()){
        log.info("에러발생");
        return "admin/whisky/"+id;
    }
    whiskyService.updateWhisky(req,file);
    return "redirect:/admin/whiskys";
    }
}
