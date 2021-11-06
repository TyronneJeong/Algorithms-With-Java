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


    /**
     * ---------------------------------------------------------
     * 호출 API
     * [START API]
     * - 문제를 풀기 위한 Key 발급 + 자전거대여소 및 트럭 정보 초기화
     * POST /start
     * X-Auth-Token: {X-Auth-Token}
     * Content-Type: application/json
     *
     * ※ request
     * curl -X POST {BASE_URL}/start \
     *      -H 'X-Auth-Token: {X_AUTH_TOKEN}' \
     *      -H 'Content-Type: application/json' \
     *      -d '{
     *          "problem": 1
     *      }'
     *
     * ※ response
     * {
     *   "auth_key": "1fd74321-d314-4885-b5ae-3e72126e75cc",
     *   "problem": 1,
     *   "time": 0
     * }
     * ---------------------------------------------------------
     * [Location API]
     * - 각 자전거 대여소가 보유한 자전거 수를 반환한다.
     * GET /locations
     * Authorization: {auth_key}
     * Content-Type: application/json
     *
     * ※ request
     * curl -X GET {BASE_URL}/locations \
     *      -H 'Authorization: {AUTH_KEY}' \
     *      -H 'Content-Type: application/json'
     *
     * ※ response
     * {
     *   "locations": [
     *     { "id": 0, "located_bikes_count": 3 },
     *     { "id": 1, "located_bikes_count": 3 },
     *     ...
     *   ]
     * }
     * ---------------------------------------------------------
     * [Trucks API]
     * - 현재 트럭 위치와 싣고 있는 자전거 수를 반환한다.
     * GET /trucks
     * Authorization: {auth_key}
     * Content-Type: application/json
     *
     * ※ response
     * {
     *     "trucks": [
     *         { "id": 0, "location_id": 0, "loaded_bikes_count": 0 },
     *         { "id": 1, "location_id": 0, "loaded_bikes_count": 0 },
     *         ...
     *     ]
     * }
     * ---------------------------------------------------------
     * [Simulate API]
     * -
     * PUT /simulate
     * Authorization: {auth_key}
     * Content-Type: application/json
     *
     * ※ request
     * curl -X PUT {BASE_URL}/simulate \
     *      -H 'Authorization: {AUTH_KEY}' \
     *          -H 'Content-Type: application/json' \
     *      -d '{
     *        "commands": [
     *          { "truck_id": 0, "command": [2, 5, 4, 1, 6] }
     *          ...
     *        ]
     *      }'
     *
     * ※ response
     * {
     *   "status": "ready",
     *   "time": 1,
     *   "failed_requests_count": 5,
     *   "distance": 1.2,
     * }
     * ---------------------------------------------------------
     * [Score API]
     * - 해당 Auth_key 로 획득한 점수를 반환
     * GET
     * /score
     * Authorization: {auth_key}
     * Content-Type: application/json
     *
     * ※ response
     * {
     *   "score": 75.7
     * }
     * ---------------------------------------------------------
     */

    public static void main(String[] args) {
        try {
            String direction;
            direction = "start";
//            direction = "locations";
//            direction = "trucks";
            URL url = new URL("http://localhost:7777/"+direction);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(ServerConst.DEFAULT.POST);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Transfer-Encoding", "chunked");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("X-Auth-Token", "f5612e21a9ae3c40fa0b6e6cd9b9e6d3");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuffer sb = new StringBuffer();

            /* start */
            sb.append("{'problem':'1'}");

            bw.write(sb.toString());
            bw.flush();
            bw.close();

            System.out.println("Send : " + sb.toString());

            // 보내고 결과값 받기
            int responseCode = conn.getResponseCode();
            System.out.println("response code >> " + responseCode);
            if (responseCode==200) {
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
