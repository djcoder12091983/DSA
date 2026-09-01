// https://leetcode.com/problems/maximum-gap-between-stations/
// TODO INCOMPLETE here binary search is not required prefix tracks minimum position from left
// and suffix tracks minimum position from right so automatically it will create a maximum GAP

class Solution {
    public int maximumGap(String skill, String station) {
        // we will do binary search for every pairs to find suitable position 
        // which can maximum gap for give pairs
        // for every point whether that point is valid or not we need to look into left and right
        // so it will take Nlog(N) and for every every pairs it will be N^2log(N)
        
        // TODO : need to think more in detail how to optimize it
        // Note: now we can think of for every point during binary search we can use precomputed prefix and suffix
        // which can help to do in O(1) time as characters are lowercase - set size 26
        // frequency may not help always also we need to track the relative order

        Map<Character, List<Integer>> positions = new HashMap<>();
        int s = station.length();
        for(int i = 0; i < s; i++) {
            char ch = station.charAt(i);
            List<Integer> p = positions.get(ch);
            if(p == null) {
                p = new ArrayList<>(2);
                positions.put(ch, p);
            }
            p.add(i);
        }

        // TODO: ealier we thought of tracking frequencies
        // note: we will track minimum position in "station" string where a prefix and suffix of "skill" fit into
        int n = skill.length();
        int P[] = new int[n];

        // assuming a valid sequence always exists so j boundary check is not required
        
        int i = 0, j = 0;
        while(i < n) {
            if(skill.charAt(i) == station.charAt(j)) {
                // we will move both
                P[i] = j;
                i++;
                j++;
            } else {
                // wait i for next match
                j++;
            }
        }

        // suffix part
        int S[] = new int[n];
        i = n - 1;
        j = s - 1;
        while(i >= 0) {
            if(skill.charAt(i) == station.charAt(j)) {
                // we will move both
                P[i] = j;
                i--;
                j--;
            } else {
                // wait i for next match
                j--;
            }
        }

        // now apply binary search on every pairs
        // TODO INCOMPLETE here binary search is not required prefix tracks minimum position from left
        // and suffix tracks minimum position from right so automatically it will create a maximum GAP
    }
}