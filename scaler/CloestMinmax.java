public class Solution {
    public int solve(int[] A) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int N = A.length;
        // find min and max
        for(int i = 0; i < N; i++) {
            min = Math.min(A[i], min);
            max = Math.max(A[i], max);
        }
        // try to find minimum length window for min-max
        // min will occur before max
        int minidx = -1;
        int window = N; // minimum window
        for(int i = 0; i < N; i++) {
            if(A[i] == min) {
                // keep latest index for minimum
                minidx = i;
            }
            if(A[i] == max && minidx != -1) {
                // minimum already found
                window = Math.min(i - minidx + 1, window);
                // reset minindex
                minidx = -1;
            }
        }
        
        // now try to do same for max-min sequence
        int maxidx = -1;
        for(int i = 0; i < N; i++) {
            if(A[i] == max) {
                // keep latest index for maximum
                maxidx = i;
            }
            if(A[i] == min && maxidx != -1) {
                // maximum already found
                window = Math.min(i - maxidx + 1, window);
                // reset maxindex
                maxidx = -1;
            }
        }
        
        return window;
    }
}
