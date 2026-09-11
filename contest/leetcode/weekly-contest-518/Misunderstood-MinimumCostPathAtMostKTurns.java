// https://leetcode.com/problems/minimum-cost-path-with-at-most-k-turns/
// TODO - Misunderstood the term K-turns with K-moves

class Solution {

    class Node {
        int x, y;
        int dist, len;

        Node(int x, int y, int dist, int len) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.len = len;
        }
    }

    static final int[][] MOVES = {
        // possible four directions
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public int minCost(int[][] G, int k) {
        // we will use BFS + greedy single source shortest path

        // sort based on distance - Dijkstra - single source shortest path
        PriorityQueue<Node> search = new PriorityQueue<>((x, y) -> x.dist - y.dist);

        int N = G.length, M = G[0].length;

        boolean V[][] = new boolean[N][M]; // visited nodes
        for(int i = 0; i < N; i++) {
            Arrays.fill(V[i], false);
        }

        search.add(new Node(0, 0, 0, 0)); // source node

        int ans = -1;
        while(!search.isEmpty()) {

            Node node = search.poll();
            int x = node.x, y = node.y;

            if(x == N - 1 && y == M - 1) {
                // found the destination
                ans = node.dist;
                break;
            }

            if(V[x][y] == false && node.len < k) {
                // still we can explore because it's not visited yet because k is not reached yet

                V[x][y] = true; // mark it as visited
                // explore 4 moves
                for(int i = 0; i < 4; i++) {
                    int x1 = x + MOVES[i][0], y1 = y1 = y + MOVES[i][1];
                    if(x1 >= 0 && x1 < N && y1 >=0 && y1 < M) {
                        // valid position
                        search.add(new Node(x1, y1, node.dist + G[x1][y1], node.len + 1));
                    }
                }
            }
        }

        return ans;
    }
}