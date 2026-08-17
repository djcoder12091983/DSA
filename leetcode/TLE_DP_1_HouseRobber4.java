class Solution {
    public int minCapability(int[] A, int k) {
        // can think of selecting extact K robs and then find minimum of al possible maximum
        // can use partition DP

        HashMap<String, Integer> dp = new HashMap<>();

        return find(A, 0, k, dp);
    }

    int find(int A[], int idx, int k, HashMap<String, Integer> dp) {

        if(k == 0) {
            return 0; // all numbers are positive
        }

        int N = A.length;
        // TODO this condition may not require
        /*if(idx >= N) {
            return Integer.MAX_VALUE;
        }*/

        String key = idx + "-" + k;
        if(dp.containsKey(key)) {
            return dp.get(key); // alreacy computed
        }

        int mincap = Integer.MAX_VALUE;
        // if we don't select the current element
        for(int i = idx; i < N; i++) {
            if((N - i) / 2 >= k) {
                // enough elements to find solution
                int cap = find(A, i + 1, k, dp);
                mincap = Math.min(mincap, cap); // find min of all possible max
            }
        }
        
        // if we select current element, need to start from +2 because of non adjacency
        if(idx + 2 >= N) {
            if(k == 1) {
                mincap = Math.min(mincap, A[idx]);
            }
        } else {
            for(int i = idx + 2; i < N; i++) {
                if((N - i + 1) / 2 >= k - 1) {
                    // enough elements to find solution
                    int cap = Math.max(A[idx], find(A, i, k - 1, dp));
                    mincap = Math.min(mincap, cap); // find min of all possible max
                }
            }
        }

        dp.put(key, mincap); // store the value the idx + k

        return mincap;
    }
}