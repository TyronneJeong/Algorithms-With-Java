package programmers.assignment.exercise;

import programmers.assignment.exercise.javanet.InOutStream;
import programmers.assignment.exercise.javanet.JavaNet;

public class Exercise {
    public static void main(String[] args) {
        // 자바 서버 소켓
        JavaNet javaNet = new JavaNet();
        javaNet.exec();
        
        // 입출력 스트림
        InOutStream inOutStream = new InOutStream();
        // inOutStream.exec();
    }



    /**
     * 과제
     * 1. http 호출과 응답을 할 수 있는 프로그램을 개발 한다.
     *    ㄴ TCP, HTTP 통신이 가능 하다.
     *       ㄴ 해당 통신 API 를 제공하는 Java.net 패키지에 대해 자세히 알아 보자.
     *       ㄴ JSON, XML 파서에 대한 부분도 궁금하다.
     *       ㄴ 응답값, 리퀘스트 헤더 및 리스폰즈 헤더에 대한 정의도 궁금하다.
     *       ㄴ 트랜잭션에 대해서도 명확한 정의를 정해두고 싶다.
     *       ㄴ 자바에서는 SSL 및 SNI 접속에 대해서도 지원하는 패키지가 있다.
     *
     *
     *
     *    ㄴ WebSocket 은 서블릿 위에서 동작하는 듯 하다.
     *    ㄴ InputStream 에 대해 조금 더 상세하게 확인할 필요가 있다.
     *    ㄴ REST API 구현이 가능한지 확인이 필요하다.
     *
     * 2. 이를 테스트 할 수 있는 프로그램을 개발 한다.
     *    ㄴ JUnit 사용이 안된다면 테스트 프로그램은 어떻게 개발 해야 할까?
     *       ㄴ 김영한 센세의 테스트 케이스 정도만 구현 하도록 하자.
     *
     *    ㄴ 단순히 입력값과 기댓값을 확인하여 오류를 검출 하는 식으로 작성하면 될까?
     *       ㄴ 김영한 센세의 테스트 케이스 작성을 확인 하자.
     */

}
