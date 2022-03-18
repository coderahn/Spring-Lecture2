package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.logdemo.LogDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoContoller {
    private final LogDemoService logDemoService;

    /**
     * 단순히 DI받으면 에러발생 -> request스코프 컴포넌트라서 HTTP요청시 빈생성 되는데 스프링 컨테이너가 올라갈 때는 주입받을 빈이 없음
     * 이를 해결하기 위해 ObjectProvider를 사용하여 HTTP요청시 DL로 찾아서 주입
     * 이것도 귀찮기 때문에 프록시 기능 사용
     */
    private final MyLogger myLogger;
    //private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass());
        //MyLogger myLogger = myLoggerProvider.getObject(); //프록시모드를 사용했기 때문에 myLogger에 프록시가 DI됨
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
