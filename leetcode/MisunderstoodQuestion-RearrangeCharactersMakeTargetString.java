// https://leetcode.com/problems/rearrange-characters-to-make-target-string/

class Solution {

    boolean anagram(int f1[], int f2[]) {
        for(int i = 0; i < 26; i++) {
            if(f1[i] != f2[i]) {
                return false;
            }
        }
        
        return true;
    }

    public int rearrangeCharacters(String S, String T) {
        int N = S.length();
        int M = T.length();
        
        if(M > N) {
            // not possible
            return 0;
        }
        
        int f1[] = new int[26]; // T character frequency
        Arrays.fill(f1, 0);
        
        for(int i = 0; i < M; i++) {
            f1[T.charAt(i) - 'a']++;
        }
        
        // non overlapping slide window
        int ans = 0;
        for(int i = 0; i < N; i += M) {
            
            int f2[] = new int[26];
            Arrays.fill(f2, 0);
            if(i + M < N) {
                for(int j = i; j < i + M; j++) {
                    f2[S.charAt(j) - 'a']++;
                }

                if(anagram(f1, f2)) {
                    ans++;
                }
            }
        }
        
        return ans;
    }
}