package programmers.assignment.kakao2021.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import programmers.assignment.kakao2021.constants.ServerConst;
import programmers.assignment.kakao2021.controller.MainController;
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

        int errCode = validateMsg(exchange);
        String direction = exchange.getRequestURI().getPath().replace("/", "");

        // 정상인 경우에만 진입
        if(errCode == 0){
            BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
            try {
                StringBuffer sb = new StringBuffer();
                while(br.ready()){
                    sb.append(br.readLine());
                }

                String contentType = (String) reqHeaders.get("Content-type").get(0);
                String xAuthToken = (String) reqHeaders.get("X-Auth-Token").get(0);
                String strContextPath = exchange.getRequestURI().
                        getPath().replace(ServerConst.DEFAULT.ROOT, "");

                /* default context */
                if(strContextPath.isEmpty()){
                    strContextPath = ServerConst.DEFAULT.MAIN;
                }

                /*************************************<Class Loader>*************************************/
//                /* class load */
//                Class<?> myClass = loader.loadClass(String.format(ServerConst.CONTROLLER_ADDR.PREFIX
//                        +"%s"+ServerConst.CONTROLLER_ADDR.POSTFIX, strContextPath));
//
//                /* getConstructor */
//                Constructor<?> constructor = myClass.getDeclaredConstructor();
//                constructor.setAccessible(true);
//
//                /* getInstance */
//                Object instance = constructor.newInstance();
//                Method m = myClass.getDeclaredMethod(ServerConst.DEFAULT_METHOD.DO_PROCESS, HashMap.class);
//                m.setAccessible(true);
//
//                /* application/json */
//                content = makeStrToByteArr(
//                        JSONUtils.mapToJSONString(
//                                (HashMap<String, Object>) m.invoke(instance, JSONUtils.jsonToMap(sb.toString()))
//                        )
//                );
                /*************************************<Class Loader>*************************************/
                MainController mainController = MainController.getInstance();
                content = makeStrToByteArr(
                        JSONUtils.mapToJSONString(
                                (HashMap<String, Object>) mainController.doProcess(JSONUtils.jsonToMap(sb.toString()), direction)
                        )
                );

                /* Set Response Headers */
                resHeaders.add("Content-Type", "text/html;charset=UTF-8");
                resHeaders.add("Content-Length", String.valueOf(content.length));
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, content.length);
                os.write(content);
            } catch (Exception e) {
                e.printStackTrace();
                errCode = HttpURLConnection.HTTP_INTERNAL_ERROR;
                System.out.println("오류발생!!"+errCode);
            } finally {
                br.close();
            }
        }
        if(errCode > 0) {
            StringBuffer sb = new StringBuffer();
            sb.append("다음과 같은 오류가 발생 하였어요.</br>" + errCode +"Error</br>");
            switch(errCode){
                case 400: // Bad Request
                    sb.append("잘못된 요청 값이 존재하네요.</br>");
                    break;
                case 401: // Unauthorized
                    sb.append("인증 정보가 제대로 되어 있지 않아요.</br>");
                    break;
                case 500: // Internal Server Error
                    sb.append("알수 없는 서버 오류가 발생 했네요?</br>");
                    break;
                default:
                    System.out.println("알수 없는 에러 코드");
                    return;
            }

            System.out.println("return message : " + sb.toString());

            content = makeStrToByteArr(sb.toString());
            resHeaders.add("Content-Type", "text/html;charset=UTF-8");
            resHeaders.add("Content-Length", String.valueOf(content.length));
            exchange.sendResponseHeaders(errCode, content.length);
            os.write(content);
            os.close();
        }
    }

    /**
     * validateMsg
     * 입력전문의 유효성을 검증한다.
     * @param exchange
     * @return
     */
    private int validateMsg(HttpExchange exchange) {
        // favicon 예외처리
        if(exchange.getRequestURI().getPath().equals("/favicon.ico")) {
            return -1;
        }
        // Content-Type 누락
        if(exchange.getRequestHeaders().get("Content-type") == null){
            return HttpURLConnection.HTTP_BAD_REQUEST;
        } else {
            if(!exchange.getRequestHeaders().get("Content-type").get(0).equals(ServerConst.CONTENT_TYPE.JSON)){
                return HttpURLConnection.HTTP_BAD_REQUEST;
            }
        }
        // 인증정보 누락
        if(exchange.getRequestHeaders().get("X-Auth-Token") == null){
            return HttpURLConnection.HTTP_UNAUTHORIZED;
        } else {
            if(!exchange.getRequestHeaders().get("X-Auth-Token").get(0).equals(ServerConst.AUTH_TOKEN.KEY)){
                return HttpURLConnection.HTTP_UNAUTHORIZED;
            }
        }
        return 0;
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