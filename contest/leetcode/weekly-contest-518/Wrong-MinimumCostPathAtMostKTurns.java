// https://leetcode.com/problems/minimum-cost-path-with-at-most-k-turns/
// TODO 812/999 passed -- G = [[4,1,9],[3,2,5],[4,8,6]] k = 2 failed
// TRICKY! -- Turns and moves/edges are not equivalent -- Need deep analysis!

class Solution {

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

    static final int[][] MOVES = {
        // possible four directions
        // 3rd values HORIZONTAL/VERTICAL -- 0/1
        {1, 0, 1}, {-1, 0, 1}, {0, 1, 0}, {0, -1, 0}
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

        search.add(new Node(0, 0, G[0][0], 0, -1)); // source node

        int ans = -1;
        while(!search.isEmpty()) {

            Node node = search.poll();
            int x = node.x, y = node.y;

            if(x == N - 1 && y == M - 1) {
                // found the destination
                ans = node.dist;
                break;
            }

            if(V[x][y] == false) {
                // still we can explore because it's not visited yet because k is not reached yet

                V[x][y] = true; // mark it as visited
                // explore 4 moves
                for(int i = 0; i < 4; i++) {
                    int x1 = x + MOVES[i][0], y1 = y1 = y + MOVES[i][1];
                    if(x1 >= 0 && x1 < N && y1 >=0 && y1 < M) {
                        // valid position
                        int turns = node.turns;
                        if(node.direction != -1 && node.direction != MOVES[i][2]) {
                            // different turn
                            turns++;
                        }
                        if(turns <= k) {
                            // still valid turn -- as per given constraints
                            search.add(new Node(x1, y1, node.dist + G[x1][y1], turns, MOVES[i][2]));
                        }
                    }
                }
            }
        }

        return ans;
    }
}