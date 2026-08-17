class Solution {

    // count valid robs count
    int countRobs(int idx[]) {
        Arrays.sort(idx); // sort the index

        int robs = 1;
        int N = idx.length;
        int prev = idx[0];
        int i = 1;
        while(i < N) {
            int next = idx[i];
            if(prev + 1 < next) {
                robs++;
                prev = next;
            }

            i += 1;
        }

        return robs;
    }

    public int minCapability(int[] R, int k) {
        // the idea is to start scanning the result from kth smallest value
        // whenever we will see x >= kth smallest value statisfies a rob sequence like no adjacent values exist
        // TODO either we can can sort then check or else we can use priority queue in case k is quite smalller

        int N = R.length;
        int A[][] = new int[N][2];
        for(int i = 0; i < N; i++) {
            A[i][0] = R[i];
            A[i][1] = i; // index
        }

        // sort based on value and need to track index which helps to find non-adjacency rob sequence
        Arrays.sort(A, (x, y) -> x[0] - y[0]);

        int idx[] = new int[N];
        for(int i = 0; i < N; i++) {
            idx[i] = A[i][1];
        }

        // will apply binary search to find optimal value
        // range will be k to N
        int l = k - 1, r = N - 1;
        int ans = A[k-1][0]; // lowest possible value
        while(l <= r) {
            int mid = (l + r) / 2;
            
            int robs = countRobs(Arrays.copyOfRange(idx, 0, mid + 1));
            if(robs >= k) {
                // potential answer
                ans = A[mid][0];
                // move to left to find optimal answer
                r = mid - 1;
            } else {
                l = mid + 1; // answer will exist on the right side
            }
        }

        return ans;
    }
}