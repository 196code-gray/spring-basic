package com.spring.basic.autowired;

import com.spring.basic.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }
/*
현재 Member는 Bean이 아님.
@Autowired(required = false)라면 자동 주입 대상 없을 시 setter 메서드 호출 자체가 안됨.
@Nullable 애너테이션을 사용하면 호출은 되지만 값이 없으면 null로 반환
Optional 을 사용하면 호출이 되고 객체 없을 시 empty 반환 있으면 Optional로 감싸서 반환
 */
    static class TestBean{
        @Autowired(required = false) // 이거 기본값 true
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1" + noBean1);
        }
        @Autowired
        public void setBean2(@Nullable Member noBean2){
            System.out.println(noBean2);
        }
        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println(noBean3);
        }
    }
}
