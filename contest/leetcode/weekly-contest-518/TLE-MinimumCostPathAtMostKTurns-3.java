// https://leetcode.com/problems/minimum-cost-path-with-at-most-k-turns/
// TODO -- We may need to think of Bellman-Ford!

class Solution {

    class Node {
        int x, y;
        int dist, turns;
        // previous direction, it's -1 if it started
        // 0 HORIZONTAL, 1 VERTICAL
        int direction;

        List<Integer> V = new ArrayList<>(2); // visited cells

        Node(int x, int y, int dist, int turns, int direction) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.turns = turns;
            this.direction = direction;
        }

        void copy(List<Integer> V) {
            this.V.addAll(V);
        }
    }

    static final int[][] MOVES = {
        // possible four directions
        // 3rd values HORIZONTAL/VERTICAL -- 0/1
        {1, 0, 1}, {-1, 0, 1}, {0, 1, 0}, {0, -1, 0}
    };

    // whether x, y exists in V or not
    boolean contains(List<Integer> V, int x, int y) {
        int N = V.size();
        int i = 0;
        while(i < N) {
            if(V.get(i) == x && V.get(i + 1) == y) {
                return true;
            }

            i += 2;
        }

        return false;
    }

    public int minCost(int[][] G, int k) {
        // we will use BFS + greedy single source shortest path

        // sort based on distance - Dijkstra - single source shortest path
        // Note: but here the trick is once the destination reached may need to explore other paths
        // TODO also we could think of bellman-ford where we can explore all possible paths
        PriorityQueue<Node> search = new PriorityQueue<>((x, y) -> x.dist - y.dist);

        int N = G.length, M = G[0].length;

        Node start = new Node(0, 0, G[0][0], 0, -1);
        search.add(start); // source node

        int ans = Integer.MAX_VALUE;
        while(!search.isEmpty()) {

            Node node = search.poll();
            int x = node.x, y = node.y;

            if(x == N - 1 && y == M - 1) {
                // found the destination
                ans = Math.min(ans, node.dist);
                
                continue; // here we will not stop the process we may need to explore further paths
            }

            List<Integer> V = node.V; // visited cells stored at node
            if(contains(V, x, y) == false) {
                // still we can explore because it's not visited yet because k is not reached yet

                // mark it as visited
                V.add(x);
                V.add(y);
                
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

                        int dist = node.dist + G[x1][y1];
                        // Note: one more trick we have used if dist <= current answer then we are good to go
                        // other wise we can drop the path 
                        if(dist <= ans && turns <= k) {
                            // still valid turn -- as per given constraints
                            Node next = new Node(x1, y1, dist, turns, MOVES[i][2]);
                            next.copy(V); // stored V so that we can reexplore it later on
                            
                            search.add(next);
                        }
                    }
                }
            }
        }

        // Integer.MAX_VALUE (INF) means it's not possible
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}