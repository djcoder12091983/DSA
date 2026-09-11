// https://leetcode.com/problems/rectangle-overlap/

class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int x11 = rec1[0], y11 = rec1[1];
        int x12 = rec1[2], y12 = rec1[3];

        int x21 = rec2[0], y21 = rec2[1];
        int x22 = rec2[2], y22 = rec2[3];

        // condition is broken to udnerstand the situation
        // NOTE: two rectangles that only touch at the corner or edges do not overlap

        // we will find negate condition
        if(x21 >= x12) {
            // second rectangle on right side
            return false;
        }

        if(x22 <= x11) {
            // second one is on left side
            return false;
        }

        if(y21 >= y12) {
            // second one is on top
            return false;
        }

        if(y22 <= y11) {
            // second one is on bottom
            return false;
        }

        return true;
    }
}