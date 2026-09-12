https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/

class Solution {

    int count(long mid) {
        long p5 = 5;
        int c = 0;
        while(p5 <= mid) {
            c += mid / p5;
            p5 *= 5;
        }

        return c;
    }

    public int preimageSizeFZF(int k) {

        if(k <= 1) {
            return 5;
        }

        // we will apply binary search where eactly k times 5 will occur
        long start = 5, end = 5L * k;
        boolean found = false;
        while(start <= end) {
            long mid = (start + end) / 2;
            int c = count(mid);
            if(c == k) {
                found = true;
                break;
            } else if(c < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if(found) {
            return 5;
        } else {
            return 0; // not solution exists
        }
    }
}