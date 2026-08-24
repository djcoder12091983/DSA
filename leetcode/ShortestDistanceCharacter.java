// https://leetcode.com/problems/shortest-distance-to-a-character/description/
// TODO need to solve in O(1) space and O(N) time

class Solution {
    public int[] shortestToChar(String s, char c) {
        // we will first track the position from right side and as we move to right
        // we will pop the position accordingly and update the closest position on left side

        // TODO without using tracking position we can think of moving your right pointer
        // when current index out of window

        int N = s.length();
        Stack<Integer> position = new Stack<>();
        for(int i = N - 1; i >= 0; i--) {
            if(s.charAt(i) == c) {
                position.push(i);
            }
        }

        int p1 = -1;
        int ans[] = new int[N];
        for(int i = 0; i < N; i++) {
            int x = s.charAt(i);

            if(x == c) {
                ans[i] = 0; // itself is the closest one
                p1 = i; // update left closest position

                // we need to pop from right side position if it's out of window size
                position.pop();
            } else {
                // now take minimum distance from left and right
                int left = N + 1, right = N + 1;
                if(p1 >= 0) {
                    left = i - p1;
                }
                if(!position.isEmpty()) {
                    right = position.peek() - i;
                }

                // at leats one 'c' exists so that ans[i] will have a valid answer
                ans[i] = Math.min(left, right);
            }
        }

        return ans;
    }
}