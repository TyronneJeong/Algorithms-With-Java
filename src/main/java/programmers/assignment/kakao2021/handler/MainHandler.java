package programmers.assignment.kakao2021.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import netscape.javascript.JSObject;
import programmers.assignment.kakao2021.constants.ServerConst;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.*;

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
        InputStream is = exchange.getRequestBody();
        OutputStream os = exchange.getResponseBody();

        String reqMethod = exchange.getRequestMethod();
        String strPath;
        HashMap<String, Object> inputMap = null;
        HashMap<String, Object> outputMap = null;

        // content type

        Method m;
        Object instance;
        try {
            strPath = exchange.getRequestURI().getPath().replace(ServerConst.DEFAULT.ROOT, "");
            if(strPath.isEmpty()) strPath = ServerConst.DEFAULT.MAIN;

            // favicon 예외처리
            if(strPath.equals("favicon.ico")) {
                os.close();
                return;
            }

            // [Http header - Accept]
            // Content negotiation
            // 사용자가 수용가능한 컨텐츠의 종류를 전달 받으면 그에 대응되는 컨텐츠를 리턴한다.
            // 서버는 해당 요청에 응할 경우 200을 리턴
            // 300 - 다중 선택 가능
            // 406 - 수용가능 옵션이 없음
            // 415 - 지원가능한 미디어 유형이 아님
            // 등으로 응답할 수 있다.

            String contentType = "";
            String xAuthToken = "";
            // K, V 헤더정보
            for (Map.Entry<String, List<String>> item: reqHeaders.entrySet()) {
                System.out.println(item.getKey());
                // [Http header - Accept]
                if(item.getKey().equalsIgnoreCase("Accept")){
                    System.out.println(item.getValue().toString().startsWith(""));
                }

                // Content-Type Catch
                if(item.getKey().equalsIgnoreCase("Content-type")){
                    contentType = item.getValue().get(0);
                    System.out.println("Set Content-Type : " + contentType);
                }

                // Content-Type Catch
                if(item.getKey().equalsIgnoreCase("X-Auth-Token")){
                    contentType = item.getValue().get(0);
                    System.out.println("Set Content-Type : " + contentType);
                }
            }

            xAuthToken = reqHeaders.get("X-Auth-Token").get(0);
            System.out.println("xAuthToken >> "+xAuthToken);



            // TODO 컨텐츠 타입이 정의되는 곳이 Accept 인지
            // Contetn-Type 이라는 바디항목이 따로 있는건지 확인이 필요함.
            // 이걸 하려면 클라이언트 프로그램 작성이 필요함.

            System.out.println("Class Load : >> " + String.format(ServerConst.CONTROLLER_ADDR.PREFIX
                    +"%s"+ServerConst.CONTROLLER_ADDR.POSTFIX, strPath));

            /* Class Load */
            Class<?> myClass = loader.loadClass(String.format(ServerConst.CONTROLLER_ADDR.PREFIX
                    +"%s"+ServerConst.CONTROLLER_ADDR.POSTFIX, strPath));

            /* getConstructor */
            Constructor<?> constructor = myClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            /* getInstance */
            instance = constructor.newInstance();

            /* GET/POST */
            if(contentType.equals(ServerConst.CONTENT_TYPE.JSON)){
                // JSON TO MAP
                inputMap = new HashMap();
                inputMap.put("data", "sampledata");
            } else {
                inputMap = new HashMap();
                inputMap.put("data", "sampledata");
            }
            m = myClass.getDeclaredMethod(ServerConst.DEFAULT_METHOD.DO_PROCESS, new Class[]{HashMap.class});
            m.setAccessible(true);
            outputMap = (HashMap) m.invoke(instance, new Object[] {inputMap});
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        /* make transaction data */
        byte[] content = this.makeTranData(outputMap);

        // parsing 시작
        System.out.println("JSON Parsing Start");
        byte[] content2 = makeTranDataToJSON(outputMap);
        System.out.println("JSON Parsing End");

        // Set Response Headers
        resHeaders.add("Content-Type", "text/html;charset=UTF-8");
        resHeaders.add("Content-Length", String.valueOf(content.length));
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, content.length);

        os.write(content);
        os.close();
    }

    /**
     * makeTranData
     * @param param
     * @return
     * @throws IOException
     */
    private byte[] makeTranData(HashMap param) throws IOException {
        String str = "샘플 텍스트 입니다.";

        // JSON format 으로도 하나 만들어야 할 듯.
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(str);
        int contentLength = byteBuffer.limit();
        byte[] content = new byte[contentLength];
        byteBuffer.get(content, 0, contentLength);
        return content;
    }

    /**
     * makeTranDataToJSON
     * @param param
     * @return
     * @throws IOException
     */
    private byte[] makeTranDataToJSON(HashMap param) throws IOException {
        if(param == null) return new byte[]{};
        String rtnStr = null;
        try {
            URL url = new URL("https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar");
            final URLClassLoader ucl = new URLClassLoader(new URL[]{url}, this.getClass().getClassLoader());
            final Class<?> cls = ucl.loadClass("org.json.JSONObject");

            Constructor<?> constructor = cls.getConstructor(Map.class);
            constructor.setAccessible(true);
            Object instance = constructor.newInstance(param);

            Method m;
            m = cls.getDeclaredMethod("toString");
            m.setAccessible(true);
            rtnStr = (String) m.invoke(instance);

            System.out.println(rtnStr);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(rtnStr);
        int contentLength = byteBuffer.limit();
        byte[] content = new byte[contentLength];
        byteBuffer.get(content, 0, contentLength);
        return content;
    }
}