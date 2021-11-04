package programmers.assignment.exercise.javanet;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * [Java 와 스트링]
 * 자바는 시스템 내부적으로 처리될떄에는 인메모리상 UTF-16FE 인코딩 문자열로 저장하고
 * 송수신시 객체 직렬화를 위해서는 modified UTF-8을
 * 문자열의 입/출력을 위해서는 운영체제의 기본 인코딩 값으로 문자열을 인코딩 한다.
 *
 * [UTF-8] 은 가변형 3바이트 문자셋
 * [UTF-16]은 거의 모든 문자가 2바이트로 구성된다. 4바이트 구성의 경우 Surrogate 로 검색
 */
public class InOutStream {
    private InputStream is = null;
    private InputStreamReader isr = null;
    private BufferedReader br = null;
    private BufferedWriter bw = null;

    public void exec() {
        /**
         * 0. Stream이란?
         * - 파일 데이터(시작과 끝이 있는 데이터 스트림.), HTTP 응답 데이터, 키보드 입력 등 입력 소스를 대상 소스로 연결 시켜 주는 파이프라인과 같은 객체.
         * - 스트림 선언 만으로는 아무런 액션이 없다. 이후 값이 전달되는건 오퍼레이션 호출에 의해 일어난다.
         * - 아래는 시스템으로 부터 받는 입력 소스에 스트림이 할당 된 모습.
         */
        is = System.in;

        /**
         * 1. InputStream 과 System.in
         * - System.in 은 InputStream 의 정적 항목이다.
         * - inputeStream.read(); 명령이 있을때 데이터를 읽어 들인다.
         * - 1바이트 단위로 데이터를 전송하고 읽어 들인다. 이때 자료형은 int 형으로 보관된다.
         * - 이렇게 바이트 단위로 주고 받기 때문에 바이트 스트림 [byte stream] 이라고도 한다.
         */
        System.out.println("Input Stream");
        try {
            System.out.println("available before >> " +is.available()); // remained count - 0
            System.out.println((char)is.read()); // byte code -> char
            System.out.println("available after >> " +is.available()); // remained count
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 2. Scanner(System.in)과 InputStreamReader()
         * - 1바이트 단위의 바이트 스트림을 읽어 문자로 정리하기 위해 Reader 객체 탄생
         * - 2바이트 이상의 문자들을 정상적으로 인식 할 수 있다.
         * - 바이트스트림 <-> 문자스트림으로 변환해주는 가교 역할을 하는 객체
         */
        System.out.println("\n\nScanner");
        try {
            char[] cArr = new char[10];
            isr = new InputStreamReader(System.in);
            System.out.println(isr.ready()); // 입력값이 존재하는 경우 true
            isr.read(); // read count 가 증가할 수록 커서가 점점 뒤로 이동 한다. Iterator 적인 특성이 있는 듯.

            isr.read(cArr);
            for (char c:cArr) {
                System.out.print(c);
            }

            /* input stream reader 로 읽어도 아직 캐스팅이 필요한 상태 */
            // - char or charbuffer 에 대입이 가능
            // isr = new InputStreamReader(is);
            System.out.println((char)isr.read());
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 3. BufferedReader()
         * - 문자열 단위로 버퍼링 하여 이를 리턴 한다.
         * - DEFAULT Buffer size = 8192 이다.
         */
        System.out.println("\n\nBuffered Reader");
        try {
            br = new BufferedReader(isr);
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 4. 문자열을 직접 바이트 스트림으로 변환 후 재생성 예제.
         */
        System.out.println("\n\nExample");
        String sample = "Hello!! World!!";
        
        // byte stream 생성
        ByteArrayInputStream bais = new ByteArrayInputStream(sample.getBytes(StandardCharsets.UTF_8)); // byte stream 생성

        br = new BufferedReader(new InputStreamReader(bais));
        String printStr = "";
        try {
            while((printStr = br.readLine()) != null){
                System.out.println(printStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * BufferedWriter
         * - StringBuffer 의 상위 클래스 인듯? 아니네. StringBuffer 는 StringBuilder 의 상속 객체
         * - StringBuffer , StringBuilder 모두 Serializable 을 지원 한다. 하지만 synchronized 는 StringBuffer 만 지원한다.
         * - StringBuilder 가 StringBuffer 에 비해 조금 더 빠르다.
         */
        System.out.println("\n\nBuffered Writer");
        try {
            StringBuffer tsb;
            StringBuilder tsbb;
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
            bw.write(sample);
            bw.newLine();
            bw.write("아직 작성중");
            bw.flush(); // 출력
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 열려진 Stream 은 모두 닫아 주어야 한다.
         */
        try {
            is.close();
            isr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}