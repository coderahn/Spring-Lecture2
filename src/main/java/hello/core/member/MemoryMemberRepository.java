package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{
    //동시성 이슈가 있을 수 있기 때문에 원래는 ConcurrentHashMap을 사용해야 함
    private static Map<Long, Member> store = new HashMap<>();

    //오버라이드 단축키 : alt + insert
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
