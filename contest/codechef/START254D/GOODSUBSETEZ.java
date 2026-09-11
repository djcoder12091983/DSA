import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int bits(int x) {
        int c = 0;
        while(x > 0) {
            c++;
            x = x << 1;
        }
        
        return c;
    }
    
    static int solve(int A[], int N) {
        // group by same size bit if MSB is 1 then XOR < AND
        Map<Integer, Integer> map = new HashMap<>(); // length based frequecy
        Set<Integer> unique = new HashSet<>();
        
        int ans = 0;
        for(int i = 0; i < N; i++) {
            if(unique.contains(A[i])) {
                continue; // duplicate
            }
            
            unique.add(A[i]);
            
            int l = bits(A[i]);
            map.put(l, map.getOrDefault(l, 0) + 1);
            
            ans = Math.max(ans, map.get(l));
        }
        
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
