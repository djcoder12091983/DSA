// https://leetcode.com/problems/count-number-of-homogenous-substrings/

class Solution {
    public int countHomogenous(String s) {
        // we will find consecutive characters
        int i = 0;
        int N = s.length();

        long ans = 0;
        while(i < N) {
            char x = s.charAt(i);
            int j =i;
            while(j < N) {
                if(s.charAt(j) == x) {
                    j++;
                } else{
                    break;
                }
            }
            long c = j - i;
            ans += c * (c + 1) / 2; // all subarrays having same characters

            i = j; // next i to start
        }

        return Long.valueOf(ans % 1000000007).intValue();
    }
}