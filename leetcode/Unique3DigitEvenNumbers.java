// https://leetcode.com/problems/unique-3-digit-even-numbers/
// TODO -- Can we solve using Math Combanitorics technique!

class Solution {

    int count(int d[], int idx) {

        if(idx == 3) {
            // if last digit is even
            return (d[idx -1] % 2 == 0) ? 1 : 0; // valid permutation
        }

        boolean v[] = new boolean[10];
        Arrays.fill(v, false);
        
        int ans  = 0;
        int N = d.length;
        for(int i = idx; i < N; i++) {
            
            boolean select = true;
            if(idx == 0 && d[i] == 0) {
                select = false;
            }
            if(v[d[i]] == true) {
                select = false;
            }

            if(select) {

                // mark it visited
                // so that next we will not choose it to avoid duplicates
                v[d[i]] = true;
                
                // swap
                int t = d[idx];
                d[idx] = d[i];
                d[i] = t;
                
                ans += count(d, idx + 1);

                // re-swap backtrack
                t = d[idx];
                d[idx] = d[i];
                d[i] = t;
            }
        }

        return ans;
    }

    public int totalNumbers(int[] digits) {
        
        if(digits.length < 3) {
            return 0;
        }
        
        return count(digits, 0);
    }
}