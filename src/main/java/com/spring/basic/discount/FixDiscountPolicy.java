package com.spring.basic.discount;

import com.spring.basic.member.Grade;
import com.spring.basic.member.Member;

// 고정 금액 할인 구현체
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
