package com.spring.basic.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
/*
싱글톤 사용시 주의점
공유 필드는 사용하지 않는게 제일 좋음.
아래 예제는 statefulService 에 price라는 싱글톤 객체를 생성.
A 사용자가 10000원을 주문, B 사용자가 20000원을 주문.
같은 객체를 공유하므로 A가 주문한 10000원은 20000원으로 덮어씌워짐.

- 해결법
StatefulService price 객체를 지역변수로 사용하게 하면 됨.
공유 x
 */
    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        statefulService1.order("userA", 10000);
        statefulService2.order("userB", 20000);

        int p = statefulService1.getPrice();

        Assertions.assertThat(p).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}