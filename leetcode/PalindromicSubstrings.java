// https://leetcode.com/problems/palindromic-substrings/

class Solution {

    int countAroundPoint(String s, int l, int r) {
        int N = s.length();
        // odd count
        int c = 0;
        while(l >= 0 && r < N) {
            if(s.charAt(l) == s.charAt(r)) {
                // keep on expanding to check for more plaindromes
                c++;
                l--;
                r++;
            } else {
                break;
            }
        }

        return c;
    }

    public int countSubstrings(String s) {
        // we will think of expanding around a point and check how many substring can be palindrome
        // TODO can we think of using - 
        // https://www.geeksforgeeks.org/dsa/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/
        // + https://cp-algorithms.com/string/manacher.html

        int c = 0;
        int N = s.length();
        for(int i = 0; i < N; i++) {
            // odd length
            c += countAroundPoint(s, i, i);

            // even length
            c += countAroundPoint(s, i, i + 1);
        }

        return c;
    }
}