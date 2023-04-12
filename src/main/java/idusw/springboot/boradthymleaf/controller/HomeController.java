package idusw.springboot.boradthymleaf.controller;

import idusw.springboot.boradthymleaf.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    MemoService memoService;  // MemoService 인터페이스의 구현체를 필드 주입

    @GetMapping("/")
    public String goHome() {
        return "sb-admin-2/index";
    }
    @GetMapping("/buttons")
    public String goButton() { return "sb-admin-2/buttons"; }
    @GetMapping("/cards")
    public String goCard() { return "adb-admin-2/cards"; }

}
