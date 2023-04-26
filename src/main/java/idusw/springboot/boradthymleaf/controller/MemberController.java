package idusw.springboot.boradthymleaf.controller;


import idusw.springboot.boradthymleaf.domain.Member;
import idusw.springboot.boradthymleaf.domain.Memo;
import idusw.springboot.boradthymleaf.service.MemberService;
import idusw.springboot.boradthymleaf.service.MemoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public String getLoginform(Model model) {
        model.addAttribute("member", Member.builder().build()); // email / pw 전달을 위한 객체
        return "/members/login"; // view : template engine - thymeleaf .html
    }
    @PostMapping("/login")
    public String loginMember(@ModelAttribute("member") Member member, HttpServletRequest request) { // 로그인 처리 -> service -> repository -> service -> controller
        Member result= null;
        if((result = memberService.login(member)) != null ) {// 정상적으로 레코드의 변화가 발생하는 경우 영향받는 레코드 수를 반환
            HttpSession session = request.getSession();
            session.setAttribute("sseion", result);
            return "redirect:/";
        }
        else
            return "/main/error";
    }



    @GetMapping("/register")
    public String getRegisterForm(Model model) { // form 요청 -> view (template engine)
        model.addAttribute("member", Member.builder().build());
        return "/members/register";
    }
    @PostMapping("/register")
    public String createMember(@ModelAttribute("member") Member member, Model model) { // 등록 처리 -> service -> repository -> service -> controller
        if (memberService.create(member) > 0) // 정상적으로 레코드의 변화가 발생하는 경우 영향받는 레코드 수를 반환
            return "redirect:/";
        else
            return "/main/error";
    }

    @GetMapping("/{seq}")
    public String getMember(@PathVariable("seq") Long seq, Model model){
        Member result = new Member(); // 반환
        Member m = new Member(); // 매개변수로 전달
        m.setSeq(seq);
        result = memberService.read(m);
        // memberService가 memberRepository에게 전달
        // memberRepository는 JqaRepository가 인터페이스의 구현체를 활용할 수 있음
        model.addAttribute("member", result);
        return "/members/detail";
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
