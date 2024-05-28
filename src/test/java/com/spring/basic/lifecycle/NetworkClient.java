package com.spring.basic.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
/*
bean 생명주기 콜백 방법
bean 생성 -> 의존관계 주입 -> 콜백 -> 초기화

1. 인터페이스 사용
     InitializingBean, DisposableBean을 사용해서 빈이 초기화 되었다는 콜백과 빈이 사용종료 되는 콜백을 받을 수 있다.
    하지만 이 방법은 추천되지 않는 방법.
        why?
            일반 spring의 직접적인 class에 의존.
            외부 라이브러리와 에러 발생시 해결 불가.
 */
public class NetworkClient implements InitializingBean, DisposableBean {
    private String url;

    public NetworkClient() {
        System.out.println(url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect(){
        System.out.println("connect = " + url);
    }

    public void call(String name){
        System.out.println("NetworkClient.call = " + url + " message = " + name);
    }

    public void disconnect(){
        System.out.println("close = " + this.url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
