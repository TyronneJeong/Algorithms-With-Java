package leetcode.easy;
// #######################################################################
// 9. Palindrome Number
// Given an integer x, return true if x is palindrome integer.
// An integer is a palindrome when it reads the same backward as forward.
//
// For example, 121 is a palindrome while 123 is not.
// Example 1:
//
//  Input: x = 121
//  Output: true
//  Explanation: 121 reads as 121 from left to right and from right to left.
// #######################################################################
public class PalindromeNumber {
    // 기본 템플릿
    public static void exec(){
        int x = 12121;
        System.out.println(isPalindrome(x));
    }

    public static boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        int len = str.length();
        for (int ix = 0; ix < len/2; ix ++){
            if(str.charAt(ix) != str.charAt((len-1) - ix)){
                return false;
            }
        }
        return true;
    }
}
