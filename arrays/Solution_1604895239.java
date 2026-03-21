class Solution {
    public int maxArea(int[] A) {
        int N = A.length;
        int ans = Integer.MIN_VALUE;

        // left side extreme left ceil value index
        int prefix[] = new int[N]; // prefix max will be increasing
        prefix[0] = A[0];
        for(int i = 1; i < N; i++) {

            // binary search to find left ceil value index on prefix max
            int l = 0, r = i - 1;
            int idx = -1;
            while(l <= r) {
                int mid = (l + r)/2;
                if(prefix[mid] >= A[i]) {
                    // move left
                    idx = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1; // move right
                }
            }

            // prefix max
            prefix[i] = Math.max(A[i], prefix[i - 1]);

            //System.out.println("1: Index: " + i + " " + A[i] + " -> " + idx);
            // update answer
            if(idx != -1) {
                ans = Math.max(ans, A[i] * (i - idx));
            }
        }

        // right side extreme right ceil value index
        int suffix[] = new int[N]; // suffix max will be increasing
        suffix[N - 1] = A[N - 1];
        for(int i = N - 2; i >= 0; i--) {

            // binary search to find left ceil value index on prefix max
            int l = i + 1, r = N - 1;
            int idx = -1;
            while(l <= r) {
                int mid = (l + r)/2;
                if(suffix[mid] >= A[i]) {
                    // move right
                    idx = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1; // move left
                }
            }

            // prefix max
            suffix[i] = Math.max(A[i], suffix[i + 1]);

            //System.out.println("2: Index: " + i + " " + A[i] + " -> " + idx);
            // update answer
            if(idx != -1) {
                ans = Math.max(ans, A[i] * (idx - i));
            }
        }

        return ans;
    }
}