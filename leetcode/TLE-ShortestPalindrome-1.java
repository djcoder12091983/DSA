// https://leetcode.com/problems/shortest-palindrome/
class Solution {

    boolean palindrome(String s, int l, int r) {
        while(l < r) {
            if(s.charAt(l) != s.charAt(r)) {
                return false;
            }

            l++;
            r--;
        }

        return true;
    }

    // reverse a string
    String reverse(String s) {
        char ch[] = s.toCharArray();
        int l = 0, r = ch.length - 1;
        while(l < r) {
            char x = ch[l];
            ch[l] = ch[r];
            ch[r] = x;

            l++;
            r--;
        }

        return String.valueOf(ch);
    }

    public String shortestPalindrome(String s) {
        
        // again we will try brute force approach
        int N = s.length();
        if(N == 0) {
            return ""; // edge case
        }
        int len = 0;
        char x = s.charAt(0);
        for(int i = N - 1; i > 0; i--) {
            if(s.charAt(i) == x) {
                if(palindrome(s, 0, i)) {
                    len = i;
                    break; // largest palindrome found
                }
            }
        }

        String rem = s.substring(len + 1, N); // this needs to be added at the front to make palindrome
        return reverse(rem) + s; // final answer
    }
}