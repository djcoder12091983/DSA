// https://leetcode.com/problems/find-the-number-of-good-pairs-ii/

class Solution {
    public long numberOfPairs(int[] A, int[] B, int k) {
        
        // ferquency map for B array * k
        // so we will iterate over A then check whether A's factor exists in frequency map or what
        Map<Long, Integer> fmap = new HashMap<>();
        
        for(int i = 0; i < B.length; i++) {
            long x = 1L * B[i] * k;
            fmap.put(x, fmap.getOrDefault(x, 0) + 1);
        }

        long c = 0;
        for(int i = 0; i < A.length; i++) {

            // check for factors exists in the map or what
            long x = 1, t = A[i];
            // TODO: here finding factors we can think of something better
            while(x * x <= t) {
                if(t % x == 0) {
                    // check for x exist
                    if(fmap.containsKey(x)) {
                        c += fmap.get(x);
                    }
                    long y = t/x;
                    if(x != y && fmap.containsKey(y)) {
                        c += fmap.get(y);
                    }
                }

                x++;
            }
        }

        return c;
    }
}