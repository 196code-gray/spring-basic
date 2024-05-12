package com.spring.basic;

import com.spring.basic.member.Grade;
import com.spring.basic.member.Member;
import com.spring.basic.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        // 아래와 같이 코드 작성시 파라미터에 있는 환경설정을 가지고 파라미터 안에 있는 @Bean을 컨테이너에 넣어서 관리해줌.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        // 직접 테스트를 위해 두 멤버가 같은지 출력으로 확인(눈으로 직접 확인)
        System.out.println(member.getName());
        System.out.println(findMember.getName());
    }
}
