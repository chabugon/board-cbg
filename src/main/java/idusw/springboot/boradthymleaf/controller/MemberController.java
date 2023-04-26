package idusw.springboot.boradthymleaf.controller;


import idusw.springboot.boradthymleaf.domain.Member;
import idusw.springboot.boradthymleaf.service.MemberService;
import idusw.springboot.boradthymleaf.service.MemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    MemberService memberService;
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String getLoginform(){ //
        return "/members/login";
    }
    @PostMapping("/login")
    public String postLogin(){ //
            return "redirect:/";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){ // form 요청 -> view (template engine)
        model.addAttribute("member", Member.builder());
        return "/members/register";
    }
    @PostMapping("/register")
    public String createMember(@ModelAttribute("member") Member member, Model model){ //
        System.out.println(member);
        member.setSeq(1L);
        if(memberService.create(member) > 0 ) // 정상적으로 레코드의 변화가 발생하는 경우 영향받은 레코드 수를 반환
            return "redirect:/";
        else
            return "/main/error";
    }

    @GetMapping("/update")
    public String getUpdaterForm(){ // form 요청 -> view(template engine)
        return "/members/update";
    }
    @PutMapping("/update")
    public String updateMember(){ //
        return "redirect:/";
    }

    @DeleteMapping("/delete")
    public String deleteMember(){ //
        return "redirect:/";
    }

    @GetMapping("/forgot") // 조회 read
    public String getForgotForm(){ //
        return "/members/forgot-password";
    }

    @PostMapping("/forgot") // create ,update, delete
    public String getForgotPasswordForm(){  // 비밀번호(갱신) -> service -> repository -> service -> controller
        return "redirect:/"; // 루트로 이동
    }
}
