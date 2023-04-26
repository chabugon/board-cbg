package idusw.springboot.boradthymleaf;

import idusw.springboot.boradthymleaf.domain.Member;
import idusw.springboot.boradthymleaf.domain.Memo;
import idusw.springboot.boradthymleaf.service.MemberService;
import idusw.springboot.boradthymleaf.service.MemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

import java.awt.*;

@SpringBootTest
class BoradThymleafApplicationTests {
    @Autowired
    MemberService memberService;
    @Autowired
    MemoService memoService;

    @Test
    void createMember() {
        Member member = Member.builder()
                .email("11@11.com")
                .name("11")
                .pw("11")
                .build();
        if(memberService.create(member)>0)
            System.out.println("등록성공");
        else
            System.out.println("등록 실패");

        }

}
