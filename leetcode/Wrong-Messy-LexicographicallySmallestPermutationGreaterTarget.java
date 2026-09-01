// https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target
// TODO: wrong approach and even it's fixed it may fail to handle some edge case

class Solution {
    public String lexGreaterPermutation(String S, String T) {
        int N = S.length();
        
        // source frequency
        int f[] = new int[26];
        for(int i = 0; i < N; i++) {
            f[S.charAt(i) - 'a']++;
        }

        // now match with target
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < N; i++) {
            char ch = T.charAt(i);
            int x = ch - 'a';
            if(f[x] > 0) {
                // keep same as prefix as long as possible to make permuatation smallest possible
                ans.append(ch);
                f[x]--;
            } else {
                // get mismatch now we will take all remaining characters then sort it in ascending order
                // to make permuatation smallest possible
                // note: here we need to put the smallest availble character
                break;
            }
        }

        for(int i = 0; i < 26; i++) {
            if(f[i] > 0) {
                for(int j = 0; j < f[i]; j++) {
                    ans.append(Character.toString(i + 'a'));
                }
            }
        }

        return ans.toString();
    }
}