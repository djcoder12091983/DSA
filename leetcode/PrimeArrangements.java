// https://leetcode.com/problems/prime-arrangements/

class Solution {

    boolean isPrime(int x) {
        int i = 2;
        while(i * i <= x) {
            if(x % i == 0) {
                return false;
            }
            i++;
        }

        return true;
    }

    static final int MOD = 1000000000 + 7;

    long f(int x) {
        long ans = 1;
        for(int i = 2; i <= x; i++) {
            ans = (ans * i) % MOD;
        }

        return ans;
    }

    public int numPrimeArrangements(int n) {
        // create permutation of non primes they can be suffled
        // and primes can shuffled within it's own position

        int pc = 0;
        for(int i = 2; i <= n; i++) {
            if(isPrime(i)) {
                pc++;
            }
        }

        long m1 = f(pc);
        long m2 = f(n - pc);

        return Long.valueOf((m1 * m2) % MOD).intValue();
    }
}