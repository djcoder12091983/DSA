import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int solve(int N) {
        if(N == 1) {
            return 1;
        }
        
        int tie = N / 2; // two players can win same times
        int rem = N % 2; // 3rd player 
        
        // to make win 3rd player need to tie first
        // then + 1 another round 2rd player wins
        return N + tie - rem + 1;
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T > 0) {
		    
		    int N = Integer.parseInt(br.readLine());

		    System.out.println(solve(N));
		    
		    T--;
		}
	}
}
