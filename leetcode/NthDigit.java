// https://leetcode.com/problems/nth-digit/

class Solution {

    int digit(long num, int idx) {
        int i = 0;
        while(num > 0) {
            int d = Long.valueOf(num % 10).intValue();
            if(idx == i) {
                return d;
            }

            num = num / 10;
            i++;
        }

        return -1; // dummy return
    }

    public int findNthDigit(int n) {
        if(n <= 9) {
            return n;
        }

        long target = n;
        long position = 9;
        long p9 = 9;
        long p10 = 1;
        int i = 1;
        // first we will find which block it belongs
        while(position < target) {
            i = i + 1;
            p9 = p9 * 10;
            p10 = p10 * 10;
            position +=  i * p9;
        }

        long move = target - (position - i * p9);
        long idx = move / i; // how many numbers we need to skip having i length
        // TODO -- need to think to generilze
        int rem = Long.valueOf(move % i).intValue();
        if(rem == 0) {
            return digit(p10 + idx - 1, 0); // last digit
        } else {
            return digit(p10 + idx, i - rem);
        }
    }
}