public class Solution {
    
    class Envelope implements Comparable<Envelope> {
        int w, h;
        
        Envelope(int w, int h) {
            this.w = w;
            this.h = h;
        }
        
        @Override
        public int compareTo(Envelope e) {
            int c = this.w - e.w;
            if(c == 0) {
                return e.h - this.h;
            }
            return c;
        }
    }
    
    public int solve(int[][] A) {
        // sorting based on width then applying concepts of LIS
        int N = A.length;
        Envelope envelopes[] = new Envelope[N];
        for(int i = 0; i < N; i++) {
            envelopes[i] = new Envelope(A[i][0], A[i][1]);
        }
        // sorting based on width
        Arrays.sort(envelopes);
        // applying LIS (N^2)
        // subsequence length vs last envelope, length sorting is descending
        TreeMap<Integer, Envelope> track = new TreeMap<>(Collections.reverseOrder());
        for(int i = 0; i < N; i++) {
            Envelope e1 = envelopes[i];
            int newl = 1;
            for(int l : track.keySet()) {
                Envelope e2 = track.get(l);
                if(e1.w > e2.w && e1.h > e2.h) {
                    // found suitable one
                    newl = l + 1;
                    break;
                }
            }
            if(track.containsKey(newl)) {
                // replace old subsequence last envelope if needed
                Envelope e2 = track.get(newl);
                if(e2.compareTo(e1) < 0) {
                    // replace old entry and add new one
                    track.remove(newl);
                    track.put(newl, e1);
                }
            } else {
                track.put(newl, e1);
            }
        }
        
        return track.firstKey(); // maximum subsequence
    }
}
