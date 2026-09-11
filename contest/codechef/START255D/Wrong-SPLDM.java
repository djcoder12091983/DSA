import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static void addIndex(int x, int i, Map<Integer, Set<Integer>> health) {
        Set<Integer> index = health.get(x);
        if(index == null) {
            index = new HashSet<>();
            health.put(x, index);
        }
        
        index.add(i);
    }
    
    static void removeIndex(int x, int i, Map<Integer, Set<Integer>> health) {
        health.get(x).remove(i);
        if(health.get(x).isEmpty()) {
            health.remove(x);
        }
    }
    
    static int selectHealth(Map<Integer, Set<Integer>> health) {
        for(int i = 1; i <= 3; i++) {
            if(health.containsKey(i)) {
                return i;
            }
        }
        
        return -1; // empty
    }
    
    static void grenade(int x, int N, int A[], Map<Integer, Set<Integer>> health) {
        Iterator<Integer> i = health.get(x).iterator();
        int idx = i.next();
        
        // dead
        A[idx] = 0;
        removeIndex(x, idx, health);
        
        if(idx + 1 < N && A[idx + 1] > 0) {
            removeIndex(A[idx + 1], idx + 1, health); // remove from old value
            A[idx + 1] -= 1;
            if(A[idx + 1] > 0) {
                addIndex(A[idx + 1], idx + 1, health); // add with new value
            }
        }
        
        if(idx - 1 >= 0 && A[idx - 1] > 0) {
            removeIndex(A[idx - 1], idx - 1, health); // remove from old value
            A[idx - 1] -= 1;
            if(A[idx - 1] > 0) {
                addIndex(A[idx - 1], idx - 1, health); // add with new value
            }
        }
    }
    
    static int solve(int A[], int N) {
        // try greedy
        
        Map<Integer, Set<Integer>> health = new HashMap<>();
        for(int i = 0; i < N; i++) {
            addIndex(A[i], i, health);
        }
        
        int ans = 0;
        while(!health.isEmpty()) {
            
            int h = selectHealth(health);
            grenade(h, N, A, health);
            
            ans++;
            
        }
        
        return ans;
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
