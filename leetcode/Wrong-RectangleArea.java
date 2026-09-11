// https://leetcode.com/problems/rectangle-area/

class Solution {

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);
        
        // as the points are discrete we can go with brute force approach
        // we will check all discrete points whether it inside the other rectangle or not
        
        int common = 0;
        for(int i = ax1; i <= ax2; i++) {
            for(int j = ay1; j <= ay2; j++) {
                // TODO will check why this fails -- COMMON area IDEA!
                boolean inside = (i > bx1) && (i < bx2) && (j > by1) && (j < by2);
                if(inside) {
                    // overlapping area
                    common++;
                }
            }
        }

        return area1 + area2 - common; // we will subtract the common area
    }
}