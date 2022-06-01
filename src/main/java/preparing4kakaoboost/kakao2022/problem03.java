package preparing4kakaoboost.kakao2022;

//문제 3 – 주차 요금 계산
//정답률 : 73.10%
//문제 3 풀러가기
//
//
//문제 설명
//
//주차장의 요금표와 차량이 들어오고(입차) 나간(출차) 기록이 주어졌을 때, 차량별로 주차 요금을 계산하려고 합니다. 아래는 하나의 예시를 나타냅니다.
//
//요금표
//기본 시간(분)	기본 요금(원)	단위 시간(분)	단위 요금(원)
//180	5000	10	600
//입/출차 기록
//시각(시:분)	차량 번호	내역
//05:34	5961	입차
//06:00	0000	입차
//06:34	0000	출차
//07:59	5961	출차
//07:59	0148	입차
//18:59	0000	입차
//19:09	0148	출차
//22:59	5961	입차
//23:00	5961	출차
//자동차별 주차 요금
//차량 번호	누적 주차 시간(분)	주차 요금(원)
//0000	34 + 300 = 334	5000 + ⌈(334 – 180) / 10⌉ x 600 = 14600
//0148	670	5000 +⌈(670 – 180) / 10⌉x 600 = 34400
//5961	145 + 1 = 146	5000
//어떤 차량이 입차된 후에 출차된 내역이 없다면, 23:59에 출차된 것으로 간주합니다.
//0000번 차량은 18:59에 입차된 이후, 출차된 내역이 없습니다. 따라서, 23:59에 출차된 것으로 간주합니다.
//00:00부터 23:59까지의 입/출차 내역을 바탕으로 차량별 누적 주차 시간을 계산하여 요금을 일괄로 정산합니다.
//누적 주차 시간이 기본 시간이하라면, 기본요금을 청구합니다.
//누적 주차 시간이 기본 시간을 초과하면, 기본요금에 더해서, 초과한 시간에 대해서 단위 시간 마다 단위 요금을 청구합니다.
//초과한 시간이 단위 시간으로 나누어떨어지지 않으면, 올림 합니다.
//⌈a⌉ : a보다 작지 않은 최소의 정수를 의미합니다. 즉, 올림을 의미합니다.
//
//
//주차 요금을 나타내는 정수 배열 fees, 자동차의 입/출차 내역을 나타내는 문자열 배열 records가 매개변수로 주어집니다. 차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담아서 return 하도록 solution 함수를 완성해 주세요.
//
//
//제한사항
//
//fees의 길이 = 4
//fees[0] = 기본 시간(분)
//1 ≤ fees[0] ≤ 1,439
//fees[1] = 기본 요금(원)
//0 ≤ fees[1] ≤ 100,000
//fees[2] = 단위 시간(분)
//1 ≤ fees[2] ≤ 1,439
//fees[3] = 단위 요금(원)
//1 ≤ fees[3] ≤ 10,000
//1 ≤ records의 길이 ≤ 1,000
//records의 각 원소는 "시각 차량번호 내역" 형식의 문자열입니다.
//시각, 차량번호, 내역은 하나의 공백으로 구분되어 있습니다.
//시각은 차량이 입차되거나 출차된 시각을 나타내며, HH:MM 형식의 길이 5인 문자열입니다.
//HH:MM은 00:00부터 23:59까지 주어집니다.
//잘못된 시각(“25:22”, “09:65” 등)은 입력으로 주어지지 않습니다.
//차량번호는 자동차를 구분하기 위한, 0~9로 구성된 길이 4인 문자열입니다.
//내역은 길이 2 또는 3인 문자열로, IN 또는 OUT입니다. IN은 입차를, OUT은 출차를 의미합니다.
//records의 원소들은 시각을 기준으로 오름차순으로 정렬되어 주어집니다.
//records는 하루 동안의 입/출차된 기록만 담고 있으며, 입차된 차량이 다음날 출차되는 경우는 입력으로 주어지지 않습니다.
//같은 시각에, 같은 차량번호의 내역이 2번 이상 입력으로 주어지지 않습니다.
//마지막 시각(23:59)에 입차되는 경우는 입력으로 주어지지 않습니다.
//아래의 예를 포함하여, 잘못된 입력은 주어지지 않습니다.
//주차장에 없는 차량이 출차되는 경우
//주차장에 이미 있는 차량(차량번호가 같은 차량)이 다시 입차되는 경우
//
//입출력 예
//
//fees	records	result
//[180, 5000, 10, 600]	["05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"]	[14600, 34400, 5000]
//[120, 0, 60, 591]	["16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"]	[0, 591]
//[1, 461, 1, 10]	["00:00 1234 IN"]	[14841]
//
//입출력 예 설명
//
//• 입출력 예 #1
//
//문제 예시와 같습니다.
//
//• 입출력 예 #2
//
//요금표
//기본 시간(분)	기본 요금(원)	단위 시간(분)	단위 요금(원)
//120	0	60	591
//입/출차 기록
//시각(시:분)	차량 번호	내역
//16:00	3961	입차
//16:00	0202	입차
//18:00	3961	출차
//18:00	0202	출차
//23:58	3961	입차
//자동차별 주차 요금
//차량 번호	누적 주차 시간(분)	주차 요금(원)
//0202	120	0
//3961	120 + 1 = 121	0 +⌈(121 – 120) / 60⌉x 591 = 591
//3961번 차량은 2번째 입차된 후에는 출차된 내역이 없으므로, 23:59에 출차되었다고 간주합니다.
//
//
//• 입출력 예 #3
//
//요금표
//기본 시간(분)	기본 요금(원)	단위 시간(분)	단위 요금(원)
//1	461	1	10
//입/출차 기록
//시각(시:분)	차량 번호	내역
//00:00	1234	입차
//자동차별 주차 요금
//차량 번호	누적 주차 시간(분)	주차 요금(원)
//1234	1439	461 +⌈(1439 – 1) / 1⌉x 10 = 14841
//1234번 차량은 출차 내역이 없으므로, 23:59에 출차되었다고 간주합니다.
//
//문제 풀이
//
//이 문제는 문자열 처리 능력과, 주어진 주차 요금표를 코드를 통해 정확히 구현할 수 있는지를 확인하는 문제였습니다.
//
//특히, 이 문제는 입/출차 시각과 누적 주차 시간을 모두 ‘분’단위로 환산하여 저장하면 조금 더 쉽게 해결할 수 있습니다.
//
//차량번호의 범위가 0000~9999이므로, 차량의 수는 최대 1만 대입니다. 이를 이용하여 두 배열 in_time, total_time을 다음과 같이 정의합니다.
//
//in_time[i] = i번 차량이 주차장에 입차 된 시각
//입차 된 적이 없거나, 출차되었다면 -1을 저장
//total_time[i] = i번 차량의 누적 주차 시간
//
//
//그 후 records에 담긴 원소를 순차적으로 처리해 줍니다.
//
//“IN”을 포함하고 있다면, 시각을 저장합니다.
//in_time[차량번호] = 시각
//“OUT”을 포함하고 있다면, 누적 주차 시간을 갱신합니다.
//total_time[차량번호] += ( 시각 – in_time[차량번호] )
//in_time[차량번호] = -1
//
//
//records를 모두 처리한 후에도 출차되지 않은 차량이 있다면, 즉, in_time[i] != -1인 모든 i번 차량에 대해서는 23시 59분(1439분)에 출차되었다고 간주하고, total_time[i]를 갱신해 줍니다.
//
//total_time[i] += ( 1439 – in_time[i] )
//
//위와 같은 방법으로 누적 주차 시간을 계산한 후, total_time[i] > 0 를 만족하는 모든 i번 차량에 대해서, 오름차순으로 주차 요금을 계산해서 배열에 담으면 문제를 해결할 수 있습니다.

