package programmers.assignment.kakao2021.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MainController {
    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    /* Constructor */
    private MainController(){
        // Nothing
    }

    /**
     * doProcess
     * @param param
     * @return
     */
    public HashMap<String, Object> doProcess(HashMap<String, Object> param){
        LOGGER.info("~~~~~~~~~~~~~~~~ Program Start ~~~~~~~~~~~~~~~~");
        HashMap outputMap = new HashMap<String, String>();
        for(Map.Entry<String, Object> item : param.entrySet()){
            System.out.println("Controller : "+item.getKey() + " / " + item.getValue());
        }

        outputMap.put("1", "number 1");
        outputMap.put("2", "number 2");
        outputMap.put("3", "number 3");
        outputMap.put("4", "number 4");

        LOGGER.info("~~~~~~~~~~~~~~~~ Program End ~~~~~~~~~~~~~~~~");
        return outputMap;
    }
}
