package preparing4kakaoboost.kakao2022;

// 문제 1 – 신고 결과 받기
// 정답률 : 80.13%
// 문제 1 풀러가기
//
//
// 문제 설명
//
// 신입사원 무지는 게시판 불량 이용자를 신고하고 처리 결과를 메일로 발송하는 시스템을 개발하려 합니다. 무지가 개발하려는 시스템은 다음과 같습니다.
//
// 각 유저는 한 번에 한 명의 유저를 신고할 수 있습니다.
// 신고 횟수에 제한은 없습니다. 서로 다른 유저를 계속해서 신고할 수 있습니다.
// 한 유저를 여러 번 신고할 수도 있지만, 동일한 유저에 대한 신고 횟수는 1회로 처리됩니다.
// k번 이상 신고된 유저는 즉시 게시판 이용이 정지되며, 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송합니다.
// 게시판 이용이 정지된 유저도 불량 이용자를 신고할 수 있습니다.
//
//
// 다음은 전체 유저 목록이 [“muzi”, “frodo”, “apeach”, “neo”]이고, k = 2(즉, 2번 이상 신고 당하면 이용 정지)인 경우의 예시입니다.
//
// 유저 ID	신고 ID	설명
// “muzi”	“frodo”	“muzi”가 “frodo”를 신고했습니다.
// “apeach”	“frodo”	“apeach”가 “frodo”를 신고했습니다.
// “frodo”	“neo”	“frodo”가 “neo”를 신고했습니다.
// “muzi”	“neo”	“muzi”가 “neo”를 신고했습니다.
// “apeach”	“muzi”	“apeach”가 “muzi”를 신고했습니다.
// 각 유저별로 신고 당한 횟수는 다음과 같습니다.
//
// 유저 ID	신고 당한 횟수
// “muzi”	1
// “frodo”	2
// “apeach”	0
// “neo”	2
// 위 예시에서는 2번 이상 신고당한 “frodo”와 “neo”의 게시판 이용이 정지됩니다. 이때, 각 유저별로 신고한 아이디와 정지된 아이디를 정리하면 다음과 같습니다.
//
// 유저 ID	신고 ID	정지 ID
// “muzi”	[“frodo”, “neo”]	[“frodo”, “neo”]
// “frodo”	[“neo”]	[“neo”]
// “apeach”	[“muzi”, “frodo”]	[“frodo”]
// “neo”	없음	없음
// 따라서 “muzi”는 처리 결과 메일을 2회, “frodo”와 “apeach”는 각각 처리 결과 메일을 1회 받게 됩니다.
//
// 이용자의 id가 담긴 문자열 배열 id_list, 각 이용자가 신고한 이용자에 대한 정보가 담긴 문자열 배열 report, 정지 기준이 되는 신고 횟수 k가 매개변수로 주어질 때, 각 유저별로 처리 결과 메일을 받은 횟수를 배열에 담아 return 하도록 solution 함수를 완성해 주세요.
//
//
// 제한사항
//
// 2 ≤ id_list의 길이 ≤ 1,000
// 1 ≤ id_list의 원소 길이 ≤ 10
// id_list의 원소는 이용자의 id를 나타내는 문자열이며 알파벳 소문자로만 이루어져 있습니다.
// 1 ≤ report의 길이 ≤ 200,000
// 3 ≤ report의 원소 길이 ≤ 21
// report의 원소는 “이용자 id 신고 id”형태의 문자열입니다드.
// 예를 들어 “muzi frodo”의 경우 “muzi”가 “frodo”를 신고했다는 의미입니다.
// id는 알파벳 소문자로만 이루어져 있습니다.
// 이용자 id와 신고 id는 공백(스페이스) 하나로 구분되어 있습니다.
// 자기 자신을 신고하는 경우는 없습니다.
// 1 ≤ k ≤ 200, k는 자연수
// return 하는 배열은 id_list에 담긴 id 순서대로 각 유저가 받은 결과 메일 수를 담으면 됩니다.
//
// 입출력 예
//
// id_list	report	k	result
// ["muzi", "frodo", "apeach", "neo"]	["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]	2	[2,1,1,0]
// ["con", "ryan"]	["ryan con", "ryan con", "ryan con", "ryan con"]	3	[0,0]
//
// 입출력 예 설명
//
// • 입출력 예 #1
//
// 문제의 예시와 같습니다.
//
// • 입출력 예 #2
//
// “ryan”이 “con”을 4번 신고했으나, 주어진 조건에 따라 한 유저가 같은 유저를 여러 번 신고한 경우는 신고 횟수 1회로 처리합니다. 따라서 “con”은 1회 신고당했습니다. 3번 이상 신고당한 이용자는 없으며, “con”과 “ryan”은 메일을 받지 않습니다. 따라서 [0, 0]을 return 합니다.

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class problem01 {
    public void exec() {
        String[] id_list = null;
        String[] report = null;
        int k;

        // 입력데이터 1
        id_list = new String[]{"muzi", "frodo", "apeach", "neo"};
        report = new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        k = 2;
        // >> 예상답안 : [2,1,1,0]

        // 입력데이터 2
//        id_list = new String[]{"con", "ryan"};
//        report = new String[]{"ryan con", "ryan con", "ryan con", "ryan con"};
//        k = 3;
        // >> 예상답안 : [0,0]

        Arrays.stream(solution(id_list, report, k)).forEach(answer -> System.out.println(answer));
    }

    public int[] solution(String[] id_list, String[] report, int k) {
        // 신고자 의 누적 신고 횟수 저장용 맵
        HashMap<String, Integer> reporterMap = new HashMap<>(); // 신고횟수저장용
        HashMap<String, HashSet> respondentMap = new HashMap<>(); // 신고자 목록 저장용
        HashMap<String, Integer> noticeMap = new HashMap<>(); // 신고자 알림용

        // 초기화
        for(int ix = 0; ix < id_list.length; ix ++){
            reporterMap.put(id_list[ix], 0);
            noticeMap.put(id_list[ix], 0);
            respondentMap.put(id_list[ix], new HashSet());
        }

        String Key =  "";
        String Val =  "";
        // 피신고인과 신고자 맵
        for(int ix = 0; ix < report.length; ix ++){
            Key = report[ix].split(" ")[0]; // 신고자
            Val = report[ix].split(" ")[1]; // 피신고인
            if(respondentMap.get(Val).add(Key)){ // key 삽입이 가능한 경우 (1회차인경우)
                reporterMap.put(Val, reporterMap.get(Val) + 1);
            }
        }

        // 누적 신고 확인 후 차단 자 지정
        for(String reportedUser : reporterMap.keySet()){
            if(reporterMap.get(reportedUser) >= k){
                respondentMap.get(reportedUser).forEach(name -> noticeMap.put((String)name, noticeMap.get((String)name) + 1));
            }
        }

        int[] answer = new int[id_list.length];
        int loopCnt = 0;
        for(String id : id_list) answer[loopCnt++] = noticeMap.get(id);
        return answer;
    }
}