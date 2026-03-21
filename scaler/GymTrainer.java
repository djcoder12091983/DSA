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
    // track how many trees exist in forest
    int tc = 0;
    
    void make(int N) {
        nodes = new DSUNode[N + 1];
        for(int i = 1; i <= N; i++) {
            nodes[i] = new DSUNode(i);
            tc++;
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
    void union(int x, int y) {
        DSUNode X = find(x);
        DSUNode Y = find(y);
        if(X == Y) {
            // can't be merged
            return;
        }
        
        if(X.rank < Y.rank) {
            // add X to Y
            tc--; // root merged
            X.parent = Y.parent;
        } else {
            // add Y to X
            tc--; // root merged
            Y.parent = X.parent;
            if(X.rank == Y.rank) {
                // it helps to do search in log(N) time
                X.rank++;
            }
        }
    }
    
    int size() {
        return tc;
    }
}

public class Solution {
    public int solve(int A, ArrayList<ArrayList<Integer>> B, ArrayList<ArrayList<Integer>> C) {
        
        DSU dsu = new DSU();
        dsu.make(A);
        
        int N = B.size();
        Set<Integer> track = new HashSet<>(); // in second set this track should not match
        for(int i = 0; i < N; i++) {
            ArrayList<Integer> row = B.get(i);
            int x = row.get(0), y = row.get(1);
            track.add(x);
            track.add(y);
            
            // union
            dsu.union(x, y);
        }
        
        N = C.size();
        boolean wrong = false;
        for(int i = 0; i < N; i++) {
            ArrayList<Integer> row = C.get(i);
            int x = row.get(0), y = row.get(1);
            
            if(track.contains(x) || track.contains(y)) {
                // two sets merged, wrong!!
                wrong = true;
                break;
            }
            
            dsu.union(x, y);
        }
        
        if(wrong) {
            // no diets combination created
            return 0;
        }
        
        // 2 power of size_of{1,2}
        int mod = 1000000007;
        int c = 1;
        N = dsu.size();
        for(int i = 0; i < N; i++) {
            c *= 2;
            if(c >= mod) {
                c %= mod;
            }
        }
        
        return c % mod;
    }
}
