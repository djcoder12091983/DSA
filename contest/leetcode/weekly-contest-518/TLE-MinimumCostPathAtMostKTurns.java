// https://leetcode.com/problems/minimum-cost-path-with-at-most-k-turns/
// TODO -- Need to think how to prunce recursive calls

class Solution {

    static final int[][] MOVES = {
        // possible four directions
        // 3rd values HORIZONTAL/VERTICAL -- 0/1
        {1, 0, 1}, {-1, 0, 1}, {0, 1, 0}, {0, -1, 0}
    };

    class Node {
        int x, y;
        int dist, turns;
        // previous direction, it's -1 if it started
        // 0 HORIZONTAL, 1 VERTICAL
        int direction;

        Node(int x, int y, int dist, int turns, int direction) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.turns = turns;
            this.direction = direction;
        }
    }

    int solve(int[][] G, Node node, boolean V[][], int k) {
        
        int x = node.x, y = node.y;
        int N = G.length, M = G[0].length;
        
        if(x == N - 1 && y == M - 1) {
            // found the destination
            return node.dist;
        }

        /*
        // TODO we will handle while making calls recursively
        if(x >= 0 && x < N && y >= 0 && y < M) {
            return Integer.MAX_VALUE; // not possible
        }

        if(node.turns > k || V[x][y] == true) {
            // not possible
            return Integer.MAX_VALUE;
        }
        */

        int ans = Integer.MAX_VALUE;

        // go through unvisited cell only
        if(V[x][y] == false) {
            
            V[x][y] = true; // mark it as visited
            // explore 4 moves
            for(int i = 0; i < 4; i++) {
                int x1 = x + MOVES[i][0], y1 = y + MOVES[i][1];
                if(x1 >= 0 && x1 < N && y1 >= 0 && y1 < M) {
                    // valid position
                    int turns = node.turns;
                    if(node.direction != -1 && node.direction != MOVES[i][2]) {
                        // different turn
                        turns++;
                    }

                    if(turns <= k) {
                        // still valid turn -- as per given constraints
                        Node next = new Node(x1, y1, node.dist + G[x1][y1], turns, MOVES[i][2]);
                        ans = Math.min(ans, solve(G, next, V, k));
                    }
                }
            }

            V[x][y] = false; // UNDO -- Backtrack (so that we can explore other possibilities)
        }

        return ans;
    }

    public int minCost(int[][] G, int k) {
        
        int N = G.length, M = G[0].length;

        boolean V[][] = new boolean[N][M]; // visited nodes
        for(int i = 0; i < N; i++) {
            Arrays.fill(V[i], false);
        }

        Node start = new Node(0, 0, G[0][0], 0, -1);
        int dist = solve(G, start, V, k);

        if(dist == Integer.MAX_VALUE) {
            // not reachable
            return -1;
        }

        return dist; // return minimum distance
    }
}