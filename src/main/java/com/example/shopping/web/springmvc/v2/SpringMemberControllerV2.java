package com.example.shopping.web.springmvc.v2;

import com.example.shopping.servlet.domain.member.Member;
import com.example.shopping.servlet.domain.member.MemberRepository;
import com.example.shopping.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/springmvc/v2/members") // /springmvc/v2/members 이 중복되니깐 클래스 레벨과 메서드 레벨 조합으로 공통적인 코드를 줄일수있다.
public class SpringMemberControllerV2 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    // @RequestMapping은 메서드 단위로 할 수 있다. 어느정도 연관있는 Controller 안에 넣어야 한다.

    @RequestMapping("/new-form") // 요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출된다.
    public ModelAndView newForm() {
        return new ModelAndView("new-form");    // ModelAndView : 모델과 뷰 정보를 담아서 반환하면 된다.
    }
    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }
    @RequestMapping
    public ModelAndView members() {

        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);
        return mv;
    }

}
