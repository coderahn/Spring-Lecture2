package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    /**
     * 생성자 주입을 해라!
     * 1.생성자 주입을 해야 컴파일시 체크가 된다.
     * 2.생성자 주입은 final 키워드를 넣을 수 있다. 생성자에서만(or 초기에만) 값을 넣어줄 수 있다.(불변유지가능)
     * 3.final을 넣으면 생성자 넣는 코드 없을 때 컴파일 에러 발생시킨다.
     * 컴파일시 에러체크 되는 코드가 좋은 코드이다.
     */
    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
    }
}