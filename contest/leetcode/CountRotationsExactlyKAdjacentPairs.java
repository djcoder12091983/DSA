https://leetcode.com/problems/count-rotations-with-exactly-k-equal-adjacent-pairs/description/

class Solution {

    String rotate(String s, int x) {
        return s.substring(x) + s.substring(0, x);
    }

    int score(String s) {
        int N = s.length();
        int c = 0;
        for(int i = 0; i < N - 1; i++) {
            if(s.charAt(i) == s.charAt(i + 1)) {
                c++;
            }
        }

        return c;
    }
    
    public int countRotations(String s, int k) {

        int i = 0;
        int N = s.length();
        int ans = 0;
        while(i < N) {
            String t = rotate(s, i);
            if(score(t) == k) {
                ans++;
            }

            i++;
        }

        return ans;
    }
}