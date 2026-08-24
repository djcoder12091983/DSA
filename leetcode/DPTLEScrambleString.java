// https://leetcode.com/problems/scramble-string/
// TODO may need to think of pruning or some sort of smart split
// like they share the same prefix then we can directly split there

class Solution {
    public boolean isScramble(String s1, String s2) {
        // TODO now we wil apply DP
        Map<String, Boolean> DP = new HashMap<>();
        return check(s1, s2, 0, s1.length() - 1, DP);
    }

    // s2 is always fix and s1 keeps on changing based on partition and all
    boolean check(String s1, String s2, int l, int r, Map<String, Boolean> DP) {
        if(l == r) {
            return s1.charAt(0) == s2.charAt(l);
        }

        String key = s1 + '|' + l + '|' + r;
        if(DP.containsKey(key)) {
            // already computed
            return DP.get(key);
        }

        // now explore both option
        int N = s1.length();
        boolean possible = false;
        for(int i = 1; i < N; i++) {
            String p1 = s1.substring(0, i);
            String p2 = s1.substring(i, N);
            
            // swap
            boolean yes = check(p1, s2, l, l + i - 1, DP) & check(p2, s2, l + i, r, DP);
            if(!yes) {
                // opt for not swap, note: the start and end index
                int len = p2.length();
                yes = check(p2, s2, l, l + len - 1, DP) & check(p1, s2, l + len, r, DP);
                if(yes) {
                    possible = true;
                    break;
                }
            } else {
                possible = true; // done
                break;
            }
        }

        DP.put(key, possible); // store for further use

        // if any option not leading to result return false
        return possible;
    }
}