package programmers.assignment.exercise.tcp;

import javax.sound.sampled.Port;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * TCPServer Class
 */
public class TCPServer {
    /**
     * Port 번호
     */
    private static final int PORT = 6077;

    /**
     * main 함수
     * @param args
     */
    public static void main(String[] args) {
        // 서버 소켓 - 열때 포트 번호가 필요하다.
        ServerSocket serverSocket = null;

        // input stream 도구
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        // output stream 도구
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        PrintWriter printWriter = null;

        // 스캐너
        Scanner scanner = new Scanner(System.in);

        try {
            // 1. 서버 소켓 생성
            serverSocket = new ServerSocket();

            // 2. Socket 에 SockerAddress를 바인딩
            InetAddress inetAddress = InetAddress.getLocalHost();
            String localhost = inetAddress.getHostAddress();

            // 3.서버 소켓 오픈
            serverSocket.bind(new InetSocketAddress(localhost, PORT));
            System.out.println("[server] binding " + localhost);

            // 4. accept(클라이언트로부터 연결 요청을 기다림)
            Socket socket = serverSocket.accept(); // accept 의 동작을 다 확인할 필요가 있음.
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

            System.out.println("[server] connected by client");
            System.out.println("[server] connect with " + inetSocketAddress.getHostString() + " " + socket.getPort());

            // 지속적인 서비스
            while(true){
                // input stream 을 열고 이를 streamReader 와 bufferedReader 로 랩핑 한다.
                inputStream = socket.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                bufferedReader = new BufferedReader(inputStreamReader);

                // outputStream 을 열고 이를 스팀 리더와 프린트 라이터로 한번 더 래핑 한다.
                // 이를 주 스트림이랑 보조 스트림이라는 용어로 불러도 되는 것인가?
                outputStream = socket.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                printWriter = new PrintWriter(outputStreamWriter, true);

                String buffer = null;
                buffer = bufferedReader.readLine();

                if(buffer == null){
                    System.out.println("[server] closed by client");
                    break;
                }

                System.out.println("[server] recieved : "+buffer);
                printWriter.println(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed()){
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }
}
