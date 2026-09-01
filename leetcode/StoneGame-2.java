// https://leetcode.com/problems/stone-game-ii/

class Solution {
    public int stoneGameII(int[] P) {
        // now we will use DP
        Map<String, int[]> DP = new HashMap<>();
        return score(P, 'A', 1, 0, DP)[0]; // alice first index score
    }

    int[] score(int P[], char player, int M, int idx, Map<String, int[]> DP) {
        int N = P.length;

        int limit = 2*M;
        if(N - idx <= limit) {
            // collect all
            int s = 0;
            for(int i = idx; i < N; i++) {
                s += P[i];
            }
            if(player == 'A') {
                return new int[]{s, 0};
            } else {
                return new int[]{0, s};
            }
        }

        String key = player + "|" + M + "|" + idx;
        if(DP.containsKey(key)) {
            return DP.get(key);
        }

        int alice = 0, bob = 0;
        int s = 0;
        if(player == 'A') {
            for(int i = idx; i < idx + limit; i++) {
                s += P[i];
                int x = i - idx + 1;

                int scores[] = score(P, 'B', Math.max(x, M), i + 1, DP);
                // when it's a alice turn we will maximize the alice score
                if(alice < s + scores[0]) {
                    alice = s + scores[0];
                    bob = scores[1];
                }
            }
        } else {
            for(int i = idx; i < idx + limit; i++) {
                s += P[i];
                int x = i - idx + 1;

                int scores[] = score(P, 'A', Math.max(x, M), i + 1, DP);
                // when it's a bob turn we will maximize the bob score
                if(bob < s + scores[1]) {
                    alice = scores[0];
                    bob = s + scores[1];
                }
            }
        }

        int res[] = {alice, bob};
        DP.put(key, res);

        return res;
    }
}