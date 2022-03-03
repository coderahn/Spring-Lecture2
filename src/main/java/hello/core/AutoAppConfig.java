package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 컴포넌트는 기본적으로 소속 상위패키지(hello.core)로 되어있으나 basePackages로 변경 가능
 * @Configuration이 붙은 테스트 코드 등이 몇 개 있기 때문에 제외시킨다. 빈이 중복 등록 등을 방지한다.
 */
@Configuration
@ComponentScan(
        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
