// https://leetcode.com/problems/longest-substring-of-one-repeating-character/
// TODO wrong approach -- here a new segment trees to grow then this approach may be able to track
// what if max segment is broken by some mismatch character

class Solution {
    public int[] longestRepeating(String S, String Q1, int[] Q2) {
        // we will track two consecutive characters tracking by setting it 1 if they are same otherwise 0
        int N = S.length();
        // tracking 0/1 index, why treeset because it will help to find closest one to see how long same chaarcters span
        TreeSet<Integer> zero = new TreeSet<>();
        TreeSet<Integer> one = new TreeSet<>();

        char ch[] = S.toCharArray();

        zero.add(0); // first character does not have no previous one
        // max will track longest sequence
        int max = 1, c = 1;
        for(int i = 1; i < N; i++) {
            if(S.charAt(i - 1) == S.charAt(i)) {
                // same
                one.add(i);
                c++;
            } else {
                max = Math.max(max, c);
                zero.add(i);

                // reset c
                c = 1;
            }
        }

        max = Math.max(max, c); // last sequence

        // now we will execute Query
        N = Q1.length();
        int ans[] = new int[N];
        for(int i = 0; i < N; i++) {
            int idx = Q2[i];
            char x = Q1.charAt(i);

            // new character to update
            if(ch[idx] != x) {
                // before update remove index data
                if(idx == 0 || ch[idx] != ch[idx - 1]) {
                    // mismatch, so need to remove from 0
                    zero.remove(idx);
                } else {
                    one.remove(idx);
                }

                ch[idx] = x; // update it and also update index data

                boolean flag = idx == 0 || ch[idx] != ch[idx - 1];
                if(flag) {
                    zero.add(idx);
                } else {
                    one.add(idx);
                }

                // now check new character how long it spans on both side
                if(!flag) {
                    // it's 1
                    Integer left = zero.floor(idx);
                    Integer right = zero.ceiling(idx);
                    if(right == null) {
                        right = N;
                    }

                    // right exclusive and left inclusive 
                    int dist = right - left;
                    max = Math.max(max, dist); // new max updated if greater
                }
            }

            ans[i] = max;
        }

        return ans;
    }
}