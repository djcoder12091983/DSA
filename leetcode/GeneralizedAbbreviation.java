// https://leetcode.com/problems/generalized-abbreviation/
class Solution {

    // abbreviate the word based on positions subsequence
    String abbreviate(List<Integer> p, String target) {
        int s = p.size();
        int l = target.length();
        String ab = "";
        if(p.get(0) > 0) {
            ab += p.get(0);
        }
        ab += target.charAt(p.get(0));

        for(int i = 1; i < s; i++) {
            int diff = p.get(i) - p.get(i-1) - 1;
            if(diff > 0) {
                ab += diff;
            }
            ab += target.charAt(p.get(i));
        }
        if(p.get(s - 1) < l - 1) {
            ab += l - 1 - p.get(s - 1);
        }

        return ab;
    }

    public List<String> generateAbbreviations(String word) {

        int l = word.length();
        List<String> ans = new ArrayList<>();
        ans.add("" + l);

        // we will bitmask
        int limit = 1 <<  l;
        for(int i = 1; i < limit; i++) {
            int bits = 0;
            List<Integer> positions = new ArrayList<>(); // bit positions
            for(int j = 0; j < l; j++) {
                int bit = i & (1 << j);
                if(bit > 0) {
                    positions.add(j);
                }
            }

            ans.add(abbreviate(positions, word));
        }

        return ans;
    }
}