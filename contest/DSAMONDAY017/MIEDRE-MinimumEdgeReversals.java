import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        
        String tokens[] = line.split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);
        
        // note: the set is used for tracking unique set of connections
        // because self loop and parallel edges are allowed
        
        // original graph
        Map<Integer, Set<Integer>> original = new HashMap<>();
        // this dummy graph is undirected, the idea is like when we see an edge
        // present in original graph then we will assign an edge 0
        // or else we wil add penalty 1
        // then sinngle source shortest path will return minimum number of reversal
        Map<Integer, Set<Integer>> dummy = new HashMap<>();
        
        for(int i = 1; i <= N; i++) {
            original.put(i, new HashSet<>(2));
            dummy.put(i, new HashSet<>(2));
        }
        
        for(int i = 0; i < M; i++) {
            line = br.readLine();
            tokens = line.split(" ");
            int u = Integer.parseInt(tokens[0]);
            int v = Integer.parseInt(tokens[1]);
            
            if(u == v) {
                // avoid self loop
                continue;
            }
            
            original.get(u).add(v); // one direction
            
            dummy.get(u).add(v);
            dummy.get(v).add(u);
        }
        
        // System.out.println("original: " + original);
        // System.out.println("Dummy: " + dummy);
        
        class Node {
            int x;
            int d;
            
            Node(int x, int d) {
                this.x = x;
                this.d = d;
            }
        }
        
        // now try to reach from 1 to N using dummy graph while no directed edge
        // exists there we will add penalty
        boolean v[] = new boolean[N + 1];
        Arrays.fill(v, false);
        
        int ans = -1; // not possible when N exists in a different component
        
        PriorityQueue<Node> Q = new PriorityQueue<>((x, y) -> x.d - y.d);
        Q.add(new Node(1, 0));
        
        while(!Q.isEmpty()) {
            
            Node node = Q.poll();
            int x = node.x;
            if(v[x] == true) {
                // already processed that node
                continue;
            }
            
            int d = node.d;
            
            if(x == N) {
                // we have reached
                ans = d; // minimum penalty
                break;
            }
            
            v[x] = true; // minimum possible distance
            
            // BFS
            for(int u : dummy.get(x)) {
                int w = 0;
                if(!original.get(x).contains(u)) {
                    // if directed path does not exist
                    w = 1; // penalty
                }
                // System.out.println("X: " + x + " -> U: " + u + " D: " + (d + w));
                Q.add(new Node(u, d + w));
            }
        }
        
        System.out.println(ans);

	}
}
