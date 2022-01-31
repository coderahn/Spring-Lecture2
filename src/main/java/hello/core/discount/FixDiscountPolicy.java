package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmount = 1000; //1000원 할인

    //코드 이동 : ctrl+shift+방향키
    @Override
    public int discount(Member member, int price) {
        //enum타입은 equals가 아니라 ==으로 쓴다
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
