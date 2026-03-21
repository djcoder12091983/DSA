class Solution {
    public int singleNumber(int[] A) {
        
        long ans = 0;
        for(int i = 0; i < 32; i++) {
            
            int c1 = 0;
            for(int j = 0; j < A.length; j++) {
                long x = A[j];
                x = Math.abs(x);
                
                if((x & (1L << i)) > 0) {
                    c1++;
                }
            }
                   
            if(c1 % 3 == 1) {
                ans = ans | (1L << i);
            }
        }
                   
        int sign = 0;
        for(int j = 0; j < A.length; j++) {
            if(A[j] < 0) {
                sign++;
            }
        }
                   
        if(sign % 3 == 1) {
            ans = ans * -1;
        }
        
        
        return (int)ans;
    }
}