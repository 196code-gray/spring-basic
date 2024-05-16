package com.spring.basic.singleton;

import com.spring.basic.AppConfig;
import com.spring.basic.member.MemberRepository;
import com.spring.basic.member.MemberService;
import com.spring.basic.member.MemberServiceImpl;
import com.spring.basic.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    /*
    현재 AppConfig 파일에서는 객체를 호출할 때마다 new로 새로 생성한 인스턴스를 반환.
    그런데 테스트 결과 인스턴스를 한번만 생성하여 싱글톤을 유지하는 모습.
    어떻게???

    spring은 AppConfig를 직접 등록하는 것이 아닌 이름이 AppConfig인 CGLIB라는 바이트코드 라이브러리를 통해
    다른 객체를 생성하여 빈으로 등록.
    따라서 해당 클래스를 조회했을 때 이미 빈에 등록이 되어 있다면 해당 빈을 리턴.
    아니라면 내가 작성한 new 코드가 실행되어 새로 생성된 빈을 등록.

    @Configuration 애너테이션을 붙이지 않으면 CGLIB으로 클래스 생성 x
    CGLIB으로 클래스를 생성하지 않을 시 싱글톤이 깨질 수 있음.
    실제 자바 코드가 동작. 객체가 호출될 때마다 new로 객체 생성.
    테스트가 실패.
     */
    @Test
    @DisplayName("현재 AppConfig 객체가 싱글톤인지 확인하는 테스트")
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository repo = ac.getBean("memberRepository", MemberRepository.class);

        // 테스트 용도로 구현체 직접 지정. 원래는 좋지 않은 방법.
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService1 -> 2 = " + memberRepository1);
        System.out.println(memberRepository2);
        System.out.println(repo);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(repo);
    }

    @Test
    void configurationDepp(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);
        MemberRepository bean1 = ac.getBean(MemberRepository.class);
        System.out.println(bean.getClass());
        System.out.println(bean1.getClass());
    }
}
