// https://leetcode.com/problems/minimum-operations-to-make-array-equal/

class Solution {
    public int minOperations(int n) {
        // we will change all elements to middle as numbers are evenly spaced
        if(n % 2 == 0) {
            int mid = n / 2;
            return ((2 + 2 * mid) * mid / 2 + (2 + 2 * (mid - 1)) * (mid - 1) / 2) / 2;
        } else {
            int mid = n / 2;
            return (2 + 2 * mid) * mid / 2;
        }
    }
}