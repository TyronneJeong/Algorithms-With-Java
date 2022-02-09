package leetcode.easy;

// Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
//
//         Symbol       Value
//         I             1
//         V             5
//         X             10
//         L             50
//         C             100
//         D             500
//         M             1000
//
// For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
//
// Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
//
// I can be placed before V (5) and X (10) to make 4 and 9.
// X can be placed before L (50) and C (100) to make 40 and 90.
// C can be placed before D (500) and M (1000) to make 400 and 900.
// Given a roman numeral, convert it to an integer.
public class RomanToInteger {

    public static void exec(){
        String x = "XXVII";
        System.out.println(romanToInt(x));
        // System.out.println(romanToInt(x));
    }

    // 1, 5, 10, 50, 100, 500, 1000 의 규칙을 가진다.
    // I, V : 1, 5,
    // X, L : 10, 50
    // C, D : 100, 500
    // M : 1000
    public static int romanToInt(String s){
        // 자리수 별로 발생 가능한 문자는 정해져 있다.
        // 1, 5

        // 문자열의 길이를 기준으로 판단할 수는 없다.

        // IV, VI
        // I는 다음 수를 기대해야만 한다.

        // V 도 다음수를 기대해야만 한다.

        //

        // IX
        return 0;
    }


    public static String intToRoman(String s) {
        StringBuffer sb = new StringBuffer(20);
        // III IX X XI XII XIII X
        char[] chA = {'I', 'X', 'C', 'M'};
        char[] chB = {'V', 'L', 'D'};
        int cursor = s.length()-1;
        // cursor
        for (int ix = 0; ix < s.length(); ix++){
            switch(Character.getNumericValue(s.charAt(ix))){
                case 1:
                    sb.append(chA[cursor - ix]);
                    break;
                case 2:
                    sb.append(chA[cursor - ix]);
                    sb.append(chA[cursor - ix]);
                    break;
                case 3:
                    sb.append(chA[cursor - ix]);
                    sb.append(chA[cursor - ix]);
                    sb.append(chA[cursor - ix]);
                    break;
                case 4:
                    sb.append(chA[cursor - ix]);
                    sb.append(chB[cursor - ix]);
                    break;
                case 5:
                    sb.append(chB[cursor - ix]);
                    break;
                case 6:
                    sb.append(chB[cursor - ix]);
                    sb.append(chA[cursor - ix]);
                    break;
                case 7:
                    sb.append(chB[cursor - ix]);
                    sb.append(chA[cursor - ix]);
                    sb.append(chA[cursor - ix]);
                    break;
                case 8:
                    sb.append(chB[cursor - ix]);
                    sb.append(chA[cursor - ix]);
                    sb.append(chA[cursor - ix]);
                    sb.append(chA[cursor - ix]);
                    break;
                case 9:
                    sb.append(chA[cursor - ix]);
                    sb.append(chA[cursor - ix+1]);
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }
}
