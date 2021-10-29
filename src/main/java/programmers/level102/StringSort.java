package programmers.level102;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * [문제설명]
 * 문자열 s에 나타나는 문자를 큰것부터 작은 순으로 정렬해 새로운 문자열을 리턴하는 함수, solution을 완성해주세요.
 * s는 영문 대소문자로만 구성되어 있으며, 대문자는 소문자보다 작은 것으로 간주합니다.
 *
 * [제한사항]
 * str은 길이 1 이상인 문자열입니다.
 *
 * [입출력 예]
 * s	return
 * "Zbcdefg"	"gfedcbZ"
 */
public class StringSort {
    public void exec(){
        String s = "Zbcdefg";
        System.out.println(solution(s));
    }

    // StringBuffer 에 인자로 바로 던지는게 인상깊음.
    // Double colon operation 은 ClassName :: MethodName 형식으로 사용가능
    // MethodName 위치에 인자도 함께 전달됨.
    // new 선언시 파라미터가 생성되어 전달 됨.
    public String solution(String s) {
        List<Character> list = s.chars().mapToObj(c->(char)c).collect(Collectors.toList());
        list.sort(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return Character.compare(o2, o1);
            }});
        StringBuffer sb = new StringBuffer();
        list.forEach(sb::append);
        return sb.toString();
    }
}
