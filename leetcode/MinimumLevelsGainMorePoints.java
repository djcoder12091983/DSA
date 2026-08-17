// https://leetcode.com/problems/minimum-levels-to-gain-more-points/

class Solution {
    public int minimumLevels(int[] possible) {
        // start with 0th index for ALice and remaining assigned to Bob
        int alice = possible[0] == 0 ? - 1 : 1;
        int bob = 0;
        int N = possible.length;

        for(int i = 1; i < N; i++) {
            bob += possible[i] == 0 ? -1 : 1;
        }

        if(alice > bob) {
            // it's best possible move
            return 1;
        }

        for(int i = 1; i < N - 1; i++) {
            // now alice will keep on taking elements moving forward
            alice += possible[i] == 0 ? -1 : 1;
            bob -= possible[i] == 0 ? -1 : 1;

            if(alice > bob) {
                // it's best possible move
                return i + 1;
            }
        }

        return -1; // not possible
    }
}