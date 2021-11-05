package programmers.assignment.kakao2021.controller;

import java.util.HashMap;
import java.util.logging.Logger;

public class MainController {
    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    /* Constructor */
    private MainController(){}

    /**
     * doProcess
     * @param param
     * @return
     */
    public HashMap<String, Object> doProcess(HashMap<String, Object> param){
        LOGGER.info("*~~~~~~~~~~~~~~~~ Program Start ~~~~~~~~~~~~~~~~*");
        HashMap outputMap = new HashMap<String, String>();

        System.out.println(param.get("name")+"님의 방문을 환영 합니다.");

        outputMap.put("NO1", "잘생김 허용");
        outputMap.put("NO2", "멋짐 인정");
        outputMap.put("NO3", "도드라짐 OK");
        outputMap.put("NO4", "고추가큼 OK");

        LOGGER.info("*~~~~~~~~~~~~~~~~~ Program End ~~~~~~~~~~~~~~~~~*");
        return outputMap;
    }
}
