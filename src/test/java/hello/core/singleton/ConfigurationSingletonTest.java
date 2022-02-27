package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    //AppConfig에서 memberRepository()를 2번 호출함에도 불구하고 참조값이 같다 -> 스프링컨테이너가 싱글톤으로 유지해준다.
    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        System.out.println("memberService -> MemberRepository: " + memberService.getMemberRepository());
        System.out.println("orderService -> MemberRepository: " + orderService.getMemberRepository());
        System.out.println("MemberRepository: " + memberRepository);


        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig appConfig = ac.getBean(AppConfig.class);

        /**
         * AppConfig.class는 @Configuration을 통해 CGLIB의 바이트코드 조작을 받음
         * 실제 주소값은 hello.core.AppConfig$$EnhancerBySpringCGLIB$$4b5ffe77@3a45c42a로 나옴
         * appConfig를 상속한 클래스가 실제로 등록된다.
         */
        System.out.println("AppConfig : " + appConfig);
    }
}
