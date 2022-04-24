package programmers3.OneTwoFourNation;

public class OneTwoFourNation {
    public void exec(){
        System.out.println(solution(100));

    }

    private String solution(int n) {
        // 1. 초기화
        int share = 0;
        int remainder = 0;

        // 2. 데이터 분해 및 재정렬
        share = n / 3;
        remainder = n % 3;

        // 3. 목적 데이터 추출
        StringBuffer sb = new StringBuffer();
        if(share > 0 && remainder != 0){
            sb.append(share);
        }
        if(share == 1 && remainder == 0){
            sb.append(4);
        } else {
            sb.append(remainder);
        }

        // 4. 결과 데이터 가공 후 리턴
        return sb.toString().replaceAll("3", "4");
    }
}
