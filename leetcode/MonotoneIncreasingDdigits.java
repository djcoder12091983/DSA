// https://leetcode.com/problems/monotone-increasing-digits/
// TODO -- Can we implement BETTER!

class Solution {

    void reverse(int digits[], int l, int r) {
        while(l < r) {
            int t = digits[l];
            digits[l] = digits[r];
            digits[r] = t;

            l++;
            r--;
        }
    }

    public int monotoneIncreasingDigits(int n) {

        if(n == 0) {
            // edge case
            return 0;
        }

        int digits[] = new int[10]; // max limit 10 power 9
        int l = 0;
        int t = n;
        while(t > 0) {
            digits[l] = t % 10;
            t = t / 10;

            l++;
        }

        if(l == 1) {
            // single digit number
            return n;
        }

        reverse(digits, 0, l - 1); // reverse the digits

        int i = 0;
        while(i < l - 1) {
            if(digits[i] > digits[i + 1]) {
                break;
            }

            i++;
        }

        if(i == l - 1) {
            // the number itself monotone
            return n;
        }

        // now we will try to form monotone number
        while(i > 0) {
            if(digits[i - 1] <= digits[i] - 1) {
                digits[i] = digits[i] - 1;
                break;
            }

            i--;
        }

        if(i == 0) {
            if(digits[0] == 1) {
                // special case
                l = l - 1;
                i = i - 1;
            } else {
                digits[0] = digits[0] - 1;
            }
        }

        for(int j = i + 1; j < l; j++) {
            digits[j] = 9;
        }

        // now we will convert the number
        int p10 = 1;
        int ans = 0;
        i = l - 1;
        while(i >= 0) {
            ans += digits[i] * p10;
            p10 = p10 * 10;

            i--;
        }

        return ans;
    }
}