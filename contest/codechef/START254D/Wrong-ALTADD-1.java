// https://www.codechef.com/problems/ALTADD
// Wrong -- approach -- Need to FIX

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    
    // given an alternative sequence it will minimize the cost
    static int makeZero(int A[], int l, int r) {
        
        // TODO testing
        /*
        System.out.println("Current Array");
	    for(int i = 0; i < A.length; i++) {
	        System.out.print(A[i] + " ");
	    }
	    System.out.println("Left: " + l + " Right: " + r);
	    */
        
        // recurively we will solve it
        if(l > r) {
            return 0;
        }
        
        if(l == r) {
            int c = Math.abs(A[l]);
            A[l] = 0; // testing purpose
            return c;
        }
        
        // strategy is like find the min and then make that zero
        // then split two two halves and recursively solve it
        // note : find minimum of all absolute values
        
        int min = Integer.MAX_VALUE;
        int splitIdx = -1;
        for(int i = l; i <= r; i++) {
            int x = Math.abs(A[i]);
            if(x < min) {
                // find minimum of all absolute values
                splitIdx = i;
                min = x;
            }
        }
        
        
        // apply changes
        int idx = splitIdx;
        int req = -1 * A[idx];
        while(++idx <= r) {
            req = -1 * req;
            A[idx] += req;
        }
        
        idx = splitIdx;
        req = -1 * A[idx];
        while(--idx >= l) {
            req = -1 * req;
            A[idx] += req;
        }
        
        int op = Math.abs(A[splitIdx]);
        A[splitIdx] = 0; // for testing purpose
        
        // recursively call for left and right
        int op1 = makeZero(A, l, splitIdx - 1);
        int op2 = makeZero(A, splitIdx + 1, r);
        
        return op + op1 + op2;
    }
    
    static int solve(int A[], int N) {
        // we will choose different sign pair because this is how we can balance
        int i = 0, j = 0;
        int ans = 0;
        // TODO need to think can we make optimal from right or what
        while(j < N - 1) {
            
            boolean opposite = (A[j] < 0) ^ (A[j + 1] < 0);
            if(opposite) {
                // different sign
                j++;
            } else {
                ans += makeZero(A, i, j);
                j++;
                i = j;
            }
        }
        
        ans += makeZero(A, i, j); // last sequence
        
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
		    
		    // TODO testing
		    /*
		    System.out.println("Final Array");
		    for(int i = 0; i < N; i++) {
		        System.out.print(A[i] + " ");
		    }
		    */
		    
		    T--;
		}
	}
}
