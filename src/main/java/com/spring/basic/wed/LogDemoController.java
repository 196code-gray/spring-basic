package com.spring.basic.wed;

import com.spring.basic.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
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

provider를 사용하면 의존관계를 주입할 때는 MyLogger 가 아닌 MyLogger를 찾는 ObjectProvider를 의존관계로 주입 받고,
getObject()를 사용했을 때 스프링 컨테이너에서 MyLogger bean 생성 후 반환해줌.

또 다른 방법으로는 proxy를 사용
의존관계 주입시 CGLIB 라이브러리를 이용해서 MyLogger를 상속받은 proxy(가짜 객체)를 주입.
proxy의 메서드 호출 시 proxy 메서드가 호출 되고 내부에 MyLogger 메서드를 찾을 수 있는 로직 존재.
해당 로직으로 스프링 컨테이너에서 진짜 MyLogger의 bean을 찾고 메서드를 실행.
만약 컨테이너에 없으면 생성 후 컨테이너에 등로하고 메서드 실행.
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
