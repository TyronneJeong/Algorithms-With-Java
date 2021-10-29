package programmers.level101;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StrangeStrings {
    public void exec(){
        String s = "try hello world";
        System.out.println(solution(s));
    }
    public String solution(String s) {
        String[] strArr = s.split(" ", -1);
        char[] charArr;
        for (int i = 0; i < strArr.length; i++) {
            charArr = strArr[i].toCharArray();
            for (int j = 0; j < charArr.length; j++) {
                if(j % 2 == 0){
                    charArr[j] = Character.toUpperCase(charArr[j]);
                } else {
                    charArr[j] = Character.toLowerCase(charArr[j]);
                }
            }
            strArr[i] = String.valueOf(charArr);
        }
        return Arrays.stream(strArr).collect(Collectors.joining(" "));
    }
}
