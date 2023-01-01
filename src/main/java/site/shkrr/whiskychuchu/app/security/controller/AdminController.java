package site.shkrr.whiskychuchu.app.security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.*;
import site.shkrr.whiskychuchu.app.rank.whisky.service.WhiskyService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public  RedirectView adminWhiskysDetailUpdate(@Valid AdminWhiskyDetailReq req, BindingResult result, @RequestParam("file")  MultipartFile file, @PathVariable Long id){
    if(result.hasErrors()){
        log.info("에러발생");
        return new RedirectView("/admin/whisky/"+id);
    }
    whiskyService.updateWhisky(req,file);
    return new RedirectView("/admin/whiskys");
    }

    @DeleteMapping("/whisky/{id}")
    @ResponseBody
    public ResponseEntity adminWhiskyDelete(@PathVariable Long id) {
        whiskyService.deleteAdminWhisky(id);

        HashMap<String,String> body=new HashMap<>();
        body.put("url","/admin/whiskys");
        return new ResponseEntity<Map>(body, HttpStatus.OK);
    }

    @GetMapping("/ownerWhisky")
    public String ownerWhisky(Model model){
        List<AdminOwnerWhisky>adminOwnerWhiskyList=whiskyService.getAdminOwnerWhisky();
        model.addAttribute("whiskyList",adminOwnerWhiskyList);
        return "admin/ownerWhisky";
    }
    @PostMapping("/ownerWhisky")
    @ResponseBody
    public ResponseEntity ownerWhiskyUpdate(@RequestBody List<AdminOwnerWhiskyReq> list, Model model) throws URISyntaxException {
            whiskyService.updateOwnerWhisky(list);
            URI redirectUri = new URI("/admin/whiskys");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(redirectUri);
            return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }
}
