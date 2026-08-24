// https://leetcode.com/problems/scramble-string/

class Solution {
    public boolean isScramble(String s1, String s2) {
        // TODO now we wil apply DP
        Map<String, Boolean> DP = new HashMap<>();
        return check(s1, s2, 0, s1.length() - 1, DP);
    }

    boolean same(String s1, String s2) {
        int f1[] = new int[26];
        int f2[] = new int[26];

        int N = s1.length();
        for(int i = 0; i < N; i++) {
            int x = s1.charAt(i) - 'a';
            int y = s2.charAt(i) - 'a';
            f1[x]++;
            f2[y]++;
        }

        for(int i = 0;i < 26; i++) {
            if(f1[i] != f2[i]) {
                return false; // not possible
            }
        }

        return true;
    }

    // s2 is always fix and s1 keeps on changing based on partition and all
    boolean check(String s1, String s2, int l, int r, Map<String, Boolean> DP) {
        if(l == r) {
            return s1.charAt(0) == s2.charAt(l);
        }

        if(!same(s1, s2.substring(l, r + 1))) {
            // if they don't share same character frequency anyways it's not possible
            return false;
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