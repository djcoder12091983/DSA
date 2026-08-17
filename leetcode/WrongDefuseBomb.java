// https://leetcode.com/problems/defuse-the-bomb/

// TODO need to think how we can applu slide window in case of left side move

class Solution {
    public int[] decrypt(int[] code, int k) {
        // use slide window
        int N = code.length;
        int ans[] = new int[N];
        if(k == 0) {
            // edge case
            Arrays.fill(ans, 0);
            return ans;
        }
        
        int sum = 0;
        int direction = 1;
        if(k < 0) {
            k = k * -1;
            direction = -1 + N;
        }
        int l = direction % N;
        int r = direction % N;
        while(k > 0) {
            sum += code[r];
            r = (r + direction) % N;
            k--;
        }

        ans[0] = sum; // first window

        // slide window
        k = 1;
        while(k < N) {
            sum = sum - code[l] + code[r];
            ans[k++] = sum;
            l = (l + direction) % N;
            r = (r + direction) % N;
        }

        return ans;
    }
}