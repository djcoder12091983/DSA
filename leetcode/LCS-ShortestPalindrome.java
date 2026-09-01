// https://leetcode.com/problems/shortest-palindrome/
class Solution {

    String reverse(String s) {
        char ch[] = s.toCharArray();
        int l = 0, r = ch.length - 1;
        while(l < r) {
            char t = ch[l];
            ch[l] = ch[r];
            ch[r] = t;

            l++;
            r--;
        }

        return String.valueOf(ch);
    }

    public String shortestPalindrome(String s) {
        // we will use LCS algorithm find which will give longest prefix
        // which is palindrome
        String S = s + '$' + reverse(s);
        // System.out.println(S);

        // now run LCS
        int i = 0, j = 1;
        int N = S.length();
        int LCS[] = new int[N];
        LCS[0] = 0;
        while(j < N) {
            if(S.charAt(i) == S.charAt(j)) {
                // move as long as it's same
                LCS[j] = i + 1;
                i++;
                j++;
            } else {
                // when it's not a match tehn it's trick, where to  place i
                // see before ith index what's the LCS value
                // then place that i
                if(i > 0) {
                    int idx = LCS[i - 1];
                    i = idx;
                } else {
                    // i is already plced at 0 so we can safely move j
                    j++;
                }
            }
        }

        int len = LCS[N - 1]; // palindrome length
        String unmatch = s.substring(len);
        return reverse(unmatch) + s; // we will add unmatch characters at the front
    }
}