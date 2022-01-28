package hello.core.member;

public class MemberServiceImpl implements MemberService{
    //다형성을 활용하였지만 구현체를 클라이언트(service)가 직접 설정해주므로 OCP 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
