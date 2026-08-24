// https://leetcode.com/problems/minimum-additions-to-make-valid-string/

// TODO this approach does not look into relative order

class Solution {
    public int addMinimum(String word) {
        // every window we will explore, we will move to next when some characters repeating
        // anyways when size > 3 then it at least one character will repeat by itself

        int f[] = new int[3];
        f[0] = f[1] = f[2] = 0;

        int N = word.length();
        int ans = 0;
        for(int i = 0; i < N; i++) {
            int x = word.charAt(i) - 'a';
            // count a, b, c frequency in the window
            f[x]++;

            if(f[0] > 1 || f[1] > 1 || f[2] > 1) {
                // one character is repeating, so we will update answer accordingly
                for(int j = 0; j < 3; j++) {
                    if(f[j] == 0) {
                        ans++; // missing character
                    }
                }

                f[0] = f[1] = f[2] = 0; // reset
                f[x]++; // window reset
            }
        }

        // we will update answer accordingly for last window
        for(int j = 0; j < 3; j++) {
            if(f[j] == 0) {
                ans++; // missing character
            }
        }

        return ans;
    }
}