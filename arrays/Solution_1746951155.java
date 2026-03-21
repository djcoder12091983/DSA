class Solution {
    public int trap(int[] height) {
        int N = height.length;
        // previous max will nomalize in case for an index
        // there is no greater values on right side
        int prevMax[] = new int[N];
        int max = height[N - 1];
        for(int i = N - 2; i >= 0; i--) {
            if(height[i] > max) {
                int t = max;
                max = height[i];
                height[i] = -1; // indicates no greater values on right side
                prevMax[i] = t; // previous max will normalize
            }
        }

        int reference = (height[0] != -1) ? height[0] : prevMax[0];
        int ans = 0;
        for(int i = 1; i < N; i++) {

            if(height[i] == -1) {
                reference = prevMax[i]; // previous max will normalize
                continue;
            }

            if(height[i] <= reference) {
                ans = ans + (reference - height[i]);
            } else {
                // change reference
                reference = height[i];
            }
        }

        return ans;
    }
}