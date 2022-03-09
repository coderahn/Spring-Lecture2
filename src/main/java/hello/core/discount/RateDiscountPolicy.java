package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy") //이런경우 문자라서 컴파일 때 에러체크를 못한다 -> 어노테이션으로 써보자(아래확인)
@MainDiscountPolicy
//@Primary //같은 타입 빈이 여러개일때 주입 우선권을 가질 수 있다.
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent/100;
        } else {
            return 0;
        }
    }
}
