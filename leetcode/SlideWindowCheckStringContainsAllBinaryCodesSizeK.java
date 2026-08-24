// https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/

class Solution {
    public boolean hasAllCodes(String s, int k) {

        int N = s.length();
        if(k > N) {
            return false; // anyways not possible
        }

        int limit = 1 << k;
        boolean found[] = new boolean[limit];
        Arrays.fill(found, false);

        // silde window of k and check whether all numbers from 0 to limit - 1 exists or not
        int x = 0;
        int i = 0;
        while(i < k) {
            x = x * 2 + (s.charAt(i) - '0');
            i++;
        }

        // first window number
        found[x] = true;

        // slide window
        int p1 = 0, p2 = k;
        while(p2 < N) {
            int b1 = s.charAt(p1) - '0';
            int b2 = s.charAt(p2) - '0';

            // remove p1
            x -= b1 * 1 << (k - 1);
            x = x << 1; // multiply by 2 so that all bits shift to right side
            x += b2; // add p2

            found[x] = true;

            p1++;
            p2++;
        }

        // check all numbers formed
        i = 0;
        while(i < limit) {
            if(found[i] == false) {
                // not all formed
                return false;
            }

            i++;
        }

        return true;

    }
}