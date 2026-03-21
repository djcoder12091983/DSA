public class Solution {
    public int largestRectangleArea(int[] h) {
        int max = 0;

        int l = h.length;
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < l; i++) {
            int h1 = h[i];
            if(s.isEmpty() || h[s.peek()] <= h1) {
                // keep on adding
                s.push(i);
            } else {
                // pop from stack till empty stack
                // or greater or equal height found
                while(!s.isEmpty() && h1 < h[s.peek()]) {
                    int h2 = h[s.pop()];
                    int area;
                    if(s.isEmpty()) {
                        area = h2 * i;
                    } else {
                        area = h2 * (i - s.peek() - 1);
                    }
                    
                    if(area > max) {
                        // new maximum area
                        max = area;
                    }
                }
                s.push(i);
            }
        }

        // if stack contains
        // pop from stack till empty stack
        // or greater or equal height found
        while(!s.isEmpty()) {
            int h2 = h[s.pop()];
            int area;
            if(s.isEmpty()) {
                area = h2 * l;
            } else {
                area = h2 * (l - s.peek() - 1);
            }

            if(area > max) {
                // new maximum area
                max = area;
            }
        }

        return max;
    }
}
