package com.example.shopping.beanfind;

import com.example.shopping.member.MemberRepository;
import com.example.shopping.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {
    // 스프링 빈 조회 둘 이상일때

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SamBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        // 위의 name에서 이름 지정해서 꺼냄.
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType(){
         Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
         for (String key : beansOfType.keySet()) {
             System.out.println("key = " + key + "value = "+ beansOfType.get(key));
         }
        System.out.println("beansOfType = " + beansOfType);
         assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SamBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
