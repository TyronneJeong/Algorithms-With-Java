package programmers.assignment.kakao2021.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import programmers.assignment.kakao2021.constant.ServerConst;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class MainHandler implements HttpHandler {

    private static final ClassLoader loader = ClassLoader.getSystemClassLoader();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers reqHeaders = exchange.getRequestHeaders();
        Headers resHeaders = exchange.getResponseHeaders();
        InputStream is = exchange.getRequestBody();

        OutputStream os = exchange.getResponseBody();

        String reqMethod = exchange.getRequestMethod();
        String strPath;
        String rtnValue;

        Method myMethod;
        Object whatInstance;

        try {
            strPath = exchange.getRequestURI().getPath().replace(ServerConst.DEFAULT.ROOT, "");
            if(strPath.isEmpty()) strPath = ServerConst.DEFAULT.MAIN;

            // favicon 예외처리
            if(strPath.equals("favicon.ico")) {
                os.close();
                return;
            }

            System.out.println("요청 URL : " + String.format(ServerConst.CONTROLLER_ADDR.PREFIX
                    +"%s"+ServerConst.CONTROLLER_ADDR.POSTFIX, strPath));
            /* Class Load */
            Class<?> myClass = loader.loadClass(String.format(ServerConst.CONTROLLER_ADDR.PREFIX
                    +"%s"+ServerConst.CONTROLLER_ADDR.POSTFIX, strPath));

            /* getConstructor */
            Constructor<?> constructor = myClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            /* getInstance */
            whatInstance = constructor.newInstance();

            /* GET/POST */
            if(reqMethod.equals(ServerConst.DEFAULT.GET)){
                myMethod = myClass.getDeclaredMethod(ServerConst.DEFAULT_METHOD.GET, new Class[]{String.class});
            } else {
                myMethod = myClass.getDeclaredMethod(ServerConst.DEFAULT_METHOD.POST, new Class[]{String.class});
            }

            myMethod.setAccessible(true);
            // TODO 파라미터, request body 부분에 데이터가 어떻게 오는지 확인해야 한다.
            rtnValue = (String) myMethod.invoke(whatInstance, new Object[] {"sameple"});
            // TODO response body 부분에 데이터를 어떻게 옮겨 담을지를 정의해야 한다.
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // response body
        String str = "안녕하세요 샘플 페이지 입니다. 방갑읍니다.";
        // page redirect 하는 방법은?!?
        // 생각해보면 페이지를 로드해서 랜더링 해서 보여주는 것인듯?!?








        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(str);
        int contentLength = byteBuffer.limit();
        byte[] content = new byte[contentLength];
        byteBuffer.get(content, 0, contentLength);

        // Set Response Headers
        resHeaders.add("Content-Type", "text/html;charset=UTF-8");
        resHeaders.add("Content-Length", String.valueOf(contentLength));

        exchange.sendResponseHeaders(200, contentLength);
        os.write(content);
        os.close();
    }
}

