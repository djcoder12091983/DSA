public class Solution {
    // permutation match
    boolean match(int A[], int B[]) {
        for(int i = 0; i < 26; i++) {
            if(A[i] != B[i]) {
                return false; // not possible
            }
        }
        return true;
    }
    
    public int solve(String A, String B) {
        int N = A.length();
        
        int F1[] = new int[26];
        Arrays.fill(F1, 0);
        for(int i = 0; i < N; i++) {
            int x = A.charAt(i) - 97;
            F1[x]++;
        }
        
        int F2[] = new int[26];
        Arrays.fill(F2, 0);
        int L = B.length();
        int i = 0, j = 0;
        int c = 0;
        while(j < L) {
            int x = B.charAt(j) - 97;
            F2[x]++;
            if(j - i + 1 == N) {
                if(match(F1, F2)) {
                    // permuation found
                    c++;
                }
                
                // slide window
                x = B.charAt(i) - 97;
                i++;
                F2[x]--;
            }
            
            j++;
        }
        
        return c;
    }
}