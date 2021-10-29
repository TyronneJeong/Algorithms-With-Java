package programmers.level102;

/**
 * [문제설명]
 * 문자열 s의 길이가 4 혹은 6이고, 숫자로만 구성돼있는지 확인해주는 함수, solution을 완성하세요.
 * 예를 들어 s가 "a234"이면 False를 리턴하고 "1234"라면 True를 리턴하면 됩니다.
 *
 * [제한사항]
 * s는 길이 1 이상, 길이 8 이하인 문자열입니다.
 *
 * [입출력 예]
 * s	    return
 * "a234"	false
 * "1234"	true
 */
public class ControlStrings {
    public void exec(){
        String s = "    ";
        System.out.println(solution(s));
    }
    public boolean solution(String s) {
        if(s.length() == 4 || s.length() == 6){
            try {
                Integer.valueOf(s);
                return true;
            } catch(Exception e){
                return false;
            }
        } else {
            return false;
        }
    }

    // 테스트케이스 6번이 통과가 안되어서 다시 짬.
    public boolean solution2(String s) {
        if(s.isEmpty()) return false;
        if(s.replaceAll("[0-9]{6}", "").isEmpty()
                || s.replaceAll("[0-9]{4}", "").isEmpty()) return true;
        return false;
    }
}
