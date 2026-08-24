// https://leetcode.com/problems/permutation-sequence/

class Solution {
    public String getPermutation(int n, int k) {
        // reverse of permutation rank
        // note: permutation tree is full complete N-ary tree
        // based on the position we will find which path it will take

        int f[] = new int[n + 1];
        f[0] = 1;
        for(int i = 1; i <= n; i++) {
            f[i] = f[i - 1] * i;
        }

        String ans = "";
        boolean v[] = new boolean[n + 1];
        Arrays.fill(v, false);
        int l = n;
        while(k > 0) {
            int q = k / f[n - 1];
            int rem = k % f[n - 1];
            if(rem > 0) {
                q++; // ceiling value
                k = rem; // remaining we will work on
            } else {
                k = 0; // we are done
            }

            for(int i = 1; i <= l; i++) {
                if(v[i] == false) {
                    q--;
                    if(q == 0) {
                        // visited
                        ans += i;
                        v[i] = true;

                        break;
                    }
                }
            }

            n--;
        }

        while(n > 0) {
            for(int i = l; i > 0; i--) {
                if(v[i] == false) {
                    // last not visited number
                    ans += i;
                    v[i] = true;

                    break;
                }
            }

            n--;
        }

        return ans;
    }
}