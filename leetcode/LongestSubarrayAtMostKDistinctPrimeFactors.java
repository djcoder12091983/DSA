// TODO need to revist EOS + SPF + finding factors
// https://leetcode.com/problems/longest-subarray-with-at-most-k-distinct-prime-factors/description/

class Solution {

    static final int LIMIT = 100000; // as per input
    int spf[] = new int[LIMIT + 1]; // SPF array

    // all factors
    Map<Integer, Integer> factors(int n) {
        Map<Integer, Integer> f = new HashMap<>();
        if (n < 2 || n > LIMIT) {
            return f;
        }

        while (n > 1) {
            int cf = spf[n];
            f.put(cf, f.getOrDefault(cf, 0) + 1);
            
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
    
    public int longestSubarray(int[] A, int k) {
        // create SPF
        createSPF();

        // slide window keep on moving p2 as long number of distinct prime factors <= k
        // when it's > k then move p1

        int p1 = 0, p2 = 0;
        int N = A.length;

        int ans = 0;
        
        Map<Integer, Integer> distinct = new HashMap<>();
        while(p2 < N) {
            Map<Integer, Integer> f = factors(A[p2]);

            // System.out.println("F-1: " + A[p2] + " => " + f);

            // check how many are different than current window
            int c = 0;
            for(int x : f.keySet()) {
                if(!distinct.containsKey(x)) {
                    // new one
                    c++;
                }
            }

            int fc = c + distinct.size(); // new factor count
            // System.out.println("New FC: " + A[p2] + " -> " + fc);
            if(fc <= k) {
                // we are safe to move p2 and add all factors
                for(int x : f.keySet()) {
                    distinct.put(x, distinct.getOrDefault(x, 0) + f.get(x));
                }
                // System.out.println("Modified FM-1: " + distinct);
                
                ans = Math.max(ans, p2 - p1 + 1); // valid window to update

                p2++;
            } else {
                // out of window we need to move p1 and remove factors from window
                if(p1 == p2) {
                    // p2 not added yet so we need move your p1 and p2
                    // exclude this cutrrent element, anyways it can't be part of yout answer
                    // TODO need to explore this case in detail
                    p1++;
                    p2++;
                } else {
                    f = factors(A[p1]);
                    // System.out.println("F-2: " + A[p1] + " => " + f);
                    for(int x : f.keySet()) {
                        distinct.put(x, distinct.get(x) - f.get(x));
                        if(distinct.get(x) == 0) {
                            distinct.remove(x);
                        }
                    }

                    // System.out.println("Modified FM-2: " + distinct);

                    p1++;
                }
            }
        }

        return ans;
    }
}