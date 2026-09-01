// https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii/

class Solution {
    public int minimumPushes(String word) {
        // greedy approach more frequency we can map at the beginning
        // to minimize the number of pushes

        int f[] = new int[26];
        Arrays.fill(f, 0);

        int N = word.length();
        for(int i = 0; i < N; i++) {
            int x = word.charAt(i) - 'a';
            f[x]++;
        }

        Arrays.sort(f);
        int i = 25;
        int pushes = 0;
        int idx = 0;
        while(i >= 0 && f[i] > 0) {

            // after coverting 8 keys next time pushes will be required +1
            pushes += f[i] * (idx / 8 + 1);

            i--;
            idx++;
        }

        return pushes;
    }
}