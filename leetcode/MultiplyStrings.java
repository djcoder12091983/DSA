// https://leetcode.com/problems/multiply-strings/

class Solution {

    // update answer array by digit and power
    void update(int ans[], int digit, int power) {
        ans[power] += digit;

        int x = ans[power];
        int rem = x % 10;
        ans[power] = rem; // update modified digit to power box
        
        int carry = x / 10;
        if(carry > 0) {
            // recursively update carry to next power
            update(ans, carry, power + 1);
        }
    }

    public String multiply(String num1, String num2) {

        if(num1.equals("0") || num2.equals("0")) {
            // if anyone is 0
            return "0"; // returns 0
        }

        int n = num1.length(), m = num2.length();
        int ans[] = new int[n + m + 1]; // power based digit store
        Arrays.fill(ans, 0);

        // we will first reverse the numbers so that they will be aligned with power
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();

        for(int i = 0; i < n; i++) {
            int digit1 = num1.charAt(i) - '0';
            for(int j = 0; j < m; j++) {
                int digit2 = num2.charAt(j) - '0';
                
                int mul = digit1 * digit2;
                update(ans, mul, i + j); // udpate multiplication result into power box
            }
        }

        StringBuilder result = new StringBuilder();
        boolean nonzero = false;
        for(int i = n + m; i >= 0; i--) {
            if(ans[i] > 0 || nonzero == true) {
                // if the answer digit > 0 or if it's zero but already nonzero has been seen
                // then we can include 0
                nonzero = true;
                result.append(ans[i]);
            }
        }

        return result.toString();
    }
}