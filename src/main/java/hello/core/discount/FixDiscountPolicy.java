package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy") //같은타입 빈2개 이상일 때 전용 이름을 지정해준다. 그리고 주입받을 인자 앞에 @Qualifier("fixDiscountPolicy")를 써준다.
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
