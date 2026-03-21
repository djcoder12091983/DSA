class Solution {

    int DP[]; // LIS @ every point
    int count[]; // count of LIS starts @ every point

    int maxlis(int A[], int idx) {
        int N = A.length;
        if(idx == N) {
            return 0;
        }

        if(DP[idx] != -1) {
            // already computed
            return DP[idx];
        }

        // try out all possible starting points after A[i]
        int maxlen = 1; // self
        for(int i = idx + 1; i < N; i++) {
            if(A[i] > A[idx]) {
                // starts with A[i] when A[i] > previous
                maxlen = Math.max(maxlen, 1 + maxlis(A, i));
            }
        }

        DP[idx] = maxlen;
        return maxlen;
    }

    public int findNumberOfLIS(int[] nums) {
        int N = nums.length;
        DP = new int[N];
        count = new int[N];
        Arrays.fill(DP, -1);
        Arrays.fill(count, 0);

        int globalmax = 0;
        for(int i = 0; i < N; i++) {
            globalmax = Math.max(globalmax, maxlis(nums, i));
        }

        // count LIS @ every point
        int ans = 0;
        count[N - 1] = 1;
        if(globalmax == 1) {
            ans += count[N - 1];
        }

        for(int i = N - 2; i >= 0; i --) {
            int maxlen = DP[i];
            if(maxlen == 1) {
                count[i] += 1;
            }
            for(int j = i+1; j < N; j++) {
                if(nums[j] > nums[i]) {
                    if(1 + DP[j] == maxlen) {
                        count[i] += count[j];
                    }
                }
            }
            if(maxlen == globalmax) {
                ans += count[i];
            }
        }

        //return globalmax;

        return ans;
    }
}