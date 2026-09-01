// https://leetcode.com/problems/rearrange-characters-to-make-target-string/

class Solution {

    public int rearrangeCharacters(String S, String T) {
        int N = S.length();
        int M = T.length();
        
        if(M > N) {
            // not possible
            return 0;
        }
        
        int f1[] = new int[26];
        Arrays.fill(f1, 0);
        
        int f2[] = new int[26];
        Arrays.fill(f2, 0);
        
        for(int i = 0; i < M; i++) {
            f1[T.charAt(i) - 'a']++;
        }

        for(int i = 0; i < N; i++) {
            f2[S.charAt(i) - 'a']++;
        }
        
        int ans = N;
        for(int i = 0; i < 26; i++) {
            if(f1[i] > 0) {
                ans = Math.min(ans, f2[i] / f1[i]);
            }
        }
        
        return ans;
    }
}