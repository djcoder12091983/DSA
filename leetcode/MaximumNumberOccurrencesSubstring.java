// https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/description/
// TODO: we can think binary search if minSize and maxSize is quite high
// need to think deep whether it will give any sort of benefits or what

class Solution {

    // will work on possible size
    void updateCount(String S, int limit, int size, Map<String, Integer> count) {
        int f[] = new int[26];
        Arrays.fill(f, 0);

        int i = 0, j = 0;
        int c = 0;
        while(j < size) {
            int x = S.charAt(j) - 'a';
            f[x]++;

            if(f[x] == 1) {
                c++;
            }

            j++;
        }

        if(c <= limit) {
            String str = S.substring(i, j);
            count.put(str, count.getOrDefault(str, 0) + 1);
        }

        int n = S.length();
        while(j < n) {
            int x = S.charAt(i) - 'a';
            f[x]--;
            if(f[x] == 0) {
                c--;
            }

            x =S.charAt(j) - 'a';
            f[x]++;
            if(f[x] == 1) {
                c++;
            }

            i++;
            j++;

            if(c <= limit) {
                String str = S.substring(i, j);
                count.put(str, count.getOrDefault(str, 0) + 1);
            }
        }
    }

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        // we will work on all possible substring in between minsie and maxsize

        // TODO: here we can think binary search if minSize and maxSize is quite high
        // need to think deep whether it will give any sort of benefits or what
        Map<String, Integer> count = new HashMap<>();
        for(int i = minSize; i <= maxSize; i++) {
            updateCount(s, maxLetters, i, count);
        }

        // maximum frequency
        int ans = 0;
        for(int x : count.values()) {
            ans = Math.max(ans, x);
        }

        return ans;
    }
}