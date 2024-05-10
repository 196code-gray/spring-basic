package com.spring.basic.discount;

import com.spring.basic.member.Member;

// 상품 할인 역할
public interface DiscountPolicy {
    /**
    * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
