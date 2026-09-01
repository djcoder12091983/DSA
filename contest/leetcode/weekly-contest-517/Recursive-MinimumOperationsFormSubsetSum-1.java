// https://leetcode.com/problems/minimum-operations-to-form-subset-sum-i/
// TODO -- solved by recursive call pruning
// TODO -- need to solve using iterative call to reduce execution latency

class Solution {

    // now we will apply DP
    long convert(int A[], int idx, int T, Map<String, Long> DP) {

        if(T == 0) {
            return 0;
        }

        long c = Integer.MAX_VALUE;
        int N = A.length;
        if(idx == N) {
            return c; // not possible
        }

        String key = idx + "|" + T;
        if(DP.containsKey(key)) {
            // already computed
            return DP.get(key);
        }

        // we not choose
        c = Math.min(c, convert(A, idx + 1, T, DP));
        if(c == 0) {
            // best possible answer
            DP.put(key, c); // store result for further use
            return c;
        }
        
        // we choose idx
        // explore all options multiply and division
        int x = A[idx];
        int op = 0;
        while(x <= T) {

            if(T - x >= 0) {
                c = Math.min(c, op + convert(A, idx + 1, T - x, DP));
                if(c == 1) {
                    // best possible answer
                    DP.put(key, c); // store result for further use
                    return c;
                }
            }

            x = x * 2;
            op++;
        }

        x = A[idx];
        op = 0;
        while(x > 0) {
            
            if(T - x >= 0) {
                c = Math.min(c, op + convert(A, idx + 1, T - x, DP));
                if(c == 1) {
                    // best possible answer
                    DP.put(key, c); // store result for further use
                    return c;
                }
            }
            
            x = x / 2;
            op++;
        }

        DP.put(key, c); // store result for further use

        return c;
    }
    
    public int minOperations(int[] A, int sum) {

        // now we will apply DP
        Map<String, Long> DP = new HashMap<>();
        
        long ans = convert(A, 0, sum, DP);
        if(ans >= Integer.MAX_VALUE) {
            return -1; // not possible
        } else {
            return Long.valueOf(ans).intValue(); // minimized one
        }
    }
}