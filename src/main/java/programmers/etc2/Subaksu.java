package programmers.etc2;

/**
 * [문제설명]
 * 길이가 n이고, "수박수박수박수...."와 같은 패턴을 유지하는 문자열을 리턴하는 함수,
 * solution을 완성하세요. 예를들어 n이 4이면 "수박수박"을 리턴하고 3이라면 "수박수"를 리턴하면 됩니다.
 *
 * [제한조건]
 * n은 길이 10,000이하인 자연수입니다.
 *
 * [입출력 예]
 * n	return
 * 3	"수박수"
 * 4	"수박수박"
 */
public class Subaksu {
    public void exec(){
        int n = 12;
        System.out.println(solution(n));
    }

    public String solution(int n) {
        char[] charArr = {'수','박'};
        StringBuffer sb = new StringBuffer(n);
        for (int i = 0; i < n; i++) {
            sb.append(charArr[i%2]);
        }
        return sb.toString();
    }

    // 새로생성하는 스트링을 특정 글자로 채워 줄 수 있다.
    public String watermelon(int n){
        return new String(new char [n/2+1]).replace("\0", "수박").substring(0,n);
    }
}
