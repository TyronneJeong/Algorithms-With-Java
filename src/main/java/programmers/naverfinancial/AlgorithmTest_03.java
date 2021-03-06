package programmers.naverfinancial;

import java.util.*;

/**
 * [문제]
 * 주어진 배열의 각 원소는
 * [학생번호][과목번호][점수]에 해당한다고 할때
 * 이들중 동일한 문제번호와 점수를 가진 부정 행위자의 명단을 찾아내어라
 */
public class AlgorithmTest_03 {
    public void exec(){
        System.out.println("------------문제 3번 시작------------");
        String[] logs = {"0001 3 95", "0001 5 90", "0001 5 100", "0002 3 95", "0001 7 80", "0001 8 80", "0001 10 90", "0002 10 90", "0002 7 80", "0002 8 80", "0002 5 100", "0003 99 90"};
        Arrays.stream(solution(logs)).forEach(e-> System.out.println(e));
        System.out.println("------------문제 3번 종료------------");
    }

    public String[] solution(String[] logs) {
        ArrayList<String> rtnList = new ArrayList<>();
        HashMap<String, Map> membersMap = new HashMap<>();
        HashMap<String, String> scoreMap;

        String memberId = "";
        String[] memberInfo;
        for (int i = 0; i < logs.length; i++) {
            memberInfo = logs[i].split(" ");
            memberId = memberInfo[0];
            // 멤버정보 존재시
            if(membersMap.containsKey(memberId)){
                membersMap.get(memberId).put(memberInfo[1], memberInfo[2]); // 문제번호 K, 점수 V
            } else {
                scoreMap = new HashMap<>();
                scoreMap.put(memberInfo[1], memberInfo[2]); // 문제번호 K, 점수 V
                membersMap.put(memberId, scoreMap) ; // 이름, Map
            }
        }

        HashMap<String, String> catchBoard = new HashMap<>();
        String catchKey = "";
        ArrayList<String> list; // 문제번호 합치기용
        StringBuffer sb;
        for (String item:membersMap.keySet()){
            list = new ArrayList<>(); // 문제번호 합치기용
            sb = new StringBuffer();
            for (Object obj:membersMap.get(item).keySet()) {
                list.add(String.valueOf(obj));
                sb.append(membersMap.get(item).get(String.valueOf(obj)));
            }
            if(list.size() < 5) continue;
            list.sort(Comparator.naturalOrder());
            catchKey = list.toString() + String.valueOf(sb.toString());

            if(catchBoard.containsKey(catchKey)){
                rtnList.add(item);
                rtnList.add(catchBoard.get(catchKey));
            } else {
                catchBoard.put(catchKey, item);
            }
        }
        String[] rtnArr = rtnList.stream().distinct().sorted().toArray(String[]::new);
        if(rtnArr.length == 0) rtnArr = new String[]{"None"};
        return rtnArr;
    }
}
