import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static long MOD = 1000000000 + 7;
    
    // generate all Fibonacci till max range -- 100000
    // also generate prefix sum to answer range queries
    static long[] fibonacci(int A[], int N) {
        int MAX = 100000;
        long F[] = new long[MAX + 1];
        F[1] = 1;
        F[2] = 1;
        for(int i = 3; i <= MAX; i++) {
            // apply mod to avoid overflow issue
            F[i] = (F[i - 1] + F[i - 2]) % MOD;
        }
        
        // build Fibonacci based prefix sum
        long FP[] = new long[N + 1];
        FP[0] = 0;
        for(int i = 1; i <= N; i++) {
            // apply mod to avoid overflow issue
            FP[i] = (FP[i - 1] + F[A[i - 1]]) % MOD;
        }
        
        return FP;
    } 
    
    static long[] solve(int A[], int N, int Q[][], int M) {
        
        long FP[] = fibonacci(A, N);
        
        // answer queries
        long res[] = new long[M];
        for(int i = 0; i < M; i++) {
            int L = Q[i][0], R = Q[i][1];
            res[i] = (FP[R] - FP[L - 1] + MOD) % MOD;
        }
        
        return res;
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        String tokens[] = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);
        
        
        int A[] = new int[N];
        tokens = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(tokens[i]);
        }
        
        int Q[][] = new int[M][2];
        for(int i = 0; i < M; i ++) {
            tokens = br.readLine().split(" ");
            Q[i][0] = Integer.parseInt(tokens[0]);
            Q[i][1] = Integer.parseInt(tokens[1]);
        }
        
        long res[] = solve(A, N, Q, M);
        
        for(int i = 0; i < M; i++) {
            out.print(res[i] + " ");
        }
        
        br.close();
        out.close();
	}
}
