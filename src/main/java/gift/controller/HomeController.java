package gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 웹 페이지 컨트롤러
 * 메인 페이지를 제공합니다.
 */
@Controller
public class HomeController {
    
    /**
     * 메인 페이지를 반환합니다.
     * @return 메인 페이지 뷰
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
