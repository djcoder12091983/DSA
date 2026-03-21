public class Solution {
    TreeMap<Integer, Integer> track = new TreeMap<>(); // track last element and sequence length
    Map<Integer, Integer> reverseTrack = new HashMap<>(); // reverse tracking
    public int findLIS(ArrayList<Integer> A) {
        int N = A.size();
        int max = 1;
        for(int i = 0; i < N; i++) {
            int a = A.get(i);
            // closed to a (stritctly increasing)
            Integer closed = track.floorKey(a - 1);
            int newl;
            if(closed == null) {
                // sequence length 1
                newl = 1;
            } else {
                // append @ last
                int l = track.get(closed);
                newl = l + 1;
            }
            if(reverseTrack.containsKey(newl)) {
                // remove old entry to make sequence length longer
                track.remove(reverseTrack.get(newl));
                reverseTrack.remove(newl);
            }
            track.put(a, newl);
            reverseTrack.put(newl, a);
            max = Math.max(newl, max);
        }
        return max;
    }
}
