// https://leetcode.com/problems/find-the-closest-palindrome/

class Solution {

    // generate palindrome based on half length
    String generate(String half, boolean even) {

        if(half.equals("0")) {
            // handling 11 smaller palindrome
            return "9";
        }

        int l = half.length();
        if(even) {
            // even length
            return half + new StringBuilder(half).reverse();
        } else {
            return half + new StringBuilder(half.substring(0, l - 1)).reverse();
        }
    }

    public String nearestPalindromic(String N) {
        if(N.equals("1")) {
            return "0"; // edge case
        }

        // take the half string then for odd/even length we need to generate accordingly
        int l = N.length();
        boolean even = (l & 1) == 0;
        int middle = (l + 1) / 2;

        String half = N.substring(0, middle);
        
        // check 3 options
        // if left side closest palindrome same as N then need to look for left and right again
        // because we need to exclude N itself and if left side is < N then look into right side only
        String left = generate(half, even);
        long t = Long.valueOf(half);
        if(left.equals(N)) {
            left = generate(String.valueOf(t - 1), even);
            // edge case like if N is 1001 and take half 10 if we generate palindrome using 10 then
            // onm left side it would (10 - 1) is 9 and palindrome will be 99 but we can have 999 closest one
            // so the idea is check the length difference, absolute length difference would be [0, 1]
            int diff = Math.abs(l - left.length());
            if(diff > 1) {
                // need to generate based on (half + 1) length in case of even length
                left = generate(String.valueOf(Long.valueOf(N.substring(0, middle + 1)) - 1), even);
            }
        }
        String right = generate(String.valueOf(t + 1), even);
        // same thing we need to apply for right case if length odd for example 99999
        // if we gererate based on 999 then (999+1) = 1000 half will give 10000001
        // so we will go with (99+1) = 100 half which will give 100001
        int diff = Math.abs(l - right.length());
        if(diff > 1) {
            // need to generate based on (half - 1) length in case of odd length
            right = generate(String.valueOf(Long.valueOf(N.substring(0, middle - 1)) + 1), even);
        }

        long leftNum = Long.valueOf(left);
        long rightNum = Long.valueOf(right);

        t = Long.valueOf(N);
        long d1 = t - leftNum;
        long d2 = rightNum - t;

        // based on closest one
        if(d1 <= d2) {
            // handle tie as well
            return left;
        } else {
            return right;
        }
    }
}