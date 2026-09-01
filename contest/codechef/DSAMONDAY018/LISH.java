// https://www.codechef.com/DSAMONDAY018/problems/LISH

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int purchase(int A[], int N, int W) {
        // greedy, sort the A
        Arrays.sort(A);
        
        // to minimize answer we need to buy from maximum to minimum
        // till W is reached
        int i = N - 1;
        int c = 0;
        while(i >= 0 && W > 0) {
            
            if(W < A[i]) {
                c++;
                // we are done
                W = W - A[i];
                break;
            }
            
            int t = Double.valueOf(Math.ceil((1.0 * W) / A[i])).intValue();
            if(t > 2) {
                t = 2; // at most 2 units we can buy
            }
            c += t;
            
            W = W - t*A[i];
            i--;
        }
        
        if(W <= 0) {
             // possible to purchase
             return c;
        } else {
            return -1; // not possible
        }
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int W = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        
        String tokens[] = br.readLine().split(" ");
        int A[] = new int[N];
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(tokens[i]);
        }
        
        System.out.println(purchase(A, N, W));
	}
}
