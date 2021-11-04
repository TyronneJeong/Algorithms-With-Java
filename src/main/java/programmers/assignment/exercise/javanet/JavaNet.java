package programmers.assignment.exercise.javanet;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * [Java.Net 패키지]
 * - 자바 표준 패키지의 Net 단위 패키지에 대해서만 서술 한다.
 * - javax 는 표준 확장 패키지에서 표준 일급 패키지로 승격된 패키지들이므로 표준 패키지에 해당 한다.
 *
 * [인터페이스]
 * - ContentHandlerFactory : URLStreamHandler 의 인터페이스
 * - CookiePolicy : 쿠키를 얻거나 리젝 하는 정책을 관리하는 인터페이스
 * - CookieStore : 쿠키를 저장하는 보관소 인터페이스
 * - DatagramSocketImplFactory : 데이터그램 소켓 인터페이스
 *   ㄴ 데이터그램 : IP계층의 가변길이 패킷, 헤더와 데이터 부분으로 구성, 헤더는 20~60바이트
 *
 * - FileNameMap : 파일명과 MIME type 을 연결시켜주는 맵 인터페이스
 * - SocketImplFactory : 소켓을 구성하기 위한 인터페이스
 * - SocketOption<T> : 소켓의 이름과 옵션을 리턴하는 인터페이스
 * - URLStreamHandlerFactory : URL Stream 프로토콜을 핸들링 하는 인터페이스
 *
 * [클래스]
 * - Authenticator : 인증자 - 네트워크 연결에서 승인을 얻기 위한 사용되는 대표 클래스
 * - CacheRequest : ResponseCache 내부에 자원을 저장하는 채널을 대표하는 클래스.
 * - CacheResponse : ResponseCache 내부에 자원을 조회하기 위한 채널을  대표하는 클래스
 * - ConetnHandler : 컨텐츠 핸들링의 대표 추상 클래스
 * - CookieHandler : Callback 매커니즘을 제공하는 쿠키 핸들러 오브젝트
 * - CookieManager : cookiehandler 의 구현을 제공하는 클래스
 * 
 * - DatagramPacker : 데이터그램 패킷을 대표하는 클래스 -> 데이터그램은 패킷이라는 용어로 대체할 수 있다.
 * - DatagramSocker : 데이터그램 패킷을 수신받기 위한 대표 클래스
 * - DatagramSockerImpl : 데이터그램을 과 멀티캐스트 소켓을 구현한 기본 클래스
 *
 * - HttpCookie : HTTP 쿠키를 대표하는 클래스. 서버와 사용자 간 상태정보를 교환하기위해 사용되는 쿠키
 * - HttpURLConnection : HTTP 특징을 가진 URLConnection
 * - IDN : Internationalized Domain Names (IDNs) 으로 컨버젼 기능을 제공하는 클래스. Unicode <-> ASCII(ACE)
 *
 * - INet4Address : IPv4 주소를 대표하는 클래스
 * - INet6Address : IPv6 주소를 대표하는 클래스
 * - INetAddress : IP 주소를 대표하는 클래스
 * - INetSockerAddress : IPSocketADress 의 구현 클래스(IP Address + Port Number)
 *
 * - InterfaceADdress : Network Interface Address 를 대표하는 클래스
 * - MulticastSocket : 멀티캐스트 데이터 그램 소켓 클래스
 *   ㄴ 멀티캐스트 : 한번의 송신으로 메세지나 정보를 목표한 여러대의 컴퓨터에 전송하는 것.
 *
 * - NetPermission : 다양한 네트워크 권한을 위한 클래스
 * - NetworkInterface : 네트워크 인터페이스 클래스
 * - PasswordAuthentication : 패스워드 인증 데이터를 관리하는 클래스 (superclass. Authenticator)
 *
 *  - Proxy : 프록시 셋팅을 제공하는 클래스
 *  - ProxySelector : 프록시 서버를 선택하는 클래스
 *  - ReponseCache : URLConnectino Cache 를 구현한 클래스
 *  - SecureCacheResponse : TLS 같은 보안 도구를 통해 조회된 캐쉬정보를 관리하는 클래스
 *
 *  - ServerSocket : server socket의 구현 클래스
 *  - Socket : client socket 의 구현 클래스
 *  - SocketAddress : socket Address 를 대표하는 클래스
 *  - SockerImpl : 실제 소켓 클래스의 슈퍼 클래스
 *  - SockerPermission : socket 이라 부르는 네트워크에 접속을 관리하는 클래스
 *  - StandardSocketOptions : standard socket option 을 정의하는 클래스
 *
 *  - URI : Uniform Resource Identifier 참조를 대표하는 클래스
 *  - URL : Uniform Resource Locator 포인터를 대표하는 클래스
 *  - URLClassLoader : URL이 가리키는 JAR 파일이나 디렉토리의 클래스를 로드하는 클래스
 *  - URLConnection : URLConnection 의 추상 클래스. 어플리케이션과 URL 과의 통신을 담당하는 클래스
 *
 *  - URLDecoder : HTML 폼의 디코더 유틸리티
 *  - URLEncoder : HTML 폼의 인코더 유틸리티
 *  - URLPermission : 자원을 엑세스 하거나 그러한 리소스 집합을 정의한 클래스
 *  - URLStreamHandler : URLStreamHandler 의 추상 클래스. 모든 Stream Protocol Handler 의 슈퍼클래스.
 *
 * [Enums]
 * - Authenticator.RequestorType
 * - Proxy-Type
 * - StandardProtocolFamily
 */
