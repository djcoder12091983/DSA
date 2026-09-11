import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int solve(int X, int Y) {
        
        // first day
        long spent = X, earned = Y;
        if(earned > spent) {
            return 1;
        }
        
        // now simulate
        int day = 2;
        long coins = 1;
        long prev = earned - spent; // previous profit
        while(true) {
            // if we buy
            long t = coins + 1;
            long profit = (earned + Y * t * t) - (spent + X);
            
            if(profit > prev) {
                // we will buy because of more profit
                coins = t;
                spent += X;
                earned += Y * t * t;
            } else {
                // we will not buy
                earned += Y * coins * coins;
            }
            
            if(earned - spent > 0) {
                // we have reached
                break;
            }
            
            prev = earned - spent;
            day++;
        }
        
        return day;
        
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
