// https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/

class Solution {
    public int minDeletions(String s) {
        int N = s.length();
        int f[] = new int[26];
        Arrays.fill(f, 0);

        for(int i = 0; i < N; i++) {
            int x = s.charAt(i) - 'a';
            f[x]++;
        }

        // sort the frequency then we will start from higher values to lower
        // with the expection of higher frequency
        Arrays.sort(f);
        int exp = f[25]; 
        int ans = 0;
        for(int i = 25; i >= 0; i--) {
            if(f[i] == 0) {
                continue;
            }

            // System.out.println(exp + " " + f[i]);

            if(f[i] < exp) {
                exp = f[i];
            } else {
                ans += f[i] - exp;
            }

            exp = Math.max(--exp, 0); // next expected we will make it maximum 0
        }

        return ans;
    }
}