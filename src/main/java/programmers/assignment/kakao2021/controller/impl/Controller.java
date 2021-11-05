package programmers.assignment.kakao2021.controller.impl;

import java.util.HashMap;

public interface Controller {
    HashMap<String, Object> doProcess(HashMap<String, Object> param, String direction);
}
