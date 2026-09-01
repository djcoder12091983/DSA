// https://www.codechef.com/problems/GRDJUMP

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int cost(int x, int c) {
        int v = x/2 * c; // jump by 2
        if(x % 2 == 1) {
            // odd
            v += c;
        }
        
        return v;
    }
    
    static int solve(int A, int B, int P, int Q, int R) {
        int ans = Integer.MAX_VALUE;
        // start with vertical
        for(int i = 0; i <= A; i++) {
            // vertical
            int c = cost(i, P);
            // diagonal
            int d = A - i;
            if(d > B) {
                c += B * R;
                c += cost(d - B, P); // vertical again
            } else {
                c += d * R; //diagonal
                c += cost(B - d, Q); // horizontal
            }
            
            ans = Math.min(ans, c);
        }
        
        return ans;
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        
        while(T > 0) {
            
            String tokens[] = reader.readLine().split(" ");
            int A = Integer.parseInt(tokens[0]);
            int B = Integer.parseInt(tokens[1]);
            int P = Integer.parseInt(tokens[2]);
            int Q = Integer.parseInt(tokens[3]);
            int R = Integer.parseInt(tokens[4]);
            
            int c1 = solve(A, B, P, Q, R); // normal shape
            int c2 = solve(B, A, Q, P, R); // reverse the shape and calculate the same
            System.out.println(Math.min(c1, c2));
            
            T--;
        }
	}
}
