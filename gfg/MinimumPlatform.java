/*package whatever //do not write package name here */

import java.util.*;
import java.io.*;

class GFG {
    
    static class Interval implements Comparable<Interval> {
        int start, end;
        
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        // based on start time
        @Override
        public int compareTo(Interval i) {
            return this.start - i.start;
        }
    }
    
    // solution
    static int solve(int A[], int B[], int N) {
        // create intervals
        Interval C[] = new Interval[N];
        for(int i = 0; i < N; i++) {
            C[i] = new Interval(A[i], B[i]);
        }
        
        // sort based on start time
        Arrays.sort(C);
        
        // now try to fit intervals to minimize platforms
        TreeMap<Integer, Integer> track = new TreeMap<>(); // track end time for each slots
        int slots = 0;
        for(int i= 0; i < N; i++) {
            Interval d = C[i];
            Integer slot = track.floorKey(d.start - 1); // try to fit in an exiting slot
            if(slot == null) {
                // slot not available, create new one
                slots++;
            } else {
                // remove old entry
                int t = track.get(slot);
                if(t == 1) {
                    track.remove(slot);
                } else {
                    track.put(slot, t - 1);
                }
            }
            // update end time
            int t = d.end;
            if(track.containsKey(t)) {
                track.put(t, track.get(t) + 1);
            } else {
                track.put(t, 1);
            }
        }
        return slots;
    }
    
	public static void main (String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(reader.readLine());
		while(--T >= 0) {
		    // for each test cases
		    int N = Integer.parseInt(reader.readLine());
		    int A[] = new int[N], B[] = new int[N];
		    StringTokenizer tokens = new StringTokenizer(reader.readLine());
		    for(int i = 0; i < N; i++) {
		        A[i] = Integer.parseInt(tokens.nextToken());
		    }
		    tokens = new StringTokenizer(reader.readLine());
		    for(int i = 0; i < N; i++) {
		        B[i] = Integer.parseInt(tokens.nextToken());
		    }
		    
		    // solution
		    System.out.println(solve(A, B, N));
		}
		
		reader.close();
	}
}