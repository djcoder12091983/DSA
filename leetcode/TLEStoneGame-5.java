// https://leetcode.com/problems/stone-game-v/description/

class Solution {
    public int stoneGameV(int[] A) {
        int N = A.length;
        // now we will apply DP
        HashMap<String, Integer> DP = new HashMap<>();
        return score(A, 0, N - 1, DP);
    }

    int score(int A[], int start, int end, HashMap<String, Integer> DP) {
        if(start == end) {
            return 0;
        }

        String key = start + "-" + end;
        if(DP.containsKey(key)) {
            // already computed
            return DP.get(key);
        }

        int t = 0;
        for(int i = start; i <= end; i++) {
            t += A[i];
        }

        // split rows
        int s = 0;
        int max = 0;
        for(int i = start; i < end; i++) {
            s = s + A[i];
            int left = s;
            int right = t - left;

            if(left < right) {
                // choose left
                // System.out.println("Left chosen -> [" + start + ", " + end + "] Left: " + left + " Right: " + right);
                max = Math.max(max, left + score(A, start, i, DP));
            } else if(left > right) {
                // choose right
                // System.out.println("Right chosen -> [" + start + ", " + end + "] Left: " + left + " Right: " + right);
                max = Math.max(max, right + score(A, i + 1, end, DP));
            } else {
                // equal, then choose either one
                int max1 = left + score(A, start, i, DP);
                int max2 = right + score(A, i + 1, end, DP);
                max = Math.max(max, Math.max(max1, max2)); // take the max of two options
            }
        }

        // store the result for further use
        DP.put(key, max);

        return max;
    }
}