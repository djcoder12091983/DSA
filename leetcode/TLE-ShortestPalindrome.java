// https://leetcode.com/problems/shortest-palindrome/
// TODO: we will use KMP which will help find largest palindrome starts at 0
// IDEA is like original-string$reverse(original-string) then see maximum prefix length occurs as it's reversed

class Solution {

    // check how a longest palindrome we can form using start and l and r
    // Note: expand around the point
    int[] check(String s, int l, int r) {
        int N = s.length();
        while(l >= 0 && r < N) {
            if(s.charAt(l) != s.charAt(r)) {
                break;
            }

            l--;
            r++;
        }

        return new int[]{l + 1, r - 1};
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
        // try brute force approach like expland around the point and see the palindrome formed
        // starts with index 0

        int N = s.length();
        int len = 0;
        for(int i = 0; i < (N+1)/2; i++) {
            // odd length
            int p[] = check(s, i, i);
            if(p[0] == 0) {
                len = Math.max(len, p[1] - p[0] + 1);
            }

            // even length
            p = check(s, i, i+1);
            if(p[0] == 0) {
                len = Math.max(len, p[1] - p[0] + 1);
            }
        }

        // String palindrome = s.substring(0, len); // already largest palindrome starts at index 0
        // Note: [0, len - 1] is already largest palindrome
        // so remaining [len, N - 1] needs to be reversed to make shortest palindrome
        String rem = s.substring(len, N); // this needs to be added at the front to make palindrome

        return reverse(rem) + s; // final answer
    }
}