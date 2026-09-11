// https://leetcode.com/problems/smallest-integer-divisible-by-k/
// TODO -- Need to understand remainder cycle

class Solution {
    public int smallestRepunitDivByK(int k) {
        int p10 = 1;
        int n = 0;
        
        int len = 0;
        boolean remainder[] = new boolean[k];
        Arrays.fill(remainder, false);

        while(true) {
            n += p10;
            len++;

            int rem = n % k;
            if(rem == 0) {
                // found smallest number
                break; 
            }

            if(remainder[rem]) {
				// TODO -- Need to understand remainder Cyclicity!
                // already generated, same remainder
                // creatse a cycle so it won't lead a solution
                return -1;
            }

            n = rem;
            p10 = (p10 * 10) % k;
            remainder[rem] = true; // mark it as true
        }

        return len;
    }
}