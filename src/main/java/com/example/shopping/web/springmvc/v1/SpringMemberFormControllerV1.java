package com.example.shopping.web.springmvc.v1;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// Controller 스프링이 자동으로 스프링 빈으로 등록한다. (Controller 내부에 @Component가 있어서 컴포넌트 스캔 대상이 된다.)
// 스프링 MVC 에서 애노테이션 기반 컨트롤러로 인식한다. => handler에서 꺼낼 수 있다.
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form") // 요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출된다.
    public ModelAndView process(){
        return new ModelAndView("new-form");    // ModelAndView : 모델과 뷰 정보를 담아서 반환하면 된다.

        /*  RequestMappingHandlerMapping
        *
        *   RequestMappingHandlerMapping 은 스프링 빈 중에서 @RequestMapping 또는 @Controller가
        *   클래스 레벨에 붙어 있는 경우에 매핑 정보로 인식한다.
        * */
    }
}
