https://leetcode.com/problems/maximum-subsequence-score/

class Solution {
    public long maxScore(int[] A, int[] B, int k) {
        int N = A.length;

        // first we will sort the array B in descending order then try to fix the minimum value
        // and free values from A we will try to choose greedily to maximize the score

        int C[][] = new int[N][2];
        for(int i = 0; i < N; i++) {
            C[i][0] = A[i];
            C[i][1] = B[i];
        }

        Arrays.sort(C, (x, y) -> y[1] - x[1]);

        // now we will fix the mimum then use PQ to choose best K - 1 values from A
        long res = 0;
        // // min PQ to track best K - 1 values from A, why MIN because it will help me replace values from top
        // then it will be easy to compute sum in one shot instead of itearting all the elements
        PriorityQueue<Integer> Q = new PriorityQueue<>();
        long sum = 0;
        for(int i = 0; i < k - 1; i++) {
            Q.add(C[i][0]);
            sum += C[i][0];
        }

        for(int i = k - 1; i < N; i++) {
            // now fix minimum value and top k - 1 free values from A
            res = Math.max(res, (sum + C[i][0]) * C[i][1]);

            // now try to see whether ith value can contribute to maximum sum from A values or what
            if(!Q.isEmpty() && C[i][0] > Q.peek()) {
                // replace and update Q and sum
                sum -= Q.poll();
                Q.add(C[i][0]);
                sum += C[i][0];
            }
        }

        return res;
    }
}