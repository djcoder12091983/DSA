// https://leetcode.com/problems/largest-component-size-by-common-factor/

class Solution {

    static final int LIMIT = 100000; // as per input
    int spf[] = new int[LIMIT + 1]; // SPF array

    // all prime factors
    Set<Integer> factors(int n) {
        Set<Integer> f = new HashSet<>();
        if (n < 2 || n > LIMIT) {
            return f;
        }

        while (n > 1) {
            int cf = spf[n];
            f.add(cf);
            
            n /= cf;
        }
        return f;
    }

    // create SPF
    void createSPF() {
        for (int i = 1; i <= LIMIT; i++) {
            spf[i] = i;
        }

        for (int i = 4; i <= LIMIT; i += 2) {
            spf[i] = 2;
        }

        for (int i = 3; i * i <= LIMIT; i += 2) {
            if (spf[i] == i) {
                // smallest prime factor for all
                for (int j = i * i; j <= LIMIT; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
    }

    public int largestComponentSize(int[] A) {
        // instead of going with GCD pairs then connecting nodes and then find a lrgest component in undirected graph
        // i would like to explore like grouping numbers by their prime factors
        // return the count of largest group
        // NOTE: the idea is like GCD of two numbers also have prime factor

        // create SPF
        createSPF();

        // now group the result based on prime factors
        Map<Integer, Integer> fc = new HashMap<>();
        int ans = 1;
        for(int x : A) {
            Set<Integer> facts = factors(x);
            for(int fact : facts) {
                fc.put(fact, fc.getOrDefault(fact, 0) + 1);
                ans = Math.max(ans, fc.get(fact)); // maximize group length
            }
        }

        return ans;
    }
}