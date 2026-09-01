// https://leetcode.com/problems/maximum-valid-split-positions-i/
// BRUTE-FORCE approach -- need to think optimally for larger input

class Solution {

    int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    int score(int A[]) {
        int N = A.length;
        if(N == 1) {
            return 0; // for 1 element score is 0
        }
        
        // prefix GCD
        int P[] = new int[N];
        P[0] = A[0];
        for(int i = 1; i< N; i++) {
            P[i] = gcd(P[i - 1], A[i]);
        }

        // suffix GCD
        int S[] = new int[N];
        S[N - 1] = A[N - 1];
        for(int i = N - 2; i >= 0; i--) {
            S[i] = gcd(S[i + 1], A[i]);
        }
        // now find split points
        int c = 0;
        for(int i = 0; i < N - 1; i++) {
            int left = P[i];
            int right = S[i + 1];
            if(left == right) {
                // split point
                c++;
            }
        }

        // System.out.println(c);

        return c;
    }
    
    public int maxValidSplits(int[] A) {

        int N = A.length;
            
        int ans = score(A); // full array score
        for(int i = 0; i < N; i++) {
            // remove ith element and see score
            int B[] = new int[N - 1];
            int k = 0;
            for(int j = 0; j < i; j++) {
                B[k++] = A[j];
            }
            for(int j = i + 1; j < N; j++) {
                B[k++] = A[j];
            }

            ans = Math.max(ans, score(B));
        }

        return ans;
    }
}