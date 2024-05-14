package com.spring.basic.singleton;

import com.spring.basic.AppConfig;
import com.spring.basic.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
/*
예전에 직접 생성했던 순수 자바코드는 요청이 들어올 때마다 인스턴스 생성.
요청이 100개 들어오면 100개가 다 다른 인스턴스로 생성이 됨.
비효율적 방식.
 */
    @Test
    @DisplayName("spring 없는 순수한 DI 컨테이너 문제점")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println(memberService1 + ", " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);

    }
/*
싱글톤 패턴을 적용하여 하나의 인스턴스로 사용.
하지만 문제점이 발생
- 싱글톤 구현 위한 많은 코드
- AppConfig 같이 역할 관리자 클래스 없을 경우 DIP 원칙 위반.
- DIP 원칙 위반으로 다른 객체지향 원칙도 위반 가능성 높음
- 공유 객체의 경우 신중하게 생각해야 함.
- 여러곳에서 참조하고 있을 경우 테스트가 어려움.
 */
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println(singletonService1);
        System.out.println(singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너의 싱글톤 구현")
    void springContainer(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println(memberService1 + ", " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