public class JavaNet {
    public void exec(){
        HttpServerMng httpServerMng = null;
        try {
            httpServerMng = new HttpServerMng();
            httpServerMng.startServer();

            System.out.print("Please press 'Enter' to stop the server.");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(httpServerMng != null){
                httpServerMng.stopServer(0);
            }
        }
    }

    /**
     * 서버를 열고 메세지를 수신 받을 수 있는 상태를 만드시오.
     */
    private class HttpServerMng{
        /**
         * Members 멤버 선언
         */
        private final String DEFAULT_HOSTNAME = "localhost";
        private final int PORT = 3000;

        /**
         * Back Log : 완료해야할 작업의 모음.
         * TCPConnection 에서 Back Log 란 ? 대기중인 커넥션의 수
         */
        private final int BACK_LOG = 0;

        /**
         * HttpServer 객체 선언
         */
        private HttpServer server = null;

        /**
         * Constuctor 생성자 선언
         */
        private HttpServerMng() throws IOException {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(DEFAULT_HOSTNAME, PORT);  // 서버 주소 객체 생성
            server = HttpServer.create(inetSocketAddress, BACK_LOG);  // 서버 생성

            /* Path Allocation */
            server.createContext("/", new RootHandler());  // Handler 가 여기에서 생성되는 구나.
            // 그럼 혹시 복수개도 가능 한지??
            // TODO 있다 /apple /movie /vedio 등으로 테스트 해보기
        }

        /**
         * Server Start
         */
        private void startServer(){
            server.start();
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override                                                   
                public void run() {
                    // 종료 로그
                    System.out.println(
                            String.format(
                                    "[%s][HTTP SERVER][STOP]",
                                    new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(new Date())
                            )
                    );
                }
            }));
        }

        /**
         * Server Stop
         */
        private void stopServer(int delay){
            server.stop(delay);
        }

        /*************************************************************
         * HTTP Handler Class
         * - HTTP 를 이용한 request&response 가 모두 여기에서 핸들링 된다.
         *************************************************************/
        class RootHandler implements HttpHandler {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                Headers reqHeaders = exchange.getResponseHeaders();
                Headers resHeaders = exchange.getRequestHeaders();

                // request header 출력
                System.out.println("header 시작");
                for (String item:resHeaders.keySet()) {
                    System.out.println(item + " : " + resHeaders.get(item));
                }
                System.out.println("header 종료");

                BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));

                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("body 시작"+br.readLine());
                // request body 출력
                String text = "";
                while( (text = br.readLine()) != null){
                    System.out.println(text);
                }
                System.out.println("body 종료");

                OutputStream os = exchange.getResponseBody();

                // Write Response Body
                StringBuilder sb = new StringBuilder();
                sb.append("<!DOCTYPE html>");
                sb.append("<html>");
                sb.append("   <head>");
                sb.append("       <meta charset=\"UTF-8\">");
                sb.append("       <meta name=\"author\" content=\"Dochi\">");
                sb.append("       <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                sb.append("       <title>Example</title>");
                sb.append("   </head>");
                sb.append("   <body>");
                sb.append("       <h5>Hello, HttpServer!!!</h5>");
                sb.append("       <span>Method: "+(exchange.getRequestMethod())+"</span></br>");
                sb.append("       <span>URI: "+(exchange.getRequestURI())+"</span></br>");
                sb.append("       <span>PATH: "+(exchange.getRequestURI().getPath())+"</span></br>");
                sb.append("       <span>QueryString: "+(exchange.getRequestURI().getQuery())+"</span></br>");
                sb.append("   </body>");
                sb.append("</html>");

                // Encoding to UTF-8
                ByteBuffer bb = Charset.forName("UTF-8").encode(sb.toString());
                int contentLength = bb.limit();
                byte[] content = new byte[contentLength];
                bb.get(content, 0, contentLength);

                // Set Response Headers
                Headers headers = exchange.getResponseHeaders();
                headers.add("Content-Type", "text/html;charset=UTF-8");
                headers.add("Content-Length", String.valueOf(contentLength));
                exchange.sendResponseHeaders(200, contentLength);

                // outputstream
                os.write(content);
                os.close();
                br.close();
            }
        }
    }
}
