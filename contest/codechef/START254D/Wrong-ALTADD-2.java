// https://www.codechef.com/problems/ALTADD
// Approach is correct but need to take care of overflow issue -- so answer could be long
// Note: intuition is that is a number either positive or negative like +x or -x
// it should receive exactly x operations, it should not oscilate

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    
    // given an alternative sequence it will minimize the cost
    static int makeZero(int A[], int l, int r) {
        
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
        
        int ans = Math.abs(A[splitIdx]);
        A[splitIdx] = 0; // for testing purpose
        
        // we will split the array based on 0
        // and slove those recursively
        int i = l, j = l;
        while(j <= r) {
            if(A[j] == 0) {
                ans += makeZero(A, i, j - 1);
                j++;
                i = j;
            } else {
                j++;
            }
        }
        
        ans += makeZero(A, i, j - 1);
        
        return ans;
    }
    
    static int solve(int A[], int N) {
        // we will choose different sign pair because this is how we can balance
        int i = 0, j = 0;
        int ans = 0;
        // TODO need to think can we make optimal from right or what
        while(j < N - 1) {
            
            if(A[j] == 0) {
                // skip 0, it's already 0
                i++;
                j++;
            } else {
            
                boolean opposite = (A[j] < 0) ^ (A[j + 1] < 0);
                if(opposite && A[j + 1] != 0) {
                    // different sign and next element is not zero
                    j++;
                } else {
                    ans += makeZero(A, i, j);
                    j++;
                    i = j;
                }
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
		    
		    T--;
		}
	}
}
