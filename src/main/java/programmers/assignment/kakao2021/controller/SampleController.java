package programmers.assignment.kakao2021.controller;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * 네이밍 분류기준
 * - Controller : 외부와 커뮤니케이션이 있을때, 데이터 뿐만 아니라 view 와 vc 를 갖고 있을 수도 있다.
 * - Handler : 특정 객체 내부에서만 폐쇄적으로 사용 될 때, closure 에 가까우나 delegate 구현 여부에서 차이가 있따.
 * - Manager : singleton 형식으로 사용될 때
 */
class SampleController {
    public SampleController() {
        System.out.println("Hi");
    }

    public void exec(String arg){
        System.out.println("실행됨" + arg);
    }
}
