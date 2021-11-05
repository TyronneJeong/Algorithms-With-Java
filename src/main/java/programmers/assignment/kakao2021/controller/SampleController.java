package programmers.assignment.kakao2021.controller;

import programmers.assignment.kakao2021.controller.impl.Controller;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * 네이밍 분류기준
 * - Controller : 외부와 커뮤니케이션이 있을때, 데이터 뿐만 아니라 view 와 vc 를 갖고 있을 수도 있다.
 * - Handler : 특정 객체 내부에서만 폐쇄적으로 사용 될 때, closure 에 가까우나 delegate 구현 여부에서 차이가 있따.
 * - Manager : singleton 형식으로 사용될 때
 */
class SampleController implements Controller {
    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    private static SampleController sampleController;

    /* Constructor */
    private SampleController(){}

    /**
     * singletone instance
     * @return
     */
    public static SampleController getInstance(){
        if(sampleController != null){
            return sampleController;
        } else {
            return new SampleController();
        }
    }

    /**
     * doProcess
     * @param param
     * @return
     */
    @Override
    public HashMap<String, Object> doProcess(HashMap<String, Object> param,  String direction){
        LOGGER.info("*~~~~~~~~~~~~~~~~ Program Start ~~~~~~~~~~~~~~~~*");
        HashMap outputMap = new HashMap<String, String>();

        System.out.println(param.get("name")+"님의 방문을 환영 합니다.");

        outputMap.put("NO1", "DATA01");
        outputMap.put("NO2", "DATA02");
        outputMap.put("NO3", "DATA03");
        outputMap.put("NO4", "DATA04");

        LOGGER.info("*~~~~~~~~~~~~~~~~~ Program End ~~~~~~~~~~~~~~~~~*");
        return outputMap;
    }
}
