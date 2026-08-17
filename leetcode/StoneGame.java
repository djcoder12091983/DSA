// https://leetcode.com/problems/stone-game/

class Solution {
    public boolean stoneGame(int[] P) {
        int sum = 0;
        int N = P.length;
        for(int i = 0; i < N; i++) {
            sum += P[i];
        }

        HashMap<String, Integer> DP = new HashMap<>();

        // this will give alice score only
        int score = maxCollect(P, 0, N - 1, 'A', DP);
        return score > sum - score; // alice score is better than bob
    }

    // here player will alternate but computation would be always for alice only
    int maxCollect(int P[], int start, int end, char player, HashMap<String, Integer> DP) {
        if(start > end) {
            return 0;
        }

        // now we will apply DP
        String key = start + "-" + end;
        if(DP.containsKey(key)) {
            return DP.get(key);
        }

        int score;
        if(player == 'A') {
            // add score for alice only
            score = Math.max(P[start] + maxCollect(P, start + 1, end, 'B', DP), P[end] + maxCollect(P, start, end - 1, 'B', DP));
        } else {
            // for bob not adding the piles score
            score = Math.max(maxCollect(P, start + 1, end, 'A', DP), maxCollect(P, start, end - 1, 'A', DP));
        }

        // store for further use
        DP.put(key, score);

        return score;
    }
}