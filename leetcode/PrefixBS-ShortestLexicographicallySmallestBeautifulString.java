// https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/
// Prefix sum - Binary Search -- TODO: slide window with clean code

class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        // we will try prefix sum with 1's and binary search to find a perfect window
        // every valid starts and ends with 1 because leading traling 0's will increase the length

        int N = s.length();
        int P[] = new int[N];
        P[0] = s.charAt(0) - '0';

        for(int i = 1; i < N; i++) {
            P[i] = P[i - 1] + (s.charAt(i) - '0');
        }

        String ans = s; // full length answer
        boolean found = false;
        for(int i = 0; i < N; i++) {
            int x = s.charAt(i) - '0';
            if(x == 1) {
                int req = P[i] + k - 1;
                // now apply BS on P to find req with smallest index
                int l = i;
                int r = N - 1;
                int idx = -1;
                while(l <= r) {
                    int mid = (l + r)/2;
                    if(P[mid] > req) {
                        r = mid - 1;
                    } else if(P[mid] < req) {
                        l = mid + 1;
                    } else {
                        // found
                        idx = mid;
                        r = mid - 1; // smallest index
                    }
                }

                if(idx != -1) {
                    found = true;
                    String str = s.substring(i, idx + 1);
                    int l1 = str.length();
                    int l2 = ans.length();
                    if(l1 < l2) {
                        ans = str;
                    } else if(l1 == l2) {
                        if(ans.compareTo(str) > 0) {
                            // lexicographically smaller
                            ans = str;
                        }
                    }
                }
            }
        }

        if(found) {
            return ans;
        } else {
            return ""; // not found
        }
    }
}