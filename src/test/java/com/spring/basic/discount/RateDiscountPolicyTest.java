package com.spring.basic.discount;

import com.spring.basic.member.Grade;
import com.spring.basic.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 10% 할인 성공 테스트")
    void vip_o(){
        // given
        Member member = new Member(1L, "vip", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("일반 회원 할인 적용 불가 성공 테스트")
    void vip_x(){
        // given
        Member member = new Member(1L, "basic", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}