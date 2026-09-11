// https://leetcode.com/problems/rectangle-area/

class Solution {

    static final int MAX_VALUE = 10000, MIN_VALUE = -10000;

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);
        
        // as the points are discrete we can go with brute force approach
        // we will check all discrete points whether it inside the other rectangle or not
        
        int minx = MAX_VALUE + 1, maxx = MIN_VALUE - 1;
        int miny = MAX_VALUE + 1, maxy = MIN_VALUE - 1;
        boolean overlap = false;
        for(int i = ax1; i <= ax2; i++) {
            for(int j = ay1; j <= ay2; j++) {
                boolean inside = (i >= bx1) && (i <= bx2) && (j >= by1) && (j <= by2);
                if(inside) {
                    overlap = true;
                    // System.out.println("Overlap: " + i + " " + j);
                    minx = Math.min(minx, i);
                    miny = Math.min(miny, j);
                    maxx = Math.max(maxx, i);
                    maxy = Math.max(maxy, j);
                }
            }
        }

        // System.out.println(area1 + " " + area2);
        // System.out.println((maxx - minx) + " " + (maxy - miny));

        int common = overlap ? (maxx - minx) * (maxy - miny) : 0;
        return area1 + area2 - common; // we will subtract the common area
    }
}