import java.util.Arrays;
import java.util.HashMap;

public class problem03 {
    public void exec() {
        int[] fees = null;
        String[] records = null;

        fees = new int[]{180, 5000, 10, 600};
        records = new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        // [14600, 34400, 5000]

//        fees = new int[]{120, 0, 60, 591};
//        records = new String[]{"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"};
//        // [0, 591]

        fees = new int[]{1, 461, 1, 10};
        records = new String[]{"00:00 1234 IN"};
        // [14841]

        Arrays.stream(solution(fees, records)).forEach(e-> System.out.println(e));

    }

    public int[] solution(int[] fees, String[] records) {
        HashMap<String, Integer> mPrice = new HashMap<>();
        HashMap<String, Integer> mTimeRecords = new HashMap<>();

        String[] record = null;
        String sRecordTime = null;
        String sCarNo = null;
        String sInOut = null;

        int iMaximumTime = parseTimeToMinute("23:59");
        int iMinimumTime = fees[0];
        int iMinimumCharge = fees[1];
        int iUnitSize = fees[2];
        int iSegCharge = fees[3];

        // 들어오고 나간걸로 계산하기
        for(String item : records){
            record = item.split(" ");
            sRecordTime = record[0];
            sCarNo = record[1];
            sInOut = record[2];

            // IN & OUT
            if( sInOut.equals("IN") ){
                mTimeRecords.put(sCarNo, parseTimeToMinute(sRecordTime) * -1); // 입고
            } else {
                // 재입고의 경우 이용시간 누적
                if( mPrice.containsKey(sCarNo) ){
                    // 이전 이용시간 + 현재 이용시간
                    mPrice.put(sCarNo
                            , mPrice.get(sCarNo) // 기존이용시간
                            + parseTimeToMinute(sRecordTime) // 출고시간 - 입고시간 = 이용시간
                                    + mTimeRecords.get(sCarNo)
                            );
                } else {
                    mPrice.put(sCarNo
                            , parseTimeToMinute(sRecordTime) // 출고시간 - 입고시간 = 이용시간
                                    + mTimeRecords.get(sCarNo)
                    );
                }
                mTimeRecords.put(sCarNo, null);
            }
        }

        // 출차정보가 미존재하는 경우 23:59 분  출차로 가정하여 계산시킨다.
        for(String key : mTimeRecords.keySet()){
            // 출차정보 미존재 확인
            if(mTimeRecords.get(key) != null){
                mPrice.put(key, (mPrice.get(key) != null ? mPrice.get(key) : 0)
                        + iMaximumTime + mTimeRecords.get(key));
            }
            // 요금계산
            if(mPrice.get(key) < iMinimumTime){
                mPrice.put(key, iMinimumCharge);
            } else {
                mPrice.put(key,
                        ( (((mPrice.get(key) - iMinimumTime) / iUnitSize))
                                + ((mPrice.get(key) - iMinimumTime) % iUnitSize > 0 ? 1 : 0)
                        ) * iSegCharge + iMinimumCharge );
            }
        }

        // 리턴은 차량번호 오름차순
        int[] answer = new int[mPrice.size()];
        int idx = 0;
        for(Object key : mPrice.keySet().stream().sorted().toArray()){
            answer[idx++] = mPrice.get(key);
        }
        return answer;
    }

    // 시:분 을 분으로 변환한다.
    private int parseTimeToMinute(String time){
        return (Integer.parseInt(time.split(":")[0]) * 60) + (Integer.parseInt(time.split(":")[1]));
    }
}
