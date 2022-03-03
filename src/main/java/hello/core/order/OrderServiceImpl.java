package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    //OCP, DIP원칙을 지키도록 외부에서 의존관계 주입
    /**
     * 필드인젝션은 사용하는 것을 권장하지 않는다.
     * 예를들어 외부 테스트시, memberRepository를 테스트 DB등으로 바꿔 테스트할 때 바꿀 방법이 없다.
     * 결국 setter를 만들어서 테스트를 해야 한다.
     * 또한 스프링 테스트가 아닌 순수 자바로 테스트할 때에 주입되지 않는다. 
     */
    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("setMemberRepository 세터 주입");
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("setDiscountPolicy 세터 주입");
//        this.discountPolicy = discountPolicy;
//    }

    //생성자가 1개면 @Autowired를 생략해도 된다.
    //@Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1.OrderServiceImpl 생성자 주입");

        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //OrderService는 Member, Discount와 관련된 기능을 모른다(SRP(단일책임원칙) 잘 지켜진 설계)
        Member member = memberRepository.findById(memberId); //회원 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); //할인 적용

        return new Order(memberId, itemName, itemPrice, discountPrice); //주문 결과 반환
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
