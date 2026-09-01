// https://leetcode.com/problems/arithmetic-slices-ii-subsequence/
// TODO: Wrong and messy approach

class Solution {

    // this V indicates that same previous index and same gap will produce repetitive results
    // so V will track that previous index and it's associated gap    
    int sequence(int A[], int idx, int prevIdx, int gap, Map<Integer, Integer> V) {
        int N = A.length;
        if(idx == N) {
            return 0;
        }

        if(V.containsKey(prevIdx) && V.get(prevIdx) == gap) {
            // avoid repetitive results
            return 0;
        }

        int i = idx;
        while(i < N) {
            if(A[prevIdx] + gap == A[i]) {
                break;
            }

            i++;
        }

        if(i < N) {
            int c = 1 + sequence(A, i + 1, i, gap, V);
            V.put(i, gap);

            return c;
        } else {
            return 0;
        }
    }

    public int numberOfArithmeticSlices(int[] A) {
        // pair to start with gap
        int N = A.length;

        // this V indicates that same previous index and same gap will produce repetitive results
        // so V will track that previous index and it's associated gap
        Map<Integer, Integer> V = new HashMap<>();

        int ans  = 0;
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                int gap = A[j] - A[i];

                int len = 2 + sequence(A, j + 1, j, gap, V);
                // System.out.println("Len: " + len);
                if(len >= 3) {
                    ans += len*(len + 1)/2 - 2*len + 1;
                }
            }
        }

        System.out.println(V);

        return ans;
    }
}