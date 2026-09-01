// https://leetcode.com/problems/smallest-palindromic-rearrangement-i/
// TODO: odd index handling -- it's not neccessary odd will be placed in middle

class Solution {
    public String smallestPalindrome(String s) {
        int f[] = new int[26];
        Arrays.fill(f, 0);

        int N = s.length();
        for(int i = 0; i < N; i++) {
            int x = s.charAt(i) - 'a';
            f[x]++;
        }

        // TODO need to think if S is having balanced character frrquency
        // then creation of palindome with maximum length

        // to create lexicographically smallest, we will put smaller characters first
        char ans[] = new char[N];
        int k = 0;
        int odd = -1; // this odd index is required to put in the middle
        for(int i = 0; i < 26; i++) {
            if(f[i] % 2 == 0) {
                // System.out.println("CH: " + ((char)(i + 'a')) + " F: " + f[i]);
                // put even frequency at end which is lexicographically smaller
                for(int j = 0; j < f[i]/2; j++) {
                    // TODO: without typecast what else we can do
                    ans[k] = (char)(i + 'a');
                    ans[N - 1 - k] = ans[k];
                    k++;
                }
            } else {
                odd = i;
            }
        }

        if(odd != -1) {
            // put odd freqeuncy character in the middle
            for(int i = 0; i < (f[odd] + 1)/2; i++) {
                // TODO: without typecast what else we can do
                ans[k] = (char)(odd + 'a');
                ans[N - 1 - k] = ans[k];
                k++;
            }
        }

        return String.valueOf(ans); // convert to string
    }
}