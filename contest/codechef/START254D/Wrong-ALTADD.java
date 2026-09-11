// https://www.codechef.com/problems/ALTADD
// TODO need to think edge case [1 -5 4 3 -6 2]

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    
    static int solve(int A[], int N) {
        // we will choose different sign pair because this is how we can balance
        int i = 0;
        int ans = 0;
        // TODO need to think can we make optimal from right or what
        while(i < N - 1) {
            int x = A[i];
            int y = A[i + 1];
            int t = x * y;
            if(t < 0) {
                // different signs we will work on pair
                // we will keep on making 0 from left side
                // TODO need to think can we make optimal from right or what
                int op = Math.abs(x);
                ans += op;
                if(y < 0) {
                    A[i + 1] = y + op;
                } else {
                    A[i + 1] = y - op;
                }
            } else {
                // treat individually
                ans += Math.abs(x);
            }
            
            i++;
        }
        
        ans += Math.abs(A[N - 1]); // last element conversion
        
        return ans;
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T > 0) {
		    
		    int N = Integer.parseInt(br.readLine());
		    
		    String tokens[] = br.readLine().split(" ");
		    int A[] = new int[N];
		    for(int i = 0; i < N; i++) {
		        A[i] = Integer.parseInt(tokens[i]);
		    }
		    
		    System.out.println(solve(A, N));
		    
		    T--;
		}
	}
}
