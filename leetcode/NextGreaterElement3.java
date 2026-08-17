// https://leetcode.com/problems/next-greater-element-iii/description/

class Solution {
    public int nextGreaterElement(int n) {
        // to handle edge case like if convrted number can't be fit into 32 bit then we need to return -1 as well
        long N = n;
        
        // right most digit
        long prev = N % 10;
        N = N / 10;
        
        // now check next digit from rith left whenever we see some descreasing sequence
        // we can replace that minimum digit to find smaller one but greater than n
        // then remainining digits we can sort it to find further smallest

        long digits[] = new long[10]; // store visited digits from right to left
        int i = 0;
        digits[i++] = prev;
        long power = 10;

        while(N > 0) {
            long digit = N % 10;
            if(digit < prev) {
                // sequence break, replace with smallest one
                //System.out.println("1. " + n + " " + power + " " + digit + " " + prev);
                N *= power;
                N -= digit * power;
                // TODO we can't replace first digit which could be potential smallest
                // but we need to find suitable digit smallest X in stored digits arrray where such that X > current digit
                // TODO NEED to fix

                long x = digits[0];
                // find smallest digit X > digit to replace with digit
                for(int j = 0; j < i; j++) {
                    if(digits[j] > digit) {
                        x = digits[j];
                        digits[j] = digit;
                        break;
                    }
                }

                N += x * power;

                break;
            } else {
                digits[i++] = digit;
                prev = digit;
            }

            N = N / 10;
            power *= 10;
        }

        if(N == 0) {
            // that means all digits from left to right is non-decreasing order
            // so we can't generate one
            return -1;
        }

        // now moodify N
        Arrays.sort(digits, 0, i);
        power = power / 10;
        int j = 0;
        while(j < i) {
            N += digits[j] * power;
            power /= 10;
            j++;
        }

        if(N > Integer.MAX_VALUE) {
            // does not fit fall back to -1
            return -1;
        }

        return new Long(N).intValue(); // int ocnversion
    }
}