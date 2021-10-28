package programmers.etc;

/**
 * [문제설명]
 * img1.png
 *
 * 네오와 프로도가 숫자놀이를 하고 있습니다. 네오가 프로도에게 숫자를 건넬 때 일부 자릿수를 영단어로 바꾼 카드를 건네주면 프로도는 원래 숫자를 찾는 게임입니다.
 *
 * 다음은 숫자의 일부 자릿수를 영단어로 바꾸는 예시입니다.
 *
 * 1478 → "one4seveneight"
 * 234567 → "23four5six7"
 * 10203 → "1zerotwozero3"
 * 이렇게 숫자의 일부 자릿수가 영단어로 바뀌어졌거나, 혹은 바뀌지 않고 그대로인 문자열 s가 매개변수로 주어집니다. s가 의미하는 원래 숫자를 return 하도록 solution 함수를 완성해주세요.
 *
 * 참고로 각 숫자에 대응되는 영단어는 다음 표와 같습니다.
 *
 * 숫자	영단어
 * 0	zero
 * 1	one
 * 2	two
 * 3	three
 * 4	four
 * 5	five
 * 6	six
 * 7	seven
 * 8	eight
 * 9	nine
 *
 * [제한사항]
 * 1 ≤ s의 길이 ≤ 50
 * s가 "zero" 또는 "0"으로 시작하는 경우는 주어지지 않습니다.
 * return 값이 1 이상 2,000,000,000 이하의 정수가 되는 올바른 입력만 s로 주어집니다.
 *
 * [입출력 예]
 * s	result
 * "one4seveneight"	1478
 * "23four5six7"	234567
 * "2three45sixseven"	234567
 * "123"	123
 * 입출력 예 설명
 * 입출력 예 #1
 *
 * 문제 예시와 같습니다.
 * 입출력 예 #2
 *
 * 문제 예시와 같습니다.
 * 입출력 예 #3
 *
 * "three"는 3, "six"는 6, "seven"은 7에 대응되기 때문에 정답은 입출력 예 #2와 같은 234567이 됩니다.
 * 입출력 예 #2와 #3과 같이 같은 정답을 가리키는 문자열이 여러 가지가 나올 수 있습니다.
 * 입출력 예 #4
 *
 * s에는 영단어로 바뀐 부분이 없습니다.
 */
public class NumbersAndEnglishWords {
    public void exec(){
//        String s = "one4seveneight";
        String s = "12zero";
        System.out.println(solution(s)); // 1, 4, 7, 8 이 나와야 함
    }

    public int solution(String s) {
        StringBuffer sb = new StringBuffer();
        int wordCnt = 0;
        while(wordCnt < s.length()){
            switch(s.charAt(wordCnt)){
                case 't': // two, three
                    if(s.charAt(wordCnt+1) == 'w'){
                        sb.append("2");
                        wordCnt += 3;
                    } else {
                        sb.append("3");
                        wordCnt += 5;
                    }
                    break;
                case 's': // six, seven
                    if(s.charAt(wordCnt+1) == 'i'){
                        sb.append("6");
                        wordCnt += 3;
                    } else {
                        sb.append("7");
                        wordCnt += 5;
                    }
                    break;
                case 'f': // four(4), five
                    if(s.charAt(wordCnt+1) == 'o'){
                        sb.append("4");
                        wordCnt += 4;
                    } else {
                        sb.append("5");
                        wordCnt += 4;
                    }
                    break;
                case 'o': // one(3)
                    sb.append("1");
                    wordCnt += 3;
                    break;
                case 'e': // eight(5)
                    sb.append("8");
                    wordCnt += 5;
                    break;
                case 'n': // nine(4)
                    sb.append("9");
                    wordCnt += 4;
                    break;
                case 'z': // zero(4)
                    sb.append("0");
                    wordCnt += 4;
                    break;
                default: // 숫자들(1)
                    sb.append(s.charAt(wordCnt));
                    wordCnt += 1;
                    break;
            }
        }
        return Integer.valueOf(sb.toString());
    }

    // replaceAll 이용한 방법인데 테스트케이스 추가되고 막혔을 듯.
    public int solution_others(String s) {
        int answer = 0;
        StringBuilder sb = new StringBuilder("");
        int len = s.length();
        String[] digits = {"0","1","2","3","4","5","6","7","8","9"};
        String[] alphabets = {"zero","one","two","three","four","five","six","seven","eight","nine"};

        for(int i=0; i<10; i++){
            s = s.replaceAll(alphabets[i],digits[i]);
        }
        return Integer.parseInt(s);
    }
}
