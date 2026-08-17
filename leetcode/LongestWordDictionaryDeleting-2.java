// https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/

class Solution {

    // is subsequence, s1 is subsequence of s2
    boolean isSubsequence(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length();
        if(l2 < l1) {
            return false; // not possible
        }
        int p1 = 0, p2 = 0;
        while(p1 < l1 && p2 < l2) {
            char x = s1.charAt(p1);
            char y = s2.charAt(p2);
            if(x == y) {
                p1++;
                p2++;
            } else {
                // i need to move my p2 only
                p2++;
            }
            // TODO only p1++ can be the part of if block we can get p2++ out of block, common part
        }

        return p1 == l1; // matched all characters
    }

    public String findLongestWord(String s, List<String> dictionary) {
        // now we can think of other way around like, i will iterate over the words array
        // then see current word is subsequence or using 2P approach then find maximized string

        // now we can sort it based on length and to handle duplciate length we can do lexicographical order
        // so once we find a result we will stop because further I can't find larger one

        Collections.sort(dictionary, new Comparator<String>() {

            @Override
            public int compare(String x, String y) {
                int c = y.length() - x.length();
                if(c == 0) {
                    return x.compareTo(y); // lexicographical order
                }

                return c;
            }
        });

        int N = dictionary.size();
        for(int i = 0; i < N; i++) {
            String s1 = dictionary.get(i);
            if(isSubsequence(s1, s)) {
                return s1; // find my best answer
            }
        }

        return "";
    }
}