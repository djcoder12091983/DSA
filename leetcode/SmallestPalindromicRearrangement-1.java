// https://leetcode.com/problems/smallest-palindromic-rearrangement-i/

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
            if(f[i] % 2 == 1) {
                odd = i;
            }

            for(int j = 0; j < f[i]/2; j++) {
                // TODO: without typecast what else we can do
                ans[k] = (char)(i + 'a');
                ans[N - 1 - k] = ans[k];
                k++;
            }
        }

        if(odd != -1) {
            // put that odd index chaarcter at the middle
            ans[k] = (char)(odd + 'a');
        }

        return String.valueOf(ans); // convert to string
    }
}