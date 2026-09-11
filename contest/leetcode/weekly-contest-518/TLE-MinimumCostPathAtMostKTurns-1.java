// https://leetcode.com/problems/minimum-cost-path-with-at-most-k-turns/
// TODO -- Need to think how to prunce recursive calls
// TODO -- even with DP it fails to submit -- TLE!

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

    int solve(int[][] G, Node node, List<Integer> V, int k, Map<String, Integer> DP) {
        
        int x = node.x, y = node.y;
        int N = G.length, M = G[0].length;
        
        if(x == N - 1 && y == M - 1) {
            // found the destination
            return node.dist;
        }

        String key = "X:" + x + "Y:" + y + "T:" + node.turns + "V:" + V;
        if(DP.containsKey(key)) {
            return DP.get(key);
        }

        int ans = Integer.MAX_VALUE;

        // go through unvisited cell only
        if(!contains(V, x, y)) {
            
            V.add(x); // mark it as visited
            V.add(y);
            
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
                        ans = Math.min(ans, solve(G, next, V, k, DP));
                    }
                }
            }

            // UNDO -- Backtrack (so that we can explore other possibilities)
            int l = V.size();
            V.remove(l - 1);
            V.remove(l - 2);
        }

        DP.put(key, ans);

        return ans;
    }

    public int minCost(int[][] G, int k) {
        int N = G.length, M = G[0].length;

        List<Integer> V = new ArrayList<>();
        Map<String, Integer> DP = new HashMap<>();

        Node start = new Node(0, 0, G[0][0], 0, -1);
        int dist = solve(G, start, V, k, DP);

        if(dist == Integer.MAX_VALUE) {
            // not reachable
            return -1;
        }

        return dist; // return minimum distance
    }
}