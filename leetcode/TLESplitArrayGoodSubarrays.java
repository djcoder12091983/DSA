// https://leetcode.com/problems/ways-to-split-array-into-good-subarrays/

class Solution {

    static final int MOD = 1000000000 + 7;

    public int numberOfGoodSubarraySplits(int[] A) {
        // we will use partition DP
        // now we will use DP
        HashMap<Integer, Long> DP = new HashMap<>();
        return Long.valueOf(count(A, 0, DP)).intValue();
    }

    long count(int A[], int idx, HashMap<Integer, Long> DP) {
        int N = A.length;
        if(idx == N) {
            return 1;
        }

        if(DP.containsKey(idx)) {
            // already computed
            return DP.get(idx);
        }

        // count all possbilities then sum up all possibilities
        // find the first 1 then split from there
        int i = idx;
        while(i < N) {
            if(A[i] == 1) {
                break; // split point
            }
            i++;
        }

        if(i == N) {
            // not possible further split
            return 0;
        }

        // split from point idx
        long c = count(A, i + 1, DP);
        i++;

        // wheneven we will see 1 then it should be part of further split
        while(i < N && A[i] != 1) {
            c = (c + count(A, i + 1, DP)) % MOD; // recursive call
            i++;
        }

        DP.put(idx, c); // store result for further use

        // note: applied MOD to avoid overflow issue
        return c;
    }
}