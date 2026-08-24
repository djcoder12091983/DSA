// https://leetcode.com/problems/scramble-string/
// TODO: need to use DP

class Solution {
    public boolean isScramble(String s1, String s2) {
        return check(s1, s2, 0, s1.length() - 1);
    }

    // s2 is always fix and s1 keeps on changing based on partition and all
    boolean check(String s1, String s2, int l, int r) {
        if(l == r) {
            return s1.charAt(0) == s2.charAt(l);
        }

        // now explore both option
        int N = s1.length();
        for(int i = 1; i < N; i++) {
            String p1 = s1.substring(0, i);
            String p2 = s1.substring(i, N);
            
            // swap
            boolean yes = check(p1, s2, l, l + i - 1) & check(p2, s2, l + i, r);
            if(!yes) {
                // opt for not swap, note: the start and end index
                int len = p2.length();
                yes = check(p2, s2, l, l + len - 1) & check(p1, s2, l + len, r);
                if(yes) {
                    return true;
                }
            } else {
                return true; // done
            }
        }

        // if any option not leading to result return false
        return false;
    }
}