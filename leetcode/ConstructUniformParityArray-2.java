// https://leetcode.com/problems/construct-uniform-parity-array-ii

class Solution {
    public boolean uniformArray(int[] A) {
        // the idea is taking all odds unchanged now even will be converted back to odd
        // by using odd so we need to choose larger odd, if all evens have at least one smaler odd then
        // only it's possible. another way we can think of convertying all to even but there we will choose odds
        // only to convert into even but what will happen to smallest odd, if all are even then it's fine

        int even = 0;
        int odd = 0;
        int N = A.length;
        for(int i = 0; i < N; i++) {
            if(A[i] % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        if(even == 0 || odd == 0) {
            return true; // all are same type already 
        }

        // find odd minimum and even minimum, if even minimum > odd minimum
        // that means we can convert all evens to all odd by choocing minumum odd
        // andsubtract that from all evens

        int oddMin = Integer.MAX_VALUE, evenMin = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            if(A[i] % 2 == 0) {
                evenMin = Math.min(evenMin, A[i]);
            } else {
                oddMin = Math.min(oddMin, A[i]);
            }
        }

        if(oddMin < evenMin) {
            // possible
            return true;
        }

        return false;
    }
}