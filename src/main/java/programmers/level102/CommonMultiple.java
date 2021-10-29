package programmers.level102;

/**
 * [문제설명]
 * 두 수를 입력받아 두 수의 최대공약수와 최소공배수를 반환하는 함수, solution을 완성해 보세요. 배열의 맨 앞에 최대공약수, 그다음 최소공배수를 넣어 반환하면 됩니다. 예를 들어 두 수 3, 12의 최대공약수는 3, 최소공배수는 12이므로 solution(3, 12)는 [3, 12]를 반환해야 합니다.
 *
 * [제한사항]
 * 두 수는 1이상 1000000이하의 자연수입니다.
 *
 * [입출력 예]
 * n	m	return
 * 3	12	[3, 12]
 * 2	5	[1, 10]
 *
 * [입출력 예 설명]
 * 입출력 예 #1
 * 위의 설명과 같습니다.
 *
 * 입출력 예 #2
 * 자연수 2와 5의 최대공약수는 1, 최소공배수는 10이므로 [1, 10]을 리턴해야 합니다.
 */
public class CommonMultiple {
    public void exec(){
        int n = 3;
        int m = 12;
        solution(2, 5);
    }

    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        // 최대공약수
        for (int i = 1; i <= (n>m? m: n); i++) {
            if(n % i == 0 && m % i == 0){
                answer[0] = i;
            }
        }
        // 최소 공배수
        for (int i = (n>m? n : m); i <= (n>m? m : n)*(n>m? n : m); i+=(n>m? n : m)) {
            if(i % (n>m? m : n) == 0){
                answer[1] = i;
                break;
            }
        }
        return answer;
    }
}
