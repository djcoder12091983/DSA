class Solution {
    public int findKthLargest(int[] A, int k) {
        int N = A.length;
        k = N - k + 1;
        int l = -10000, h = 10000; // we can find min and max as well to set boundaries
        int ans = -1; // we can set any random number
        while(l <= h) {
            int mid = (l + h)/2;
            int c = 0;
            for(int i = 0; i < N; i++) {
                if(A[i] <= mid) {
                    c++;
                }
            }

            if(c < k) {
                // move right
                l = mid + 1;
            } else {
                // can be potential answer and to find the exiting move left
                // will be able to handle duplicates
                ans = mid;
                h = mid - 1;
            }
        }

        return ans;
    }
}