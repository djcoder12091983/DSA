// https://leetcode.com/problems/find-palindrome-with-fixed-length/

class Solution {

    static final int POWER[] = new int[9];
    static {
        int p = 1;
        for(int i = 0; i < 9; i++) {
            POWER[i] = p;
            p = p * 10;
        }
    }

    // generate full length palindrom
    long generate(int n, int len) {
        // first half length palindrome then reverse to make full length
        int mid = (len + 1) / 2; // handle odd and even case
        
        // apart from root all other nodes contain 10 nodes [0-9]
        // int power = new Double(Math.pow(10, mid - 1)).intValue();
        int power = POWER[mid - 1];

        if(n > power * 9) {
            // handling boundary check
            return -1; // not exists
        }

        int first = (n / power) + 1; // root contains 9 level
        int rem = n % power;
        int half = first * power + rem - 1; // last level also contains [0-9]

        //System.out.println("N: " + n + " Half: " + half);

        // now reverse the first half
        // for even length we need to reverse half and odd length it will be half - one, leaving the middle one
        boolean even = (len & 1) == 0;
        int t = even ? half : (half / 10);
        
        // System.out.println("T: " + t);
        
        int rev = 0;
        while(t > 0) {
            rem = t % 10;
            rev = rev * 10 + rem;

            t = t / 10;
        }
        
        //System.out.println("Rev: " + rev);

        // now generate full length
        // for even we need to consider extra power
        return 1L * half * (even ? (power * 10) : power) + rev;
    }

    public long[] kthPalindrome(int[] Q, int len) {
        // the idea is quite simple like build the tree with half length
        // because half length number we will generate remaining half we will reverse to geenrate plaindrome
        // TODO if we generate the entire tree and then check which one is palindrome then it will faill like TLE or out of memory
        // now second optimization is that if build the tree of half lengthand generate all possible numbers accordingly
        // then also it may get TLE out out of memory bevcause it worst case tree depth can go to 8 as length is 15 and each level
        // having 10 nodes so rather we can track which path it takes to generate the number instead of building entire tree
        // Note: Tree is full and complete N-aray tree where N is 10 except root level, at root it's 9

        int N = Q.length;
        long ans[] = new long[N];
        for(int i = 0; i < N; i++) {
            // answer queries
            ans[i] = generate(Q[i], len);
        }

        return ans;
    }
}