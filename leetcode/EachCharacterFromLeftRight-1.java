// https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/
// reduced around 30 ms

class Solution {
    public int takeCharacters(String s, int k) {
        
        // maintain prefix frequency and right side position list
        // so based on left frequency we can collect minimum elements from right
        // to fullfill requirements and find minimum operations among all of them

        int freq[] = new int[3];
        Arrays.fill(freq, 0);
        int N = s.length();
        for(int i = 0; i < N; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // 3 characters position list
        int position[][] = new int[3][];
        for(int i = 0; i < 3; i++) {
            position[i] = new int[freq[i]];
        }

        int c[] = new int[3];
        Arrays.fill(c, 0);
        for(int i = N - 1; i >= 0; i--) {
            int x = s.charAt(i) - 'a';
            position[x][c[x]++] = i;
        }

        // left frequency
        int left[] = new int[3];
        Arrays.fill(left, 0);
        int i = -1;
        int ans = -1;
        while(i < N) {

            if(i >= 0) {
                left[s.charAt(i) - 'a']++;
            }

            int minpick = N;
            boolean possible = true;
            for(int j = 0; j < 3; j++) {
                if(k > left[j]) {
                    int req = k - left[j];
                    if(req <= position[j].length && position[j][req - 1] > i) {
                        // take farthest minimum distance from right
                        minpick = Math.min(minpick, position[j][req - 1]);
                    } else {
                        // not possible
                        possible = false;
                        break;
                    }
                }
            }

            if(possible) {
                int total = i + 1 + N - minpick;
                if(ans == -1) {
                    // first time
                    ans = total;
                } else {
                    ans = Math.min(ans, total);
                }

                if(minpick == N) {
                    // nothing selected from right side
                    // TODO can we stop the process
                    break;
                }
            }

            i++;
        }

        return ans;
    }
}