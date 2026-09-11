import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static class Node {
        int dish;
        int owner;
        Node parent;
        int h = 1; // height for balancing
        
        long score;

        Node(int dish, int score) {
            this.dish = dish;
            this.owner = dish; // initially chef i owns only dish i
            this.score = score;
            this.parent = this; // self point
        }
        
        void changeOwner(int owner) {
            this.owner = owner;
        }
    }
    
    // union two nodes
    static boolean union(Node dish1, Node dish2) {
        Node p1 = find(dish1);
        Node p2 = find(dish2);

        if(p1 == p2) {
            return false; // invalid query
        }
        
        long score1 = p1.score;
        long score2 = p2.score;
        
        // System.out.println("D1: " + p1.dish + " D2: " + p2.dish + " S1: " + score1 + " S2: " + score2);
        
        if(score1 == score2) {
            // still valid query but will not merge
            return true;
        }
        
        // new owner
        int owner = score1 > score2 ? p1.owner : p2.owner;

        // otehrwise merge - always choose larger height for balance
        int h1 = p1.h;
        int h2 = p2.h;
        if(h1 >= h2) {
            p2.parent = p1;
            p1.h = Math.max(p1.h, 1 + p2.h);
            
            p1.changeOwner(owner); // change new owner
            p1.score = Math.max(p1.score, p2.score); // update score
        } else {
            p1.parent = p2;
            p2.h = Math.max(p2.h, 1 + p1.h);
            
            p2.changeOwner(owner); // change new owner
            p2.score = Math.max(p2.score, p1.score); // update score
        }
        
        return true; // valid query
    }
    
    static Node find(Node dish) {
        Node x = dish;
        while(x.parent != x) {
            x = x.parent;
        }

        return x;
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // PrintWriter out = new PrintWriter(System.out);
        
        int T = Integer.parseInt(br.readLine());
        while(T > 0) {
            
            int N = Integer.parseInt(br.readLine());
            
            String tokens[] = br.readLine().split(" ");
            
            Node[] dishes = new Node[N + 1];
            // first we will build the graph then do find and union
            for(int i = 1; i <= N; i++) {
                int score = Integer.parseInt(tokens[i - 1]);
                dishes[i] = new Node(i, score); 
            }
            
            // answer queries
            int Q = Integer.parseInt(br.readLine());
            for(int i = 0; i < Q; i++) {
                tokens = br.readLine().split(" ");
                int type = Integer.parseInt(tokens[0]);
                if(type == 0) {
                    int x = Integer.parseInt(tokens[1]);
                    int y = Integer.parseInt(tokens[2]);
                    // merge
                    boolean valid = union(dishes[x], dishes[y]);
                    if(!valid) {
                        System.out.println("Invalid query!");
                    }
                } else {
                    // find
                    int x = Integer.parseInt(tokens[1]);
                    Node dish = find(dishes[x]);
                    System.out.println(dish.owner);
                }
            }
            
            T--;
        }
        
        br.close();
        // out.close();
	}
}