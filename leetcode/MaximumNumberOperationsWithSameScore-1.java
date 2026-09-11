// https://leetcode.com/problems/maximum-number-of-operations-with-the-same-score-i/

class Solution {
    public int maxOperations(int[] A) {
        int N = A.length;
        int ans = 0;

        int i = 0;
        int sum = -1;
        while(i < N - 1) {
            int x = A[i] + A[i + 1];
            if(sum == -1) {
                sum = x;
            } else {
                if(x != sum) {
                    break;
                }
            }

            ans++;
            i += 2;
        }

        return ans;
    }
}