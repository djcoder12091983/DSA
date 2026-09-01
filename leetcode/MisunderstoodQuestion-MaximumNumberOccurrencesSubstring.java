// https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/
// TODO may need to rewrite our logic as it's misunderstood the question

class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        // slide window
        int f[] = new int[26];
        int c = 0;
        Arrays.fill(f, 0);

        int n = s.length();
        int i = 0, j = 0;
        int ans = 0;
        while(j < n) {
            int x = s.charAt(j) - 'a';
            f[x]++;
            if(f[x] == 1) {
                // first time entry
                c++;
            }

            if(c <= maxLetters) {
                int len = j - i + 1;
                if(len >= minSize && len <= maxSize) {
                    ans = Math.max(ans, ans);
                }

                j++;
            } else {
                // need to slide from left
                f[x]--; // undo current char
                if(f[x] == 0) {
                    c--;
                }
                System.out.println("i: " + i + " j: " + j);
                x = s.charAt(i) - 'a';
                f[x]--;
                if(f[x] == 0) {
                    // removed
                    c--;
                }

                i++;

                // TODO need to think whether ans update is required or what
            }
        }

        return ans;
    }
}