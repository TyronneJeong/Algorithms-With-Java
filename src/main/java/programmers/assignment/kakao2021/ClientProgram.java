package programmers.assignment.kakao2021;

import programmers.assignment.kakao2021.constants.ServerConst;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Test Program
 */
public class ClientProgram {
    private static HttpURLConnection conn;
    // 호출 메소드 유형은 GET, POST, PUT, DELETE 모두 가능 하다고 한다.

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:7777/");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(ServerConst.DEFAULT.POST);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Transfer-Encoding", "chunked");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("X-Auth-Token", "f5612e21a9ae3c40fa0b6e6cd9b9e6d3");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuffer sb = new StringBuffer();

            sb.append("{'name':'baekgooni', 'age':'12', 'height':'188', 'weight':'65'}");
            bw.write(sb.toString());
            bw.flush();
            bw.close();

            System.out.println("Send : " + sb.toString());

            // 보내고 결과값 받기
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                sb = new StringBuffer();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                System.out.println("Recv : "+sb.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
