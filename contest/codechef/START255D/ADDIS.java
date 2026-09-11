import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int solve(int A[], int N) {
        // idea is take the maximum frequency
        // try to half ceiling value to same number
        
        int maxf = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
            
            maxf = Math.max(maxf, map.get(A[i])); // maximum frequency
        }
        
        int half = maxf / 2;
        if(maxf % 2 == 1) {
            // half ceiling value
            half++;
        }
        
        return half;
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T > 0) {
		    
		    int N = Integer.parseInt(br.readLine());
		    int A[] = new int[N];
		    
		    String tokens[] = br.readLine().split(" ");
		    
		    for(int i = 0; i < N; i++) {
		        A[i] = Integer.parseInt(tokens[i]);
		    }
		    
		    System.out.println(solve(A, N));
		    
		    
		    T--;
		}
	}
}
