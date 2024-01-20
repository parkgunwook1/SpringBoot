package com.example.shopping.web.springmvc.v3;


import com.example.shopping.servlet.domain.member.Member;
import com.example.shopping.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();


//    @RequestMapping(value = "/new-form", method = RequestMethod.GET) // @ReuqestMapping에 get 방식만 지정.
    @GetMapping("/new-form")    // @RequestMapping은 @GetMapping, @PostMapping 으로 바꿀수도 있다.
    public String newForm() {
        return "new-form";
    }
//    @RequestMapping(value = "/save" , method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(             // @RequestParam("username")은 request.getParameter("username")과 같은 코드라 생각하면 된다.
            @RequestParam("username") String username,
            @RequestParam("age") int age, // 타입캐스팅과 자동변환을 스프링에서 자동으로 해준다.
            Model model) {
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("Member", member);
        return "save-result";
    }
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping

    public String members(Model model ) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }

}
