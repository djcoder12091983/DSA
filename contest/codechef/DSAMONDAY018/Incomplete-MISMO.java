// https://www.codechef.com/problems/MISMO
// TODO -- incomplete

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    
    static long minimumSmoke(int A[], int N, int l, int r) {
        if(l >= r) {
            // reach the base case
            return 0;
        }
        
        long min = Long.MAX_VALUE;
        for(int i = l; i < N - 1; i++) {
            
            long score = 1L * A[i] * A[i + 1];
            int mix = (A[i] + A[i + 1]) % 100;
            
            // left call
            int l1 = l, r1 = i - 1;
            long lscore = minimumSmoke(A, N , l1, r1);
            
            // right call
            int l2 = r + 1, r1 = r;
            long rscore = minimumSmoke(A, N , l2, r2);
            
            // now we will merge current score with left and right score
            // TODO INCOMPLETE
            
        }
        
        return min;
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
        
        System.out.println(minimumSmoke(A, N, 0, N - 1));

	}
}
