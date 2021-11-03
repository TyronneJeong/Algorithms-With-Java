package programmers.assignment.exercise.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class NHttpURLConnection {
    /**
     * Main 함수
     * @param args
     */
    public static void main(String[] args) {
        String clientId = "9uF8Sc7DPChDuAOWQVJx"; // 어떤 인증 키가 잇을 경우를 대비하여 작성
        String clientSecret = "qbkdQ8x1Sk"; // 시크릿 값

        String text= null; // 검색어
        try {
            text = URLEncoder.encode("삼겹살","UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("검색어 인코딩 실패");
        }

        String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text; // json 결과
        // String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

        Map<String, String> requestHeader = new HashMap<>();
        requestHeader.put("X-Naver-Client-Id", clientId);
        requestHeader.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeader);

        System.out.println(responseBody);
    }

    /**
     * Get with requestHeader
     * @param apiUrl
     * @param requestHeaders
     * @return
     */
    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl); // Connect Open

        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header : requestHeaders.entrySet()){
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode(); // 실제 커넥션 결과를 받아오는 부분

            // HttpURLConnection Constants
            // HttpURLConnection.HTTP_OK // 200 - Success
            // HttpURLConnection.HTTP_BAD_REQUEST // 400 - Bad Request
            // HttpURLConnection.HTTP_UNAUTHORIZED // 401 - Unauthorized
            // HttpURLConnection.HTTP_INTERNAL_ERROR // 500 - INternal Server Error
            if(responseCode == HttpURLConnection.HTTP_OK) {
                return readBody(con.getInputStream()); // 커넥션 결과가 정상이면 스트림을 연다.
            } else {
                return readBody(con.getErrorStream()); // 커넥션 결과가 비정상이면 에러 스트림을 연다.
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return null;
    }

    /**
     * HttpURLConnection
     * @param apiUrl
     * @return
     */
    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * readBody
     * 응답 결과 메세지의 body 를 읽는다.
     * @param body
     * @return
     */
    private static String readBody(InputStream body){
        InputStreamReader inputStreamReader = new InputStreamReader(body);

        try {
            BufferedReader lineReader = new BufferedReader(inputStreamReader);
            StringBuilder responseBody = new StringBuilder();

            String line;

            // line 단위로 응답 값을 읽어 StringBuilder 에 전달한다.
            while((line = lineReader.readLine()) != null){
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("API 응답을 읽는데 실패 했습니다.", e);
        }
    }
}
