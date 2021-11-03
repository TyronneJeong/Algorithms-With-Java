package programmers.assignment.exercise;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Main Class
 */
public class HttpServerManager {

    /* 멤버 변수 선언 */
    private final String DEAFULT_HOSTNAME = "0.0.0.0";
    private final int DEFAULT_PORT = 8000;
    private final int DEFAULT_BACKLOG = 0;
    private HttpServer server = null;

    /**
     * Constuctor 생성자
     */
    private HttpServerManager() throws IOException {
        createServer(DEAFULT_HOSTNAME, DEFAULT_PORT);
    }
    private HttpServerManager(int port) throws IOException {
        createServer(DEAFULT_HOSTNAME, port);
    }
    private HttpServerManager(String host, int port) throws IOException {
        createServer(host, port);
    }

    /**
     * 서버 생성
     */
    private void createServer(String host, int port) throws IOException{
        this.server = HttpServer.create(new InetSocketAddress(host, port), DEFAULT_BACKLOG);
        server.createContext("/", new RootHandler());
    }
    /**
     * 서버실행
     */
    private void start(){
        server.start();
    }

    /**
     * 서버종료
     */
    private void stop(int delay){
        server.stop(delay);
    }

    /**
     * main 함수
     * @param args
     */
    public static void main(String[] args) {
        HttpServerManager httpServerManager = null;

        try{
            // 시작 로그
            System.out.println(
                    String.format(
                            "[%s][HTTP SERVER][START]",
                            new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(new Date())
                    )
            );

            // 서버 생성
            httpServerManager = new HttpServerManager("localhost", 3000);
            httpServerManager.start();
            // Shutdown Hook
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

            // Enter를 입력하면 종료
            System.out.print("Please press 'Enter' to stop the server.");
            System.in.read();

        } catch(IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            httpServerManager.stop(0);
        }
    }


    /**
     * sub class
     */
    class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException{
            OutputStream respBody = exchange.getResponseBody();

            try {
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

                // Send Response Headers
                exchange.sendResponseHeaders(200, contentLength);

                respBody.write(content);
                respBody.close();
            } catch (IOException e){
                e.printStackTrace();
                if(respBody != null){
                    respBody.close();
                }
            } finally {
                exchange.close();
            }
        }
    }
}
