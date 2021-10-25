package programmers.exercise.hash;

import java.util.HashMap;
import java.util.HashSet;

/**
 * [문제설명]
 * 수많은 마라톤 선수들이 마라톤에 참여하였습니다.
 * 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
 * 마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때,
 * 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.
 *
 * [제한사항]
 * 마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
 * completion의 길이는 participant의 길이보다 1 작습니다.
 * 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
 * 참가자 중에는 동명이인이 있을 수 있습니다.
 *
 * [입출력 예]
 * participant	completion	return
 * ["leo", "kiki", "eden"]	["eden", "kiki"]	"leo"
 * ["marina", "josipa", "nikola", "vinko", "filipa"]	["josipa", "filipa", "marina", "nikola"]	"vinko"
 * ["mislav", "stanko", "mislav", "ana"]	["stanko", "ana", "mislav"]	"mislav"
 */

public class E01UncompletedRace {
    public void exec(){
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        String rtnStr = solution(participant, completion);;
        System.out.println(rtnStr);
    }

    /**
     * [HashMap vs HashSet]
     * - HashMap은 Map 인터페이스로 구현된다.
     * - HashMap은 내부적으로 자료형 구현을 위한 다른 자료형 참조나 구성을 하지 않는다.
     * - HashMap은 키-벨류 쌍으로 저장되며 각 엘리먼트는 검색시 사용되는 유니크 키가 할당된다.
     * - HashMap은 유니크 키 사용으로 인해 인덱싱시 보다 빠르다.
     */
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> hm = new HashMap<String, Integer>(completion.length);
        for (int i = 0; i < completion.length; i++) {
            if(hm.containsKey(completion[i])){
                hm.put(completion[i], hm.get(completion[i]) + 1);
            } else {
                hm.put(completion[i], 1);
            }
        }
        for(String item:participant){
            if(hm.containsKey(item)){
                if(hm.get(item) >= 1){
                    hm.put(item, hm.get(item) - 1);
                } else {
                    return item;
                }
            } else {
                return item;
            }
        }
        return "";
    }

    /**
     * [HashMap vs HashSet]
     * - HashSet은 Set 인터페이스로 구현된다.
     * - HashSet은 내부적으로 Set 자료형 구현을 위해 HashMap 을 이용한다.
     * - HashSet은 Key 가 없는 오직 객체(Object)형태로만 값을 저장한다.
     * - HashSet은 완전히 오브젝트 기준으로 구성되어 있기 때문에 HashMap 과 비교시 더 느리다.
     */
    public String solution2(String[] participant, String[] completion) {
        HashSet<String> hs = new HashSet<String>(completion.length);
            for (String item:completion) {
            hs.add(item);
        }
        for(String item:participant){
            if(hs.contains(item)){
                hs.remove(item);
            } else {
                return item;
            }
        }
        return "";
    }
}

