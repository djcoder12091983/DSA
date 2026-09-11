// https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/

class Solution {

    boolean check(int F[]) {
        for(int i = 0; i < 26; i++) {
            if(F[i] > 2) {
                return false;
            }
        }

        return true; // all are within range
    }

    public int maximumLengthSubstring(String s) {
        // try with sliding window approach
        int N = s.length();
        
        int F[] = new int[26];
        Arrays.fill(F, 0);
        
        int i = 0, j = 0;
        int ans = 0;
        while(j < N) {
            int x = s.charAt(j) - 'a';
            F[x]++;

            if(check(F)) {
                // all set
                ans = Math.max(ans, j - i + 1);
                j++;
            } else {
                // slide widow from left
                F[x]--; // undo latest change

                x = s.charAt(i) - 'a';
                F[x]--;
                i++;
            }
        }

        return ans;
    }
}