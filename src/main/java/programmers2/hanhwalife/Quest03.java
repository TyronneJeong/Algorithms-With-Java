package programmers2.hanhwalife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.spi.CalendarDataProvider;

public class Quest03 {
    // 실행 메서드
    public void exec(){
        System.out.println("~~ Question 03 Start ~~");
        // variables
        int[] arr = {1, 1, 1, 1, 1};
        int target = 3;
        String start_date = "05/04 MON";
        String end_date = "05/30";
        String[] login_dates = {"05/26", "05/25", "05/27", "05/10", "05/11", "05/23", "05/22", "05/21", "05/06", "05/09", "05/07", "05/08"};
        // "05/04 MON"	"05/30"	["05/26", "05/25", "05/27", "05/10", "05/11", "05/23", "05/22", "05/21", "05/06", "05/09", "05/07", "05/08"]	5
        // "05/27 SUN"	"06/16"	["05/31", "05/30", "06/01", "06/04", "06/07", "06/06", "06/09", "06/08", "06/13", "06/14", "06/10"]	4

        // solution
        System.out.println("리턴 결과 ==> " + solution( start_date,  end_date, login_dates));
        System.out.println("\n\n~~ Question 03 End ~~\n\n");
    }

    private int solution(String start_date, String end_date, String[] login_dates){
        // #############################################################################################################
        // [초기화 및 자료형 선언]
        // #############################################################################################################
        HashMap<Integer, List<Integer>> visitRecMap = new HashMap<Integer, List<Integer>>();
        // 방문일자 캐싱 용
        int iMonth = 0; // 월
        int iDay   = 0; // 일
        for (String login : login_dates){
            iMonth = Integer.parseInt(login.split("/")[0]);
            iDay   = Integer.parseInt(login.split("/")[1]);
            if(!visitRecMap.containsKey(iMonth)){
                // 공휴일 여부 확인
                visitRecMap.put(iMonth, new ArrayList<Integer>(       // 최초 리스트 생성
                        Arrays.asList(iDay)  // 신고자 ID 대입
                ));
            }  else {
                visitRecMap.get(iMonth).add(iDay); // 방문일자 추가
            }
        }

        // #############################################################################################################
        // [데이터 분해 후 재정렬]
        // #############################################################################################################
        // 평일수만 기록한다. 주말은 카운트 하지 않는다. (시작일은 월요일)
        int[] maxDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // 월별 마지막 일자
        String[] dayOfWeek = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};

        // 월요일을 기준으로 한다.
        int adjustCnt = 0;
        int iFromMonth = Integer.parseInt(start_date.split("/| ")[0]);
        int iFromDay   = Integer.parseInt(start_date.split("/| ")[1]);
        String sDayOfWeek = start_date.split("/| ")[2];
        for(int ix = 0; ix < dayOfWeek.length; ix++){
            if(sDayOfWeek.equals(dayOfWeek[ix])){
                System.out.println("입력 요일은 == > "+dayOfWeek[ix]);
                adjustCnt = ix;
                break;
            }
        }

        int iToMonth = Integer.parseInt(end_date.split("/")[0]);
        int iToDay   = Integer.parseInt(end_date.split("/")[1]);

        // 해를 넘기지는 않는듯? 하지만 추가할 수 있다.
        int distanceCnt = adjustCnt; // 일수
        int continuousVisitDays = 0;
        boolean serialYN = true;
        int returnCnt = 0;
        // 월
        for(int mon = iFromMonth; mon <= iToMonth;mon++){
            // 일자 계산
            for(int day = 1; day <= 31; day++){
                if(day == maxDays[mon-1]) break;
                if(mon == iFromMonth && day < iFromDay) continue;
                if(mon == iToMonth && day > iToDay) continue;

                distanceCnt++;

                // 시작일을 기준으로 7로 나누어 5(토), 6(일) 은 휴일로 보면 된다.
                if((distanceCnt) % 7 == 6 || (distanceCnt) % 7 == 0){
                    continue;
                }

                // #############################################################################################################
                // [목적데이터 추출]
                // #############################################################################################################
                if(visitRecMap.get(mon).contains(day)){
                    if(serialYN){
                        continuousVisitDays++;
                        if(returnCnt <= continuousVisitDays){
                            returnCnt = continuousVisitDays;
                        }
                    } else {
                        continuousVisitDays = 1;
                    }
                    serialYN = true;
                } else {
                    serialYN = false;
                }
            }
        }

        System.out.println("연속 방문일 수 : " + returnCnt);
        // #############################################################################################################
        // [결과 데이터 가공 후 리턴]
        // #############################################################################################################

        return returnCnt;
    }
}
