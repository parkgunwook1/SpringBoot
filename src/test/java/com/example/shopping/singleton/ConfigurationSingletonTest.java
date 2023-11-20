package com.example.shopping.singleton;

import com.example.shopping.AppConfig;
import com.example.shopping.member.MemberRepository;
import com.example.shopping.member.MemberServiceImpl;
import com.example.shopping.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService" , MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService" , OrderServiceImpl.class);
        MemberRepository memberRepository =  ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println(" memberRepository = " + memberRepository);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        // bean = class com.example.shopping.AppConfig$$SpringCGLIB$$0
        System.out.println("bean = " + bean.getClass());
    }
}
