package site.shkrr.whiskychuchu.app.rank.whisky.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
