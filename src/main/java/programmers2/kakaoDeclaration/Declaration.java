package programmers2.kakaoDeclaration;

import java.util.*;

public class Declaration {
    // 실행 메서드
    public void exec(){
        System.out.println("~~ Declaration Start ~~");
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2 ;

        Arrays.stream(solution(id_list, report, k)).forEach(e->{
            System.out.println("### "+e);
        });
        System.out.println("\n\n~~ Declaration End ~~\n\n");
    }
    // #################################################################################################################
    // TODO
    // Q. STREAM. foreach, map, collection 에 대해 조금 더 파보기
    // 정렬
    // Q. JSON 처럼 멀티 키 벨류 셋 데이터 구조 확인하기 ==> 사실 MAP 이 JSON 의 키와 같다. 복수 라인은 Map 을 저장한 ArrayList
        // but resultset 의 구조는 어떻게 되어 있는지 확인 해 봐야 겠다.
    // 인텔리 단축키 익숙해 지기
    // alt + insert : 코드 생성 메뉴 뜸

    // #################################################################################################################
    // 작성 포맷 정하기
    // STEP, TODO, COMPLETE, GOAL
    // #################################################################################################################
    private static int[] solution(String[] id_list, String[] report, int k){
        HashMap<String, Integer> userMap = new HashMap<String, Integer>(); // 전달 받은 전체   사용자 ID 목록
        HashMap<String, Integer> notiMap = new HashMap<String, Integer>(); // 메세지 전달 대상 사용자 ID 목록
        HashMap<String, List<String>> pollUserListMap = new HashMap<String, List<String>>();   // 경고 및 피경고 사용자 ID 목록

        String userId   = ""; // 신고자
        String abuserId = ""; // 신고대상

        for(String id : id_list) {
            userMap.put(id, 0);
            notiMap.put(id, 0);
        }

        for(String r : report){
            userId   = r.split(" ")[0]; // 신고자
            abuserId = r.split(" ")[1]; // 신고대상
            if(!pollUserListMap.containsKey(abuserId)){
                // 신고자 목록 작성
                pollUserListMap.put(abuserId           // 피신고자
                        , new ArrayList<String>(       // 최초 리스트 생성
                                Arrays.asList(userId)  // 신고자 ID 대입
                        )
                );
                userMap.put(abuserId, 1); // 피신고자 카운트 증가
            } else {
                if(pollUserListMap.get(abuserId).contains(userId)){ // 중복 신고는 인정되지 않음.
                    continue;
                } else {
                    pollUserListMap.get(abuserId).add(userId); // 신고자 추가
                    userMap.put(abuserId, userMap.get(abuserId)+1); // 카운트 증가
                }
            }
        }
        for(String t : userMap.keySet()){
            if(userMap.get(t) >= k) { // K 회 이상 누적 경고를 받은 대상자를 신고한 사람들에게 노티 횟수를 측정 하는 것이다.
                for (String dc : pollUserListMap.get(t)){ // 신고자에게 노티가 감으로 그 횟수를 기록한다.
                    notiMap.put(dc, notiMap.get(dc)+1); // 발송 횟수를 1 증가 시킨다.
                }
            }
        }
        Stack<Integer> st = new Stack();
        for(String id : id_list){
            st.push(notiMap.get(id));
        }
        return st.stream().mapToInt(i->i).toArray();
    }

    private static int[] solutionLong(String[] id_list, String[] report, int k){
        // #############################################################################################################
        // [초기화 및 자료형 선언]
        // #############################################################################################################
        HashMap<String, Integer> userMap = new HashMap<String, Integer>(); // 전달 받은 전체   사용자 ID 목록
        HashMap<String, Integer> notiMap = new HashMap<String, Integer>(); // 메세지 전달 대상 사용자 ID 목록
        HashMap<String, List<String>> pollUserListMap = new HashMap<String, List<String>>();   // 경고 및 피경고 사용자 ID 목록

        String userId   = ""; // 신고자
        String abuserId = ""; // 신고대상

        // #############################################################################################################
        // [데이터 분해 후 재정렬]
        // #############################################################################################################
        // 전달 받은 전체   사용자 ID 목록 작성
        // 메세지 전달 대상 사용자 ID 목록 작성
        for(String id : id_list) {
            userMap.put(id, 0);
            notiMap.put(id, 0);
        }

        // 신고 정보(Report) 데이터 분해
        for(String r : report){
            userId   = r.split(" ")[0]; // 신고자
            abuserId = r.split(" ")[1]; // 신고대상

            // DB처럼 PK 아래에 신고자 목록이랑 피신고 횟수를 동시에 관리 가능한 자료형은 없을까?
            // result set 이
            // 또는 hashMap <String:PK, HashMap<String, String>> 도 가능 할 듯.
            // JSON {userid:"", count:"", pollList:[]} 와 같은 형태

            // 신고자 리스트 작성 [대상] - [신고자]
            if(!pollUserListMap.containsKey(abuserId)){
                // 신고자 목록 작성
                pollUserListMap.put(abuserId           // 피신고자
                        , new ArrayList<String>(       // 최초 리스트 생성
                                Arrays.asList(userId)  // 신고자 ID 대입
                        )
                );
                // 피신고자 카운트 증가
                userMap.put(abuserId, 1);
            } else {
                if(pollUserListMap.get(abuserId).contains(userId)){ // 중복 신고는 인정되지 않음.
                    continue;
                } else {
                    // #################################################################################################
                    // [주요 목적 케이스]
                    // #################################################################################################
                    pollUserListMap.get(abuserId).add(userId); // 신고자 추가
                    userMap.put(abuserId, userMap.get(abuserId)+1); // 카운트 증가
                    // #################################################################################################
                }
            }
            // 현재 이터레이션 중인 객체는 수정을 할 수 없다. -> ConcurrentModificationException 발생
        }

        // #############################################################################################################
        // [목적데이터 추출]
        // #############################################################################################################
        // k 회 이상 경고를 받은 사람
        System.out.println("############### K회 이상 경고 대상");
        for(String t : userMap.keySet()){
            System.out.println(t + "는 " + userMap.get(t) + "회 경고 받았습니다." + " | 신고자는 => " + pollUserListMap.get(t) + "입니다.");
            if(userMap.get(t) >= k) { // K 회 이상 누적 경고를 받은 대상자를 신고한 사람들에게 노티 횟수를 측정 하는 것이다.
                // 신고자 목록이 나온다.
                for (String dc : pollUserListMap.get(t)){ // 신고자에게 노티가 감으로 그 횟수를 기록한다.
                    // 발송 횟수를 1 증가 시킨다.
                    notiMap.put(dc, notiMap.get(dc)+1);
                }
            }
        }

        // #############################################################################################################
        // [결과 데이터 가공 후 리턴]
        // #############################################################################################################
        // 순서대로 조립하는 과정이 필요
        Stack<Integer> st = new Stack();
        // 입력 ID LIST 순서대로 노티 순서를 꺼내어 스택에 저장한다.
        for(String id : id_list){
            st.push(notiMap.get(id));
        }
        // Object Array 를 int array 로 변경 [mapToInt]
        // mapToInt 에 대해 자세히 알아보기
        // stream 의 발생 조건은?
        st.stream().mapToInt(Integer::intValue).toArray(); // 이 수식도 궁금하다.
        return st.stream().mapToInt(i->i).toArray();
    }
}
