import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    
    static int solve(int A[], int N, int K) {
        
        if(K == N) {
            return 0; // all elements removed
        }
        
        // prefix and suffix sum to solve
        
        int P[] = new int[N];
        int S[] = new int[N];
        
        P[0] = A[0];
        for(int i = 1; i < N; i++) {
            P[i] = P[i - 1] + A[i];
        }
        
        S[N - 1] = A[N - 1];
        for(int i = N - 2; i >= 0; i--) {
            S[i] = S[i + 1] + A[i];
        }
        
        int ans = Integer.MIN_VALUE;
        for(int l = 0; l <= K; l++) {
            
            int r = K - l;
            int s = 0;
            if(l > 0) {
                s += P[l - 1];
            }
            if(r > 0) {
                s += S[N - r];
            }
            
            int rem = P[N - 1] - s;
            ans = Math.max(ans, rem);
        }
        
        return ans;

    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T > 0) {
		    
		    String tokens[] = br.readLine().split(" ");
		    int N = Integer.parseInt(tokens[0]);
		    int K = Integer.parseInt(tokens[1]);
		    
		    tokens = br.readLine().split(" ");
		    int A[] = new int[N];
		    for(int i = 0; i < N; i++) {
		        A[i] = Integer.parseInt(tokens[i]);
		    }
		    
		    System.out.println(solve(A, N, K));
		    
		    T--;
		}
	}
}
