package site.shkrr.whiskychuchu.app.rank.whisky.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.shkrr.whiskychuchu.app.rank.whisky.repository.dto.WhiskyMainRankDto;
import site.shkrr.whiskychuchu.app.rank.whisky.service.WhiskyService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rank")
public class WhiskyController {
    private final WhiskyService whiskyService;

    @GetMapping("/main")
    public String main(Model model){
        List<WhiskyMainRankDto> mainRankDtoList=whiskyService.getMainRankList();
        model.addAttribute("whiskyMainRankList",mainRankDtoList);
        return "/rank/main";
    }

    @GetMapping("/main/sort")
    public  String main(@RequestParam(defaultValue = "salerank") String field, Model model){
        List<WhiskyMainRankDto> mainRankDtoList=whiskyService.getMainRankListOrderBy(field);
        model.addAttribute("whiskyMainRankList",mainRankDtoList);
        return "/rank/main :: #mainTable";
    }

}
