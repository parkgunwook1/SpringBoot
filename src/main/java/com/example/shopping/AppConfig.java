package com.example.shopping;

import com.example.shopping.discount.DiscountPolicy;
import com.example.shopping.discount.FixDiscountPolicy;
import com.example.shopping.member.MemberRepository;
import com.example.shopping.member.MemberService;
import com.example.shopping.member.MemberServiceImpl;
import com.example.shopping.member.MemoryMemberRepository;
import com.example.shopping.order.OrderService;
import com.example.shopping.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* AppConfig 는 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성" 한다.
* - MemberServiceImpl ,. MemoryMemberRepository, OrderServiceImpl , FixDiscountPolicy
*
* AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 "생성자를 통해서 주입(연결)" 해준다.
* - MemberServiceImpl -> MemoryMemberRepository
* - OrderServiceImpl -> MemoryMemberRepository, FixDiscountPolicy
* */

// AppConfig를 보면 역할과 구현 클래스가 한눈에 들어온다. 애플리케이션 전체 구성이 어떻게 되었는지 빠르게 파악할 수 있다.

// AppConfig의 자바코드를 보면 각각 2번 new MemoryMemberRepository 호출해서 다른 인스턴스가 생성되어야 하는데,
// 스프링 컨테이너는 싱글톤을 검증해준다는데 과연 싱글톤이 깨질것인가?
// memberService -> new memberRepository

//call AppConfig.memberService
//call AppConfig.memberRepository
//call AppConfig.orderService

// @Bean이 붙은 메서드마다 이미스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
// 덕분에 싱글톤이 보장되는 것이다.

// @Configuration을 적용하지 않고 @Bean만 적용하면 어떻게 될까?
// @Configuration을 붙이면 바이트코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을 보장하지만, 만약 @Bean만 적용하면 어떻게 될까?
// 싱글턴이 깨진다.

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
        /*
        * - appConfig 객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달한다.
        * - 클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection)
        *   우리말로 의존관계 주입 또는 의존성 주입이라 한다.
        * */
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        // AppConfig를 사용해서 OrderService를 조회하면 OrderServiceImpl을 리턴해준다.
        // OrderServiceImpl에는 MemoryMemberRepository, discountPolicy가 들어간다.

    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
        // 할인 정책을 바꾸고 싶으면 FixDiscountPolicy를 RateDiscountPolicy() 로 변경만 해주면 된다.
    }

}
