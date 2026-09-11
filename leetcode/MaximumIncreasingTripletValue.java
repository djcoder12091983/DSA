// https://leetcode.com/problems/maximum-increasing-triplet-value/

class Solution {
    public int maximumTripletValue(int[] A) {
        // the idea is like for every point find maximum on the right side which we will add
        // now on the left side to minimize the difference we need to closest one

        int N = A.length;
        int SM[] = new int[N];
        SM[N - 1] = A[N - 1];
        for(int i = N - 2; i >= 0; i--) {
            SM[i] = Math.max(SM[i + 1], A[i]);
        }

        // we will use TreeSet to find floor of x - 1 where x is current element
        TreeSet<Integer> track = new TreeSet<>();
        track.add(A[0]);

        int ans = Integer.MIN_VALUE;
        for(int i = 1; i < N - 1; i++) {
            Integer left = track.floor(A[i] - 1); // we need find strictly lesser but closest
            if(left != null && SM[i + 1] > A[i]) {
                // sequence possible
                ans = Math.max(ans, left - A[i] + SM[i + 1]);
            }

            track.add(A[i]);
        }

        return ans;
    }
}