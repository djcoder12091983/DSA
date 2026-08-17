// https://leetcode.com/problems/next-greater-element-iii/

// note: it's similar to next permutation problem

class Solution {
    public int nextGreaterElement(int n) {
        // right most digit
        int prev = n % 10;
        n = n / 10;
        
        // now check next digit from rith left whenever we see some descreasing sequence
        // we can replace that minimum digit to find smaller one but greater than n
        // then remainining digits we can sort it to find further smallest

        int digits[] = new int[10]; // store visited digits from right to left
        int i = 0;
        digits[i++] = prev;
        int power = 10;

        while(n > 0) {
            int digit = n % 10;
            if(digit < prev) {
                // sequence break, replace with smallest one
                //System.out.println("1. " + n + " " + power + " " + digit + " " + prev);
                n *= power;
                n -= digit * power;
                // TODO we can't replace first digit which could be potential smallest
                // but we need to find suitable digit smallest X in stored digits arrray where such that X > current digit
                // TODO NEED to fix
                n += digits[0] * power;
                //System.out.println("Modified N: " + n);
                digits[0] = digit;

                break;
            } else {
                digits[i++] = digit;
                prev = digit;
            }

            n = n / 10;
            power *= 10;
        }

        if(n == 0) {
            // that means all digits from left to right is non-decreasing order
            // so we can't generate one
            return -1;
        }

        // now moodify N
        Arrays.sort(digits, 0, i);
        power = power / 10;
        int j = 0;
        while(j < i) {
            n += digits[j] * power;
            power /= 10;
            j++;
        }

        return n;
    }
}