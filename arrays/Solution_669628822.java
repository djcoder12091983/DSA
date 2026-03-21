class Solution {
    public int sumOddLengthSubarrays(int[] A) {
        int sum = 0;
        int N = A.length;
        for(int i = 0; i < N; i++) {
            int t = 0;
            for(int j = i; j < N; j++) {
                t = t + A[j];
                int len = j - i + 1;
                if(len % 2 == 1) {
                    sum = sum + t;
                }
            }
        }
        
        return sum;
    }
}