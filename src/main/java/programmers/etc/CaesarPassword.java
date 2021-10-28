package programmers.etc;

/**
 * [문제설명]
 * 어떤 문장의 각 알파벳을 일정한 거리만큼 밀어서 다른 알파벳으로 바꾸는 암호화 방식을 시저 암호라고 합니다.
 * 예를 들어 "AB"는 1만큼 밀면 "BC"가 되고, 3만큼 밀면 "DE"가 됩니다. "z"는 1만큼 밀면 "a"가 됩니다.
 * 문자열 s와 거리 n을 입력받아 s를 n만큼 민 암호문을 만드는 함수, solution을 완성해 보세요.
 *
 * [제한조건]
 * 공백은 아무리 밀어도 공백입니다.
 * s는 알파벳 소문자, 대문자, 공백으로만 이루어져 있습니다.
 * s의 길이는 8000이하입니다.
 * n은 1 이상, 25이하인 자연수입니다.
 *
 * [입출력 예]
 * s	n	result
 * "AB"	1	"BC"
 * "z"	1	"a"
 * "a B z"	4	"e F d"
 */
public class CaesarPassword {
    public void exec(){
        String s = "a B z";
        int n = 4;
        System.out.println(solution(s, n));

        // 90 다음이 97?
        // 65 ~ 122
    }
    public String solution(String s, int n) {
        char[] charArr = s.toCharArray();
        int point;
        for (int i = 0; i < charArr.length; i++) {
            point = Character.codePointAt(charArr, i);
            if(Character.isSpaceChar((char)point)){
                charArr[i] = (char)point;
            } else {
                point += n;
                if(Character.isLowerCase(charArr[i])){
                    if(point > 122){
                        point = point - 26;
                    }
                } else {
                    if(point > 90){
                        point = point - 26;
                    }
                }
                charArr[i] = (char)point;
            }
        }
        return String.valueOf(charArr);
    }
}
