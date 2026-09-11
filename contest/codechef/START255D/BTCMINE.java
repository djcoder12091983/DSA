import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    
    static int minimize(int coins, long earned, long X, long Y, Map<String, Integer> DP) {
        
        int t = coins + 1;
        long p1 = (earned + Y * t * t) - (X * t);
        long p2 = (earned + Y * coins * coins) - (X * coins);
        
        if(p1 > 0 || p2 > 0) {
            // reach the goal
            return 1;
        }
        
        String key = coins + "-" + earned;
        if(DP.containsKey(key)) {
            return DP.get(key);
        }
        
        int op1 = minimize(t, earned + Y * t * t, X, Y, DP);
        int op2 = minimize(coins, earned + Y * coins * coins, X, Y, DP);
        
        int day = 1 + Math.min(op1, op2);
        
        DP.put(key, day);
        
        return day;
    } 
    
    static int solve(int X, int Y) {
        
        // first day
        long spent = X, earned = Y;
        if(earned > spent) {
            return 1;
        }
        
        Map<String, Integer> DP = new HashMap<>();
        
        return 1 + minimize(1, earned, X, Y, DP);
        
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T > 0) {
		    
		    String tokens[] = br.readLine().split(" ");
		    int X = Integer.parseInt(tokens[0]);
		    int Y = Integer.parseInt(tokens[1]);
		    
		    System.out.println(solve(X, Y));
		    
		    
		    T--;
		}
	}
}
