// https://leetcode.com/problems/minimum-cost-path-with-at-most-k-turns/
// TODO -- need to debug [[3,13,24,2,16],[9,7,38,21,39],[30,21,3,30,7],[37,4,14,34,4]] k = 3

class Solution {

    class Node {
        int x, y;
        int dist, len;
        // direction -1 means first time
        // or else it can be 0 or 1 -- 0 means HORIZONTAL 1 means VERTICAL
        int direction = -1;

        Node(int x, int y, int dist, int len, int direction) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.len = len;
            this.direction = direction;
        }
    }

    // explore valid possible moves
    List<int[]> next(int[][] G, int x, int y, int direction) {
        boolean horizontal = false, vertical = false;
        // decide next moves
        if(direction == -1) {
            // both direction we can move
            horizontal = true;
            vertical = true;
        } else if(direction == 0) {
            // currently horizontal so next is vertical
            vertical = true;
        } else {
            // currently vertical so next is horizontal
            horizontal = true;
        }

        List<int[]> moves = new ArrayList<>();
        int N = G.length, M = G[0].length;
        if(horizontal) {
            int t = y;
            // left
            int dist = 0;
            while(t > 0) {
                dist += G[x][t - 1];
                int[] move = {x, t - 1, dist, 0};
                moves.add(move);

                t--;
            }

            t = y;
            // right
            dist = 0;
            while(t < M - 1) {
                dist += G[x][t + 1];
                int[] move = {x, t + 1, dist, 0};
                moves.add(move);

                t++;
            }
        }

        if(vertical) {
            int t = x;
            // up
            int dist = 0;
            while(t > 0) {
                dist += G[t - 1][y];
                int[] move = {t - 1, y, dist, 1};
                moves.add(move);

                t--;
            }

            t = x;
            // down
            dist = 0;
            while(t < N - 1) {
                dist += G[t + 1][y];
                int[] move = {t + 1, y, dist, 1};
                moves.add(move);

                t++;
            }
        }

        return moves;
    }

    public int minCost(int[][] G, int k) {
        // we will use BFS + greedy single source shortest path

        // sort based on distance - Dijkstra - single source shortest path
        PriorityQueue<Node> search = new PriorityQueue<>((x, y) -> x.dist - y.dist);

        int N = G.length, M = G[0].length;

        boolean V[][] = new boolean[N][M]; // visited nodes
        for(int i = 0; i < N; i++) {
            Arrays.fill(V[i], false);
        }

        // TODO -- WHY initial turn length = -1
        search.add(new Node(0, 0, G[0][0], -1, -1)); // source node

        int ans = -1;
        while(!search.isEmpty()) {

            Node node = search.poll();
            int x = node.x, y = node.y;

            if(x == N - 1 && y == M - 1) {
                // found the destination
                ans = node.dist;
                break;
            }

            // System.out.println("x: " + x + " y: " + y + " V: " + V[x][y] + " L: " + node.len);

            if(V[x][y] == false && node.len < k) {
                // still we can explore because it's not visited yet because k is not reached yet

                V[x][y] = true; // mark it as visited
                // explore possible moves
                List<int[]> moves = next(G, x, y, node.direction);
                int l = moves.size();
                for(int i = 0; i < l; i++) {
                    int move[] = moves.get(i);
                    // System.out.println("Next x: " + move[0] + " y: " + move[1]);
                    search.add(new Node(move[0], move[1], node.dist + move[2], node.len + 1, move[3]));
                }
            }
        }

        return ans;
    }
}