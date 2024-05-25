package com.spring.basic;

import com.spring.basic.member.MemberRepository;
import com.spring.basic.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
/*
현재 AppConfig에 Configuration 애너테이션이 붙어있으므로 궅이 ComponentScan 이 아니여도 Bean에 등록이 됨.
따라서 Configuration 애너테이션은 제외해주겠다는 으미.
 */
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
/*
수동 등록 vs 자동 등록
수동등록이 우선권을 가짐.
그래서 이름이 같은 빈이여도 수동 등록한 것이 빈으로 등록이 됨.
하지만 이런 수동 등록의 경우 여러 개발자가 개발을 진행하면서 꼬일 확률 높음.
spring boot에서는 이렇게 이름이 같은 빈이 있으면 무조건 에러를 발생시키게 변경.

애매한 상황 만들지 않기(명확하지 않은 것 xx). 까먹음. 혼자개발하는 것 아님.
 */
//    @Bean("memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}
