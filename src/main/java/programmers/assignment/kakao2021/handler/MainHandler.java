package programmers.assignment.kakao2021.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import programmers.assignment.kakao2021.constants.ServerConst;
import programmers.assignment.kakao2021.utils.JSONUtils;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * 네이밍 분류기준
 * - Controller : 외부와 커뮤니케이션이 있을때, 데이터 뿐만 아니라 view 와 vc 를 갖고 있을 수도 있다.
 * - Handler : 특정 객체 내부에서만 폐쇄적으로 사용 될 때, closure 에 가까우나 delegate 구현 여부에서 차이가 있따.
 * - Manager : singleton 형식으로 사용될 때
 */
public class MainHandler implements HttpHandler {
    /* constructor */
    public MainHandler() {}

    /* declare singletone instance */
    private static MainHandler mainHandler;
    public static MainHandler getInstance(){
        if(mainHandler == null){
            mainHandler = new MainHandler();
        }
        return mainHandler;
    }

    /* class loader*/
    private static final ClassLoader loader = ClassLoader.getSystemClassLoader();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers reqHeaders = exchange.getRequestHeaders();
        Headers resHeaders = exchange.getResponseHeaders();
        OutputStream os = exchange.getResponseBody();
        byte[] content;

        String errMsg = validateInputdMsg(exchange);

        // 정상인 경우에만 진입
        if(errMsg.isEmpty()){
            BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
            StringBuffer sb = new StringBuffer();
            while(br.ready()){
                sb.append(br.readLine());
            }

            String contentType = (String) reqHeaders.get("Content-type").get(0);
            String xAuthToken = (String) reqHeaders.get("X-Auth-Token").get(0);

            String strContextPath = exchange.getRequestURI().
                    getPath().replace(ServerConst.DEFAULT.ROOT, "");

            if(strContextPath.isEmpty()){
                strContextPath = ServerConst.DEFAULT.MAIN;
            }

            try {
                /* Class Load */
                Class<?> myClass = loader.loadClass(String.format(ServerConst.CONTROLLER_ADDR.PREFIX
                        +"%s"+ServerConst.CONTROLLER_ADDR.POSTFIX, strContextPath));

                /* getConstructor */
                Constructor<?> constructor = myClass.getDeclaredConstructor();
                constructor.setAccessible(true);

                /* getInstance */
                Object instance = constructor.newInstance();
                Method m = myClass.getDeclaredMethod(ServerConst.DEFAULT_METHOD.DO_PROCESS, HashMap.class);
                m.setAccessible(true);

                /* application/json */
                if(contentType.equals(ServerConst.CONTENT_TYPE.JSON)){
                    content = makeStrToByteArr(
                            JSONUtils.mapToJSONString(
                                    (HashMap<String, Object>) m.invoke(instance, JSONUtils.jsonToMap(sb.toString()))
                            )
                    );
                } else {
                    // m.invoke(instance, new Object[] {inputMap}); // String 을 리턴 한다는 가정 FIXME
                    content = makeStrToByteArr("어서오세요~");
                }
                // Set Response Headers
                resHeaders.add("Content-Type", "text/html;charset=UTF-8");
                resHeaders.add("Content-Length", String.valueOf(content.length));

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, content.length);
                os.write(content);
            } catch (Exception e) {
                e.printStackTrace();
                // 오류처리 작업 필요
            } finally {
                br.close();
                os.close();
            }
        } else {
            content = makeStrToByteArr("무지성 패킷 헤더로 인해 오류가 발생 했어요");

            resHeaders.add("Content-Type", "text/html;charset=UTF-8");
            resHeaders.add("Content-Length", String.valueOf(content.length));
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, content.length);
            os.write(content);
            os.close();
        }
    }

    private String validateInputdMsg(HttpExchange exchange) {
        // favicon 예외처리
        if(exchange.getRequestURI().getPath().equals("/favicon.ico")) {
            return "500";
        }

        if(exchange.getRequestHeaders().get("Content-type") == null){
            return "500";
        }

        if(exchange.getRequestHeaders().get("X-Auth-Token") == null){
            return "500";
        }
        return "";
    }

    /**
     * makeStrToByteArr
     * @param param
     * @return
     * @throws IOException
     */
    private byte[] makeStrToByteArr(String param) throws IOException {
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(param);
        int contentLength = byteBuffer.limit();
        byte[] content = new byte[contentLength];
        byteBuffer.get(content, 0, contentLength);
        return content;
    }
}