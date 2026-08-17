// https://leetcode.com/problems/sum-of-digit-differences-of-all-pairs/

class Solution {
    // same as https://leetcode.com/problems/total-hamming-distance/description/
    // this time we will pairs across different 10 digits
    public long sumDigitDifferences(int[] A) {
        
        // assuming unordered pairs
        int N = A.length;
        long ans = 0;
        // maximum length is 10 digits as it can be 10 power 9
        for(int i = 0; i < 10; i++) {
            int digits[] = new int[10]; // 10 different digits
            Arrays.fill(digits, 0);

            for(int j = 0; j < N; j++) {
                int digit = A[j] % 10;
                digits[digit]++; // digit frequency

                // change the number so that next time remainder will give proper positional digit
                A[j] /= 10;
            }

            // sum contribution, unordered pairs - cartesian product across all frequency of digits
            for(int j = 0; j < 10; j++) {
                for(int k  = j + 1; k < 10; k++) {
                    ans += 1L * digits[j] * digits[k]; // cross product across all unordered pairs of frequency
                }
            }
        }

        return ans;
    }
}