package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//implements InitializingBean, DisposableBean
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message){
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    /**
     * <초기화방법1>
     * InitializingBean 인터페이스 의존시 오버라이드해야하는 메소드
     * 싱글톤빈 기준으로, 빈객체생성 및 의존관계주입이 완료된 후 호출
     * @throws Exception
     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NewtworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }

    /**
     * <클로즈방법1>
     * DisposableBean 인터페이스 의존시 오버라이드해야하는 메소드
     * 싱글톤빈 기준으로, 스프링컨테이너가 내려갈 때 호출
     * @throws Exception
     */
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NewtworkClient.destroy");
//        disconnect();
//    }

    /**
     * <초기화방법2>
     * 메소드명 자유자재로 선택 가능(스프링 인터페이스 의존 안하는 장점도 있음)
     * @Bean(initMethod="init")으로 주면 작동
     */
//    public void init() {
//        System.out.println("NewtworkClient.init");
//        connect();
//        call("초기화 연결 메시지");
//    }

    /**
     * <클로즈방법2>
     * 메소드명 자유자재로 선택 가능(스프링 인터페이스 의존 안하는 장점도 있음)
     * @Bean(destroyMethod="close")으로 주면 작동
     */
//    public void close() {
//        System.out.println("NewtworkClient.destroy");
//        disconnect();
//    }

    /**
     * <초기화방법3 - 추천>
     * javax에서 지원하여 스프링에 자유로움
     * 외부라이브러리에는 사용못하니 방법2사용하자
     * 깔 끔 !
     */
    @PostConstruct
    public void init() {
        System.out.println("NewtworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    /**
     * <클로즈방법3 - 추천>
     * javax에서 지원하여 스프링에 자유로움
     * 외부라이브러리에는 사용못하니 방법2사용하자
     * 깔 끔 !
     */
    @PreDestroy
    public void close() {
        System.out.println("NewtworkClient.destroy");
        disconnect();
    }
}
