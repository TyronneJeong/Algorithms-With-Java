package programmers.assignment.kakao2021;

import com.sun.net.httpserver.HttpServer;
import programmers.assignment.kakao2021.handler.MainHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerProgram {
    /**
     * Main 함수
     * @param args
     */
    public static void main(String[] args) {
        KakaoServer kakaoServer = null;
        try {
            kakaoServer = new KakaoServer();
            kakaoServer.startServer();

            System.out.println("Please press 'Enter' to stop the server.");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            kakaoServer.stopServer(0);
        }
    }

    /**
     * Kakao Server
     */
    static class KakaoServer {
        /**
         * 멤버 정의
         */
        private final String DEFAULT_HOSTNAME = "localhost"; // 서버 자기 IP 를 대입하는 방법은?!?
        private final int PORT = 7777;
        private final int BACK_LOG = 0;

        /**
         * HttpServer 객체
         */
        private HttpServer server = null;

        /**
         * 서버 생성자
         * @throws IOException
         */
        private KakaoServer() throws IOException {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(DEFAULT_HOSTNAME, PORT);  // 서버 주소 객체 생성
            server = HttpServer.create(inetSocketAddress, BACK_LOG);  // 서버 생성
            server.createContext("/", MainHandler.getInstance());
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(
                            String.format("[%s][HTTP SERVER][STOP]",
                                    new SimpleDateFormat("yyyy-MM-dd H:mm:ss")
                                            .format(new Date())
                            )
                    );
                }
            }));
        }

        /**
         * 서버시작
         */
        private void startServer(){
            server.start();
        }

        /**
         * 서버종료
         * @param delay
         */
        private void stopServer(int delay){
            server.stop(delay);
        }
    }
}

