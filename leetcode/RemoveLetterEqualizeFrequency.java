// https://leetcode.com/problems/remove-letter-to-equalize-frequency/

class Solution {

    boolean check(int f[]) {
        int x = -1;
        for(int i = 0; i < 26; i++) {
            if(f[i] > 0) {
                // non-zero frequency check
                if(x == -1) {
                    x = f[i];
                } else if(x != f[i]) {
                    // different frequency
                    return false; // not possible
                }
            }
        }

        // all are same
        return true;
    }

    public boolean equalFrequency(String s) {
        int N = s.length();
        int f[] = new int[26];
        Arrays.fill(f, 0);

        for(int i = 0; i < N; i++) {
            int x = s.charAt(i) - 'a';
            f[x]++;
        }

        // delete character by character then see whether the frequency is still same
        for(int i = 0; i < N; i++) {
            int x = s.charAt(i) - 'a';
            f[x]--;

            // check
            if(check(f)) {
                return true;
            }

            f[x]++; // undo
        }

        return false;
    }
}