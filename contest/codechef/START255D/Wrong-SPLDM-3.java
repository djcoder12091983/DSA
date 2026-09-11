import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    
    static class Data {
        int idx;
        int next, previous;
        
        Data(int idx, int next, int previous) {
            this.idx = idx;
            this.next = next;
            this.previous = previous;
        }
    }
    
    static int grenade(final int A[], int N, int health) {
        // we will sort based on empty cells around it
        PriorityQueue<Data> targets = new PriorityQueue<>(new Comparator<Data>(){
            
            @Override
            public int compare(Data d1, Data d2) {
                int nonzero1 = 0;
                if(d1.next < N && A[d1.next] > 0) {
                    nonzero1++;
                }
                if(d1.previous >= 0 && A[d1.previous] > 0) {
                    nonzero1++;
                }
                
                int nonzero2 = 0;
                if(d2.next < N && A[d2.next] > 0) {
                    nonzero2++;
                }
                if(d2.previous >= 0 && A[d2.previous] > 0) {
                    nonzero2++;
                }
                
                return nonzero1 - nonzero2;
            }
        });
        
        for(int i = 0; i < N; i++) {
            if(A[i] == health) {
                targets.add(new Data(i, i + 1, i - 1));
            }
        }
        
        int launch = 0;
        while(!targets.isEmpty()) {
            Data data = targets.poll();
            int i = data.idx;
            if(A[i] == 0) {
                // already destroyed
                continue;
            }
            
            // dead and side effect
            A[i] = 0;
            boolean left = false, right = false;
            if(i - 1 >= 0 && A[i - 1] > 0) {
                A[i - 1] -= 1;
                left = true;
            }
            if(i + 1 < N && A[i + 1] > 0) {
                A[i + 1] -= 1;
                right = true;
            }
            
            // TRICK -- here we can add nex point which is affected
            if(left && i - 2 >= 0 && A[i - 2] == health) {
                targets.add(new Data(i - 2, i - 1, i - 3));
            }
            
            if(right && i + 2 < N && A[i + 2] == health) {
                targets.add(new Data(i + 2, i + 3, i + 1));
            }
            
            // if it becomes same health
            if(i - 1 >= 0 && A[i - 1] == health) {
                targets.add(new Data(i - 1, i, i - 2));
            }
            if(i + 1 < N && A[i + 1] == health) {
                targets.add(new Data(i + 1, i + 2, i));
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
