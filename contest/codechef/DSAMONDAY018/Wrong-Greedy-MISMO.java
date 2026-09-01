// https://www.codechef.com/DSAMONDAY018/problems/MISMO
// TODO need to ensure why greedy will work!

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    
    static long minimumSmoke(int A[], int N) {
		// try greedy, need to ensure how greedy will work
        if(N == 1) {
            return 0;
        }
        int p1 = 0, p2 = 1;
        int mod = (A[0] + A[1]) % 100;
        
        for(int i = 1; i < N - 1; i++) {
            int tmod = (A[i] + A[i + 1]) % 100;
            if(tmod < mod) {
                mod = tmod;
                p1 = i;
                p2 = i + 1;
            }
        }
        
        int B[] = new int[N - 1]; // modified array
        int j = 0;
        for(int i = 0; i < p1; i++) {
            B[j++] = A[i];
        }
        B[j++] = mod;
        for(int i = p2 + 1; i < N; i++) {
            B[j++] = A[i];
        }
        
        return 1L * A[p1] * A[p2] + minimumSmoke(B, N - 1);
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
        
        String tokens[] = br.readLine().split(" ");
        int A[] = new int[N];
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(tokens[i]);
        }
        
        System.out.println(minimumSmoke(A, N));
	}
}
