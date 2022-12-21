package site.shkrr.whiskychuchu.app.global.errorcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
@Controller
@Slf4j
public class WebErrorController implements ErrorController {
    @GetMapping("/error")
    public String handleError(HttpServletRequest request){
        Object status=request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status!=null){
            int statusCode=Integer.valueOf(status.toString());
            log.info(statusCode+"에러 발생");
            if(statusCode== HttpStatus.NOT_FOUND.value()){
                return "rank/error/404error";
            }
        }
        return"rank/error/error";
    }
}
