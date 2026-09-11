import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int solve(int A[], int N, int K) {
        if(N == 1) {
            // edge case
            return 0;
        }
        
        // greedy
        Arrays.sort(A);
        
        // now idea is maximize the minimum and minimize the maximum
        // so we will have least difference
        
        
        // minimum is maximized
        A[0] += K;
        
        // TODO it may happen last element can be less than minum
        // after decreasing by k
        if(A[N - 1] >= K) {
            A[N - 1] -= K;
        } else {
            // if it's less than k then we need to increase it by k 
            A[N - 1] += K;
        }
        
        int min = Math.min(A[0], A[N - 1]);
        int max = Math.max(A[0], A[N - 1]);
        
        // the idea is we will try to transform all the numbers
        // in between A[0] and A[N - 1]
        // TODO it may happen it exceeds A[N - 1] still we wil get minimum difference
        for(int i = 1; i < N - 1; i++) {
            // TODO we need to still think the correctness
            
            
            // we will do both operations and see where it will fit best
            int t1 = A[i] + K;
            int t2 = A[i] - K;
            if(t2 < 0) {
                t2 = t1;
            }
            if(t1 >= min && t1 <= max) {
                A[i] = t1;
            } else if(t2 >= min && t2 <= max) {
                A[i] = t2;
            } else {
                // if both fails to fit inside min and max
                // then we will see the difference from and max
                if(t1 == t2) {
                    A[i] = t1; // we have no choice
                } else {
                    int d1 = t1 - max;
                    int d2 = min - t2;
                    if(d1 < d2) {
                        A[i] = t1;
                    } else {
                        A[i] = t2;
                    }
                }
            }
            
            // update min max after each update
            min = Math.min(min, A[i]);
            max = Math.max(max, A[i]);
        }
        
        /*
        min = A[0];
        max = A[0];
        for(int i = 0; i < N; i++) {
            min = Math.min(min, A[i]);
            max = Math.max(max, A[i]);
        }
        */
        
        return max - min;
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        String tokens[] = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int K = Integer.parseInt(tokens[1]);
        
        int A[] = new int[N];
        tokens = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(tokens[i]);
        }
        
        out.println(solve(A, N, K));
        
        br.close();
        out.close();
	}
}
