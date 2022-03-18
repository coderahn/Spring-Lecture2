package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * 프록시 모드로 CGLIB 바이트코드 기술로 가짜 프록시를 만들어 주입시켜둠
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    /**
     * scope가 singleton인 경우 : 최초 빈 생성시에 init이 됨 -> 요청때마다 같은 uuid로 확인되어 어느요청인지 판단 불가
     * scope가 request인 경우 : http요청시에 항상 init이 됨 -> 요청때마다 새로운 uuid로 확인되어 어느요청인지 판단 가능
     */
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}