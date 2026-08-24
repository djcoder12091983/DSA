// https://leetcode.com/problems/largest-component-size-by-common-factor/
// TODO TLE Fix

class Solution {

    int gcd(int x, int y) {
        if(y == 0) {
            return x;
        }

        return gcd(y, x % y);
    }

    public int largestComponentSize(int[] A) {
        // let's try brute force approach
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int N = A.length;
        for(int i = 0; i < N; i++) {
            List<Integer> connections = new ArrayList<>(2);
            graph.put(i, connections);
        }
        
        // TODO: may get TLE here
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                if(gcd(A[i], A[j]) > 1) {
                    // share common factor - bidirectional connection
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        // System.out.println("G: " + graph);

        // check for largest component using BFS
        boolean V[] = new boolean[N];
        Arrays.fill(V, false);

        int ans = 1;
        for(int i = 0; i < N; i++) {
            if(V[i] == false) {
                // not visited
                ans = Math.max(ans, bfs(graph, i, V));
            }
        }

        return ans;
    }

    // bfs and returns component size
    int bfs(Map<Integer, List<Integer>> graph, int node, boolean V[]) {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(node);
        V[node] = true; // mark it as visited
        int c = 0;
        while(!Q.isEmpty()) {
            node = Q.poll();
            c++; // number of nodes visited
            
            List<Integer> connections = graph.get(node);
            for(int x : connections) {
                if(V[x] == false) {
                     // not visited yet
                     Q.add(x);
                     V[x] = true; // mark it as visited right here to add duplicate nodes into Q
                }
            }
        }

        return c;
    }
}