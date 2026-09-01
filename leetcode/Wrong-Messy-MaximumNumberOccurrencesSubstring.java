// https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/
// TODO: Wrong and bit messy so need to work on simkpler and clean logic as it also misses some substring as well

class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        // slide window
        int f[] = new int[26];
        int c = 0;
        Arrays.fill(f, 0);

        // the answer string frequency then we will get the maximum frequency
        Map<String, Integer> track = new HashMap<>();

        int n = s.length();
        int i = 0, j = 0;
        while(j < n) {
            int x = s.charAt(j) - 'a';
            f[x]++;
            if(f[x] == 1) {
                // first time entry
                c++;
            }

            int len = j - i + 1;
            if(c <= maxLetters && len <= maxSize) {
                if(len >= minSize) {
                    String str = s.substring(i, j + 1);
                    // System.out.println("1: " + str);
                    track.put(str, track.getOrDefault(str, 0) + 1);
                }

                j++;
            } else {
                // need to slide from left
                f[x]--; // undo current char
                if(f[x] == 0) {
                    c--;
                }
                x = s.charAt(i) - 'a';
                f[x]--;
                if(f[x] == 0) {
                    // removed
                    c--;
                }

                i++;

                len = j - i;
                String str = s.substring(i, j);
                // System.out.println("2: " + str);
                if(c <= maxLetters && len >= minSize) {
                    track.put(str, track.getOrDefault(str, 0) + 1);
                }
            }
        }

        // maximum frequency
        int ans = 0;
        for(int x : track.values()) {
            ans = Math.max(ans, x);
        }

        return ans;
    }
}