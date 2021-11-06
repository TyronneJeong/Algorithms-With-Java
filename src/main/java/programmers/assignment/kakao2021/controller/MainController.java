package programmers.assignment.kakao2021.controller;

import programmers.assignment.kakao2021.controller.impl.Controller;
import programmers.assignment.kakao2021.domain.ServiceMap;

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
     * doProcess
     * @param param
     * @return
     */
    @Override
    public HashMap<String, Object> doProcess(HashMap<String, Object> param, String direction) throws Exception {
        LOGGER.info("*~~~~~~~~~~~~~~~~ Program Start ~~~~~~~~~~~~~~~~* ");
        System.out.println("요청작업 : "+direction);
        ServiceMap serviceMap = ServiceMap.getInstance();
        HashMap outputMap = new HashMap<String, String>();

        // 처리지시사항
        switch(direction){
            case "start": // 인증키 발급
                outputMap.put("auth_key", "1fd74321-d314-4885-b5ae-3e72126e75cc");
                outputMap.put("problem",  "1"); // 시나리오 유형 1, 2
                outputMap.put("time",     "0");
                serviceMap.initMap("1");
                serviceMap.toString();
                break;
            case "locations":
                ArrayList<HashMap> list = new ArrayList<>();
                int[][] mapArr = serviceMap.getMap();
                HashMap<String, Integer> map;
                for (int i = 0; i < mapArr.length; i++) {
                    for (int j = 0; j < mapArr.length; j++) {
                        map = new HashMap<>();
                        map.put("id", j+(i * mapArr.length));
                        map.put("located_bikes_count", mapArr[i][j]);
                        list.add(map);
                    }
                }
                outputMap.put("locations", list);
                serviceMap.toString();
                break;
            case "trucks":
                serviceMap.setMap(3, 2, 1);
                serviceMap.setMap(1, 1, 3);
                serviceMap.toString();
                outputMap.put("trucks",  "부릉부릉");
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
