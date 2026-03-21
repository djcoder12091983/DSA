// standard MST (Kruskal's algorithm) 

class DSUNode {
    int x, rank = 1;
    DSUNode parent;
    
    DSUNode(int x) {
        this.x = x;
        parent = this;
    }
}

// union find
class DSU {
    DSUNode nodes[];
    
    void make(int N) {
        nodes = new DSUNode[N + 1];
        for(int i = 1; i <= N; i++) {
            nodes[i] = new DSUNode(i);
        }
    }
    
    // find parent
    DSUNode find(int x) {
        DSUNode parent = nodes[x].parent;
        while(parent != nodes[x]) {
            x = parent.x;
            parent = nodes[x].parent;
        }
        
        return parent;
    }
    
    // union
    boolean union(int x, int y) {
        DSUNode X = find(x);
        DSUNode Y = find(y);
        if(X == Y) {
            // can't be merged
            return false;
        }
        
        if(X.rank < Y.rank) {
            // add X to Y
            X.parent = Y.parent;
        } else {
            // add Y to X
            Y.parent = X.parent;
            if(X.rank == Y.rank) {
                // it helps to do search in log(N) time
                X.rank++;
            }
        }
        
        return true;
    }
}

// graph edge
class Edge {
    int u, v;
    int w;
    
    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

public class Solution {
    
    static long MOD = 1000000007L;
    
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        int N = B.size();
        List<Edge> edges = new ArrayList<>(N);
        for(int i = 0; i < N; i++) {
            ArrayList<Integer> row = B.get(i);
            Edge e = new Edge(row.get(0), row.get(1), row.get(2));
            edges.add(e);
        }
        
        // sort them as per weight(greedy) in ascending order
        Collections.sort(edges, new Comparator<Edge>(){
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.w - e2.w;
            }
        });
        
        // intialize DSU
        DSU dsu = new DSU();
        dsu.make(A);
        
        // now find MST and get minimum cost
        long cost = 0L;
        for(int i = 0; i < N; i++) {
            Edge e = edges.get(i);
            int u = e.u, v = e.v;
            // using DSU find cycle and discard that
            if(dsu.union(u, v)) {
                // not a part of cycle
                cost += e.w;
                if(cost >= MOD) {
                    // modulo reduction
                    cost -= MOD;
                }
            }
        }
        
        return Long.valueOf(cost % MOD).intValue();
    }
}
