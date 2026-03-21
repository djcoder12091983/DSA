class DSUNode {
    int x, rank = 1;
    long p;
    DSUNode parent;
    
    DSUNode(int x, long p) {
        this.x = x;
        this.p = p;
        parent = this;
    }
}

// union find
class DSU {
    DSUNode nodes[];
    Map<Integer, Long> powers = new HashMap<>();
    
    // initialization
    void make(ArrayList<Integer> B, int A) {
        nodes = new DSUNode[A + 1];
        for(int i = 1; i <= A; i++) {
            long p = B.get(i - 1);
            nodes[i] = new DSUNode(i, p);
            powers.put(i, p);
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
            // can't be merged, so no change of power
            return;
        }
        
        long t = X.p + Y.p; // merged power
        X.p = Y.p = t;
        if(X.rank < Y.rank) {
            powers.remove(X.parent.x); // remove power entry
            // add X to Y
            X.parent = Y.parent;
            
            // parent power update
            powers.put(Y.parent.x, t);
        } else {
            // add Y to X
            powers.remove(Y.parent.x); // remove power entry
            Y.parent = X.parent;
            if(X.rank == Y.rank) {
                // it helps to do search in log(N) time
                X.rank++;
            }
            
            // parent power update
            powers.put(X.parent.x, t);
        }
    }
}

public class Solution {
    public int solve(int A, ArrayList<Integer> B, ArrayList<ArrayList<Integer>> C, int D) {
        DSU groups = new DSU();
        groups.make(B, A);
        
        // combine groups
        int N = C.size();
        for(int i = 0; i < N; i++) {
            ArrayList<Integer> row = C.get(i);
            groups.union(row.get(0), row.get(1));
        }
        
        // valid group count
        int c = 0;
        Map<Integer, Long> powers = groups.powers;
        for(int g : powers.keySet()) {
            long p = powers.get(g);
            if(p >= D) {
                c++;
            }
        }
        
        return c;
    }
}
