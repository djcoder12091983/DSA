// https://leetcode.com/problems/stone-game-vii/
// TODO -- Need to work on it!

class Solution {

    int[] optimize(int A[], int start, int end, int playerIdx) {
        if(start > end) {
            // no more stones to remove
            return new int[]{0, 0};
        }

        int total = 0;
        for(int i = start; i <= end; i++) {
            total += A[i];
        }

        // explore two options
        int nextPlayer = 1 - playerIdx;
        int op1[] = optimize(A, start + 1, end, nextPlayer);
        int op2[] = optimize(A, start, end - 1, nextPlayer);

        // every player plays optimally
        int score1 = Math.max(total - A[start] + op1[playerIdx], total - A[end] + op2[playerIdx]);
        
        int scores[] = new int[2];
        scores[playerIdx] = score1;
        scores[nextPlayer] = Math.max(op1[nextPlayer], op2[nextPlayer]);

        return scores;
    }

    public int stoneGameVII(int[] stones) {

        int N = stones.length;
        /*
        int total = 0;
        for(int i = 0; i < N; i++) {
            total += stones[i];
        }
        */

        int scores[] = optimize(stones, 0, N - 1, 0);
        System.out.println(scores[0] + " " + scores[1]);
        return scores[0] - scores[1];
    }
}