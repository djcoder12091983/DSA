// https://leetcode.com/problems/minimum-unique-word-abbreviation/

class Solution {

    // given position array and see whether that position subsequence unmatches or not
    boolean unmatchFind(String[] dictionary, List<Integer> idx, int positions[], String target) {

        int N = positions.length;
        for(int i = 0; i < N; i++) {
            int p1 = positions[i];
            char x = target.charAt(p1);
            
            // System.out.println("IDX: " + idx);
            int M = idx.size();
            List<Integer> tidx = new ArrayList<>();
            for(int j = 0; j < M; j++) {
                int p2 = idx.get(j);
                if(x == dictionary[p2].charAt(p1)) {
                    // character match
                    tidx.add(p2);
                }
            }

            if(tidx.size() == 0) {
                // unmatch found
                return true;
            }

            idx = tidx; // next index list
        }

        // matching found
        return false;
    }

    // abbreviate the word based on positions subsequence
    String abbreviate(int p[], String target) {
        int s = p.length;
        int l = target.length();
        String ab = "";
        if(p[0] > 0) {
            ab += p[0];
        }
        ab += target.charAt(p[0]);

        for(int i = 1; i < s; i++) {
            int diff = p[i] - p[i - 1] - 1;
            if(diff > 0) {
                ab += diff;
            }
            ab += target.charAt(p[i]);
        }
        if(p[s - 1] < l - 1) {
            ab += l - 1 - p[s - 1];
        }

        return ab;
    }

    public String minAbbreviation(String target, String[] dictionary) {
        List<Integer> idx = new ArrayList<>();
        // capture same length word index
        int l = target.length();
        int N = dictionary.length;
        for(int i = 0; i < N; i++) {
            if(dictionary[i].length() == l) {
                idx.add(i);
            }
        }

        if(idx.size() == 0) {
            return "" + l; // no same length word found
        }

        // bit mask for every length
        Map<Integer, List<Integer>> bitmask = new HashMap<>();
        int limit = 1 <<  l;
        for(int i = 1; i < limit; i++) {
            int bits = 0;
            for(int j = 0; j < l; j++) {
                int bit = i & (1 << j);
                if(bit > 0) {
                    bits++;
                }
            }

            if(!bitmask.containsKey(bits)) {
                bitmask.put(bits, new ArrayList<>());
            }
            bitmask.get(bits).add(i);
        }

        // System.out.println(bitmask);

        // try for every bitmask like 1 length to target word length
        String ans = target;
        for(int i = 1; i < l; i++) {
            for(int mask : bitmask.get(i)) {

                int positions[] = new int[i];
                int k = 0;
                for(int j = 0; j < l; j++) {
                    int bit = mask & (1 << j);
                    if(bit > 0) {
                        positions[k++] = j;
                    }
                }

                if(unmatchFind(dictionary, idx, positions, target)) {
                    // we have found the answer
                    String found = abbreviate(positions, target);
                    if(found.length() < ans.length()) {
                        // minimize the length
                        ans = found;
                    }
                }
            }
        }

        return ans; // this won't happen
    }
}