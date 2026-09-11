// https://leetcode.com/problems/valid-square/
// TODO ensure correctness!
// Though it's accepted, may I need to check points colinearity.

class Solution {

    int dist(int p1[], int p2[]) {
        int x = p1[0] - p2[0], y = p1[1] - p2[1];
        return x * x + y * y; // avoiding square root to eliminate floating error
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        // we will go with simple approach like
        // given four points we will have 6 edges 
        // and if four edges are same two diagonals follow diagoal rule
        int edges[] = new int[6];

        // all 6 edges
        edges[0] = dist(p1, p2);
        edges[1] = dist(p1, p3);
        edges[2] = dist(p1, p4);
        edges[3] = dist(p2, p3);
        edges[4] = dist(p2, p4);
        edges[5] = dist(p3, p4);

        Arrays.sort(edges);
        // expected first four value will be same
        // and last 2 will be same but diagonal should follow length rule
        for(int i = 0; i < 4; i++) {
            // note: edges are 0 then anways it's not possible
            if(edges[i] == 0 || edges[0] != edges[i]) {
                return false;
            }
        }

        if(edges[4] != edges[5]) {
            return false;
        }

        // now validate the length rule
        if(edges[4] != 2 * edges[0]) {
            return false;
        }

        return true;
    }
}