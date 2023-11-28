package com.example.shopping.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
         AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonTest.class);

         SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
         SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("SingletonTest.singletonBeanFind");
        System.out.println("SingletonTest.singletonBeanFind");
        assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destory");
        }

    }
}
