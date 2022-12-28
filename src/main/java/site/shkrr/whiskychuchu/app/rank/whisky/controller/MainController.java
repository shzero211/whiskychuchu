package site.shkrr.whiskychuchu.app.rank.whisky.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.WhiskyMainRankDto;
import site.shkrr.whiskychuchu.app.rank.whisky.service.WhiskyService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final WhiskyService whiskyService;
    @GetMapping("/")
    public String main(Model model){
        List<WhiskyMainRankDto> mainRankDtoList=whiskyService.getMainRankListOrderBy("salerank");
        model.addAttribute("whiskyMainRankList",mainRankDtoList);
        return "rank/main";
    }
}
