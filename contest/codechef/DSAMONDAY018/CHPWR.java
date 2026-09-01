// https://www.codechef.com/problems/CHPWR

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static double pour(int P, int R, int C) {
        double DP[][] = new double[R + 1][R + 1];
        
        DP[0][0] = Math.max(0, P);
        for(int i = 1; i <= R; i++) {
            
            // we will split by half after filling by 1 cup
            DP[i][0] = Math.max(0, DP[i - 1][0] - 1) / 2.0; // first column
            
            int idx = Math.min(R, i);
            for(int j = 1; j < idx; j++) {
                // we will split by half after filling by 1 cup
                DP[i][j] = Math.max(0, DP[i - 1][j - 1] - 1) / 2.0 + Math.max(0, DP[i - 1][j] - 1) / 2.0;
            }
            
            // we will split by half after filling by 1 cup
            DP[i][idx] = Math.max(0, DP[i - 1][idx - 1] - 1) / 2.0; // last column
        }
        
        /*
        for(int i = 0; i <= R; i++) {
            for(int j = 0; j <= C; j++) {
                System.out.print(DP[i][j] + " ");
            }
            System.out.println();
        }
        */
        
        return Math.min(1, DP[R][C]);
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String tokens[] = br.readLine().split(" ");
        int P = Integer.parseInt(tokens[0]);
        int R = Integer.parseInt(tokens[1]);
        int C = Integer.parseInt(tokens[2]);
        
        System.out.println(String.format("%.5f", pour(P, R, C)));

	}
}
