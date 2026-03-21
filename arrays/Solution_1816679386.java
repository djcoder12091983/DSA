class Solution {
    public int maxProduct(int[] A) {
        int ans = A[0];
        int prefixMax = Integer.MIN_VALUE; // prefix product max of all negative numbers
        int prefixProduct = A[0];
        if(prefixProduct < 0) {
            prefixMax = Math.max(prefixMax, prefixProduct);
        } else if(prefixProduct == 0) {
            // reset value
            prefixProduct = 1;
            prefixMax = Integer.MIN_VALUE;
        }

        int N = A.length;
        for(int i = 1; i < N; i++) {
            prefixProduct *= A[i];
            ans = Math.max(ans, prefixProduct);
            
            if(prefixProduct < 0) {
                if(prefixMax > Integer.MIN_VALUE) {
                    // we may find optimal subarray positive product
                    ans = Math.max(ans, prefixProduct / prefixMax);
                }
                prefixMax = Math.max(prefixMax, prefixProduct);
            } else if(prefixProduct == 0) {
                // reset value, because all subsequent product will be 0
                prefixProduct = 1;
                prefixMax = Integer.MIN_VALUE;
            }

        }

        return ans;

    }
}