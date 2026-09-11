import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    
    static int[] target(int i, int A[], int N) {
        int p[] = new int[2];
        p[0] = i;
        p[1] = 0;
        if(i - 1 >= 0 && A[i - 1] > 0) {
            p[1]++; // not an empty cell
        }
        if(i + 1 < N && A[i + 1] > 0) {
            p[1]++; // not an empty cell
        }
        
        return p;
    }
    
    static int grenade(int A[], int N, int health) {
        // we will sort based on empty cells around it
        PriorityQueue<int[]> targets = new PriorityQueue<>((p1, p2) -> p1[1] - p2[1]);
        
        for(int i = 0; i < N; i++) {
            if(A[i] == health) {
                targets.add(target(i, A, N));
            }
        }
        
        int launch = 0;
        while(!targets.isEmpty()) {
            int p[] = targets.poll();
            int i = p[0];
            if(A[i] == 0) {
                // already destroyed
                continue;
            }
            
            // dead and side effect
            A[i] = 0;
            if(i - 1 >= 0 && A[i - 1] > 0) {
                A[i - 1] -= 1;
            }
            if(i + 1 < N && A[i + 1] > 0) {
                A[i + 1] -= 1;
            }
            
            // if it becomes same health
            if(i - 1 >= 0 && A[i - 1] == health) {
                targets.add(target(i - 1, A, N));
            }
            if(i + 1 < N && A[i + 1] == health) {
                targets.add(target(i + 1, A, N));
            }
            
            launch++;
        }
        
        return launch;
    }
    
    static int solve(int A[], int N) {
        // grenade with target 1 2 and 3
        int launch = grenade(A, N, 1); 
        launch += grenade(A, N, 2);
        launch += grenade(A, N, 3);
        
        return launch;
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
