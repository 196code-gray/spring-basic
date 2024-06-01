package com.spring.basic.wed;

import com.spring.basic.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
/*
현재 MyLogger 클래스 scope = request 상태.
그런데 request 상태는 요청이 들어와야 빈이 주입됨.
하지만 스프링 컨테이너가 띄워질 때 의존관계를 맺기 위한 bean 주입에서 에러 발생.
스프링 컨테이너가 띄워질 때는 요청이 들어오지 않으므로 빈 생성 안됨. 그로인해 의존 관계를 못 맺는 것.
이를 해결하기 위해 provider를 사용해보자.
 */
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        myLogger.setRequestURL(requestURI);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return requestURI;
    }
}
