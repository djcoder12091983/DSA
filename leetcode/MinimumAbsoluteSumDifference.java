// https://leetcode.com/problems/minimum-absolute-sum-difference/
// TODO -- Can we do better!
// TODO -- Execution time reduction!

class Solution {
    public int minAbsoluteSumDiff(int[] A, int[] B) {
        // this sorted tree will help to find ceiling and floor value to minize difference
        TreeSet<Integer> sorted = new TreeSet<>();
        for(int x : A) {
            sorted.add(x);
        }

        int N = A.length;
        int C[] = new int[N];
        long sum = 0;
        for(int i = 0; i < N; i++) {
            C[i] = Math.abs(A[i] - B[i]);
            sum += C[i];
        }

        // now we will replace and try to minimize
        long ans = sum;
        // System.out.println("S: " + sum);
		
		// TODO -- can we avoid these loops, without checking all possibilities
		// can we do it in kind of O(1) time
		
        for(int i = 0; i < N; i++) {
            int diff = C[i];

            int diff1 = Integer.MAX_VALUE;
            if(sorted.floor(B[i]) != null) {
                diff1 = Math.min(diff1, B[i] - sorted.floor(B[i]));
            }
            if(sorted.ceiling(B[i]) != null) {
                diff1 = Math.min(diff1, sorted.ceiling(B[i]) - B[i]);
            }

            // System.out.println("Idx: " + i + " Diff: " + diff + " Diff1: " + diff1);

            if(diff1 < diff) {
                // if after replace we find a lesser difference
                long t = sum - diff + diff1; // add new difference
                ans = Math.min(ans, t);
            }
        }

        return Long.valueOf(ans % 1000000007).intValue();
    }
}