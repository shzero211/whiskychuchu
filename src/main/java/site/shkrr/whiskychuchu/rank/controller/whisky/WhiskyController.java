package site.shkrr.whiskychuchu.rank.controller.whisky;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.shkrr.whiskychuchu.rank.controller.dto.WhiskyMainRankDto;
import site.shkrr.whiskychuchu.rank.service.whisky.WhiskyService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rank")
public class WhiskyController {
    private final WhiskyService whiskyService;

    @GetMapping(value={"/**","/main"})
    public String main(Model model){
        List<WhiskyMainRankDto> mainRankDtoList=whiskyService.getMainRankListOrderBy("salerank");
        model.addAttribute("whiskyMainRankList",mainRankDtoList);
        return "rank/main";
    }

    @GetMapping("/main/sort")
    public  String main(@RequestParam(defaultValue = "salerank") String field, Model model){
        List<WhiskyMainRankDto> mainRankDtoList=whiskyService.getMainRankListOrderBy(field);
        model.addAttribute("whiskyMainRankList",mainRankDtoList);
        return "rank/main :: #mainTable";
    }

}
