// https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target
// TODO - can we implementation better

class Solution {

    // fit larger than x
    int fit(int x, int f[]) {
        int j = x + 1;
        while(j < 26) {
            if(f[j] > 0) {
                f[j]--;
                break;
            }

            j++;
        }

        return j;
    }

    public String lexGreaterPermutation(String S, String T) {
        int N = S.length();
        
        // source frequency
        int f[] = new int[26];
        for(int i = 0; i < N; i++) {
            f[S.charAt(i) - 'a']++;
        }

        // now match with target
        StringBuilder ans = new StringBuilder();
        int i = 0;
        while(i < N - 1) {
            char ch = T.charAt(i);
            int x = ch - 'a';
            if(f[x] > 0) {
                // keep same as prefix as long as possible to make permuatation smallest possible
                ans.append(ch);
                f[x]--;
            } else {
                break;
            }

            i++;
        }

        // need to fit larger one, if that does not exist then we will go back till it finds one
        // either last character or mismatch found
        // last character anyways need to have larger oone n worst case
        int x = T.charAt(i) - 'a';
        int larger = fit(x, f);
        // System.out.println("L1: " + larger);
        if(larger == 26) {
            // then need to backtrack till it finds one
            int k = i - 1;
            while(k >= 0) {
                x = T.charAt(k) - 'a';
                // backtrack
                f[x]++;
                ans.deleteCharAt(k);
                
                larger = fit(x, f);
                // System.out.println("L2: " + larger);
                if(larger < 26) {
                    // found fit
                    ans.append(Character.toString(larger + 'a'));
                    break;
                }

                k--;
            }

            if(k < 0) {
                return ""; // not found
            }
        } else {
            ans.append(Character.toString(larger + 'a'));
        }

        // sort remaining characters
        i = 0;
        while(i < 26) {
            if(f[i] > 0) {
                for(int j = 0; j < f[i]; j++) {
                    ans.append(Character.toString(i + 'a'));
                }
            }
            i++;
        }

        return ans.toString();
    }
}