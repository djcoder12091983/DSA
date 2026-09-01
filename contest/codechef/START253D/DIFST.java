// https://www.codechef.com/problems/DIFST

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static final String NEW_LINE = System.lineSeparator();
    
    static List<int[]> solve(int A[], int N) {
        List<int[]> swaps = new ArrayList<>();
        
        // we will keep on swapping by obeying the rules
        // like when A[i] != i then we will try to place in it's original position
        // but wil skip if it does not obey the rule, and we will run the process till it becomes sorted
        
        boolean sorted = false;
        while(!sorted) {
            int i = 1;
            boolean flag = true;
            while(i <= N) {
                if(A[i] != i) {
                    flag = false; // not sorted yet
                    // try to place in it's position
                    int t = A[A[i]];
                    if(Math.abs(A[i] - t) >= Math.abs(i - A[i])) {
                        
                        swaps.add(new int[]{i, A[i]});
                        
                        int x = A[i];
                        A[i] = t;
                        A[x] = x;
                        
                        // here we will not move i because next we will try to swap
                        // newly brought element into position i
                    } else {
                        // we will do any of the next cycles
                        // when it will satisfy the rule
                        i++;
                    }
                } else {
                    // already positioned - so move on
                    i++;
                }
            }
            
            sorted = flag; // if not mismatch found then it's sorted
        }
        
        return swaps;
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        
        StringBuilder ans = new StringBuilder();
        while(T > 0) {
            
            int N = Integer.parseInt(reader.readLine());
            String tokens[] = reader.readLine().split(" ");
            
            int A[] = new int[N + 1];
            for(int i = 1; i <= N; i++) {
                A[i] = Integer.parseInt(tokens[i - 1]);
            }
            
            List<int[]> swaps = solve(A, N);
            ans.append(swaps.size()).append(NEW_LINE);
            for(int[] swap : swaps) {
                ans.append(swap[0]).append(' ');
                ans.append(swap[1]).append(NEW_LINE);
            }
            
            T--;
        }
        
        System.out.print(ans);
	}
}
