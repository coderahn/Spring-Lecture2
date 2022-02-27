package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    //다형성을 활용하였지만 구현체를 클라이언트(service)가 직접 설정해주므로 OCP 위반
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    private MemberRepository memberRepository;

    /**
     * @Autowired
     * 기존 AppConfig 설정정보에서는 메소드 호출을 통해 의존관계를 설정해주었다.
     * 그러나 AutoAppConfig를 사용하고 @Component를 사용하면서 그런식의 의존관계 주입은 불가능해졌다.
     * 이럴때 @Autowired를 사용하면 @Autowired 대상의 객체타입과 맞는 등록되어 있는 빈을 주입해준다.
     */
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
