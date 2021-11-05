package programmers.assignment.kakao2021.controller;

import programmers.assignment.kakao2021.controller.impl.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class MainController implements Controller {
    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    private static MainController mainController;

    /* constructor */
    private MainController(){}

    /**
     * singletone instance
     * @return
     */
    public static MainController getInstance(){
        if(mainController != null){
            return mainController;
        } else {
            return new MainController();
        }
    }
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

    /**
     * doProcess
     * @param param
     * @return
     */
    @Override
    public HashMap<String, Object> doProcess(HashMap<String, Object> param, String direction){
        LOGGER.info("*~~~~~~~~~~~~~~~~ Program Start ~~~~~~~~~~~~~~~~* "+ direction);
        HashMap outputMap = new HashMap<String, String>();

        // 처리지시사항
        switch(direction){
            case "start": // 인증키 발급
                outputMap.put("auth_key", "1fd74321-d314-4885-b5ae-3e72126e75cc");
                outputMap.put("problem",  "1");
                outputMap.put("time",     "0");
                break;
            case "locations":
                ArrayList<String> aList = new ArrayList<>();
                aList.add("1");
                aList.add("2");
                aList.add("3");
                aList.add("4");
                outputMap.put("locations",  aList);
                break;
            case "trucks":
                ArrayList<String> tList = new ArrayList<>();
                tList.add("1");
                tList.add("2");
                tList.add("3");
                tList.add("4");
                outputMap.put("trucks",  tList);
                break;
            case "simulate":
                break;
            case "score":
                break;
            default:
                break;
        }

        LOGGER.info("*~~~~~~~~~~~~~~~~~ Program End ~~~~~~~~~~~~~~~~~*");
        return outputMap;
    }
}
