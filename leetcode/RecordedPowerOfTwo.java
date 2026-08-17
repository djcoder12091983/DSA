// https://leetcode.com/problems/reordered-power-of-2/
class Solution {

    // check whether it's power of two or not
    boolean isPowerOfTwo(long n) {
        while(n > 1) {
            if((n & 1) != 0) {
                // odd
                return false;
            }
            n = n >> 1; // n = n/2
        }

        return true;
    }

    // count of digits of given n
    int countDigits(int n) {
        int c = 0;
        while(n > 0) {
            n = n/10;
            c++;
        }

        return c;
    }

    boolean reorderedPowerOf2(int n) {
        if(n == 1) {
            // handle edge case
            return true;
        }
        int l = countDigits(n);
        return generateAndCheck(n, 0, l);
    }

    // get digit for position from right side
    long digit(long n, int pos) {
        long rem = 0;
        while(pos >= 0) {
            rem = n % 10;
            n = n / 10;
            pos--;
        }
        return rem;
    }

    // given n we will swap the digit pos1 and pos2
    long swap(long n, int pos1, int pos2) {
        //long t = n;
        long d1 = digit(n, pos1);
        long d2 = digit(n, pos2);
        //System.out.println("Digit1: " + pos1 + " " + d1 + " Digit2: " + pos2 + " " + d2);
        n -= d1 * (long)Math.pow(10, pos1);
        n -= d2 * (long)Math.pow(10, pos2);
        n += d1 * (long)Math.pow(10, pos2);
        n += d2 * (long)Math.pow(10, pos1);
        //System.out.println(t + " swapping digit position: " + pos1 + " " + pos2 + " => " + n);
        return n;
    }

    // generate permutation and check whether it's power of two or not
    boolean generateAndCheck(long n, int pos, int l) {
        if(pos == l) {
            // this is one of my permutation
            System.out.println("Permutation: " + n);
            if(isPowerOfTwo(n)) {
                return true;
            } else {
                return false;
            }
        }

        // generate permuations by all digits on right side with the position
        boolean flag = false;
        // track ith digit visited set
        boolean visitedDigits[] = new boolean[10]; // 0 -9 digits, all are by default filled as false
        for(int i = pos; i < l; i++) {

            int d = (int)digit(n, i); // ith digit
            if(pos == l - 1 && d == 0) {
                // we will stop brining 0 at the beginning (left most -> (l - 1)) to avoid trailing 0's
                // we have considered the digit sequence from right to left
                continue;
            }

            if(pos == 0 && (d & 1) == 1) {
                // if trying to bring an odd digit to right most (least sigficant digit)
                // then it's not possible to be power of two
                continue;
            }

            // if ith digit visited again
            if(visitedDigits[d] == true) {
                // already visited at same level so skip it for unnecessary computation
                continue;
            }

            // swap ith digit with position digit
            n = swap(n, pos, i);

            flag = generateAndCheck(n, pos + 1, l);
            if(flag) {
                // no need to check for other permutations
                break;
            }

            // mark it as visited
            visitedDigits[d] = true;

            // backtrack
            n = swap(n, pos, i);
        }

        return flag;
    }
}