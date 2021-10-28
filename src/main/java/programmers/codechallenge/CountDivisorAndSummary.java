package programmers.codechallenge;

public class CountDivisorAndSummary {
    public void exec(){
        int left = 13;
        int right = 17;
        System.out.println(solution(left, right)); // 43
    }
    public int solution(int left, int right) {
        int answer = 0;
        int val = 0;
        for (int i = left; i <= right; i++) {
            val = countDivisors(i);
            if(val % 2 == 0){
                answer+= i;
            } else {
                answer-= i;
            }
        }
        return answer;
    }
    // 약수 카운팅
    private int countDivisors(int numb){
        int cnt = 0;
        for (int i = 1; i <= (numb / 2); i++) {
            if(numb % i == 0){
                cnt ++;
            }
        }
        return cnt+1;
    }
}
