package com.example.shopping;

import com.example.shopping.member.MemberRepository;
import com.example.shopping.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 컴포넌트 스캔 사용하면 @Configuration 붙은 설정 정보도 자동 등록되기 때문에 제외해준다. (excludeFilters)사용
        // 기존 예제 코드를 남기고 유지하기위함.
)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
