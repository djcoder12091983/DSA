// https://leetcode.com/problems/lexicographically-largest-string-after-pair-transformations/

class Solution {

    String convert(int x) {
        int f[] = new int[26];
        f[0] = x; // all 'a'
        
        for(int i = 0; i < 25; i++) {
            int c = f[i];
            if(c % 2 == 0) {
                // even frequency
                f[i + 1] = c/2;
                f[i] = 0; // balanced, all done
            } else {
                // odd one
                f[i + 1] = c/2; // next character
                f[i] = 1; // one left-over
            }
        }

        StringBuilder ans = new StringBuilder();
        // lexicographically larger
        for(int i = 25; i >= 0; i--) {
            for(int j = 0; j < f[i]; j++) {
                ans.append(Character.toString(i + 'a'));
            }
        }
        return ans.toString();
    }
    
    public String[] largestString(int[] A) {
        int N = A.length;
        String ans[] = new String[N];

        for(int i = 0; i < N; i++) {
            ans[i] = convert(A[i]);
        }

        return ans;
    }
}