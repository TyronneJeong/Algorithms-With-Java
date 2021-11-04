package programmers.assignment.kakao2021.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import programmers.assignment.kakao2021.controller.SampleController;
import programmers.assignment.kakao2021.utils.KClassLoader;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * 네이밍 분류기준
 * - Controller : 외부와 커뮤니케이션이 있을때, 데이터 뿐만 아니라 view 와 vc 를 갖고 있을 수도 있다.
 * - Handler : 특정 객체 내부에서만 폐쇄적으로 사용 될 때, closure 에 가까우나 delegate 구현 여부에서 차이가 있따.
 *   ㄴ delegate : 함수를 보다 효율적으로 사용하기 위해 특정 함수 자체를 캡슐화 하기 위한 도구
 * - Manager : singleton 형식으로 사용될 때 사용
 */
public class MainHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers header = exchange.getRequestHeaders();
        OutputStream os = exchange.getResponseBody();

        // URI 에 따라 컨트롤러를 분리시켜 줄 수 있을 것 같다.
        System.out.println(exchange.getRequestMethod());
        System.out.println(exchange.getRequestURI());
        System.out.println(exchange.getRequestURI().getPath());
        System.out.println(exchange.getRequestURI().getQuery());

//        GET
//        /together?math=first
//        /together
//        math=first
        // path 를 이용하여 호출 클래스를 조정 할 수 있을 것 같다.
        try {
            String className = "programmers.assignment.kakao2021.controller.SampleController";
            ClassLoader loader = ClassLoader.getSystemClassLoader();

//            Class<?> myClass = loader.loadClass(className);
            Class<?> myClass = Class.forName(className);

//            Object whatInstance = myClass.newInstance();

            Method[] mArr = myClass.getMethods();
            System.out.println("Method 가져옴");
            for (Method m: mArr) {
                m.getName();
            }
            System.out.println("Method 끝남");


            Constructor<?> constructor = myClass.getConstructor();
            Object whatInstance = constructor.newInstance();

            Method myMethod = myClass.getMethod("exec", new Class[]{String.class});

            String methodParameter = "Hi!!";
            String returnValue = (String) myMethod.invoke(whatInstance, new Object[] {methodParameter});

//            String returnValue = (String) myMethod.invoke(whatInstance,
//                    new Object[] { methodParameter1, methodParameter2, methodParameter3 });

            System.out.println("#### RETURN ####");
            System.out.println(returnValue);
        } catch (ClassNotFoundException e) {
            System.out.println("클래스 로드 실패");
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        // response body
        String str = "안녕하세요 샘플 페이지 입니다. 방갑읍니다.";

        ByteBuffer bb = Charset.forName("UTF-8").encode(str);
        int contentLength = bb.limit();
        byte[] content = new byte[contentLength];
        bb.get(content, 0, contentLength);

        // Set Response Headers
        Headers headers = exchange.getResponseHeaders();
        headers.add("Content-Type", "text/html;charset=UTF-8");
        headers.add("Content-Length", String.valueOf(contentLength));

        exchange.sendResponseHeaders(200, contentLength);
        os.write(content);
        os.close();
    }
}