package programmers.exercise.hash;

import java.util.*;

/**
 * [문제설명]
 * 전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
 * 전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.
 *
 * 구조대 : 119
 * 박준영 : 97 674 223
 * 지영석 : 11 9552 4421
 * 전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.
 *
 * [제한사항]
 * phone_book의 길이는 1 이상 1,000,000 이하입니다.
 * 각 전화번호의 길이는 1 이상 20 이하입니다.
 * 같은 전화번호가 중복해서 들어있지 않습니다.
 *
 * [입출예제]
 * phone_book	return
 * ["119", "97674223", "1195524421"]	false
 * ["123","456","789"]	true
 * ["12","123","1235","567","88"]	false
 */
public class ListOfPhoneNumbers {
    public void exec(){
        String[] phone_book = {"119", "97674223", "1195524421"};
        System.out.println(solution(phone_book));
    }

    // ArrayList 의 검색 속도를 활용
    // 정렬 시. 앞/뒤 문자열만 확인 하면 된다.
    public boolean solution(String[] phone_book) {
        ArrayList<String> ar = new ArrayList<>(phone_book.length);
        for (int i = 0; i < phone_book.length; i++) {
            ar.add(phone_book[i]);
        }
        ar.sort(String.CASE_INSENSITIVE_ORDER);
        String prevNumber = "_";
        for (String item: ar){
            if(item.startsWith(prevNumber)){
                return false;
            }
            prevNumber = item;
        }
        return true;
    }
}
