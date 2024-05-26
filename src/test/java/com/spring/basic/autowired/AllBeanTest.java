package com.spring.basic.autowired;

import com.spring.basic.AutoAppConfig;
import com.spring.basic.discount.DiscountPolicy;
import com.spring.basic.discount.RateDiscountPolicy;
import com.spring.basic.member.Grade;
import com.spring.basic.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/*
하나의 타입의 속한 빈을 모두 조회하는 방법
ex) 클라이언트가 할인을 선택할 수 있는 경우
   할인에 해당하는 모든 빈 필요.
   DiscountPolicy 타입을 DiscountService의 생성자 주입을 통해 DiscountPolicy type의 빈을 모두 주입.
   discount메서드를 통해 discountCode로 들어온 bean을 map을 통해 조회하고 결과 리턴.
 */
public class AllBeanTest {
    @Test
    void findAllBean(){
        ApplicationContext ac =
                new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        assertThat(discountService).isInstanceOf(DiscountService.class);

        int rateDiscount = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscount).isEqualTo(2000);
    }

    static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
