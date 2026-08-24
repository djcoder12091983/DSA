// https://leetcode.com/problems/count-array-pairs-divisible-by-k/

class Solution {

    static final int LIMIT = 100000; // as per input

    int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    public long countPairs(int[] A, int k) {
        int fmap[] = new int[LIMIT + 1]; // factors count map

        int N = A.length;
        long c = 0;
        for(int i = 0; i < N; i++) {
            // TODO: also same logic will for right to left
            // for every element we will look into left
            int x = gcd(A[i], k);
            if(x == k) {
                // then all pairs will be part of K-divisibility
                c += i;
                // System.out.println("1. " + A[i] + " -> " + c);
            } else {
                int y = k / x; // another factor we will look into left side for count contri
                c += fmap[y]; // so far what we have found on left side, stored into fmap
                // System.out.println("2. " + A[i] + " -> " + c + " FMAP: " + fmap[y]);
            }

            // find factors for current element and update into FMAP
            // SQRT algorithm, TODO: we can think of something better
            x = 1;
            while(x * x <= A[i]) {
                if(A[i] % x == 0) {
                    // factor count update
                    fmap[x]++;
                    int y = A[i] / x;
                    if(x != y) {
                        // avoid duplicate
                        fmap[y]++;
                    }
                }
                x++;
            }
        }

        return c;
    }
}