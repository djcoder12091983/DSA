// https://leetcode.com/problems/circle-and-rectangle-overlapping/
// Though it's accepted but it's a potential-TLE
// need to check mathematics behind it.

class Solution {

    // check x, y point inside circle
    boolean insideCircle(int radius, int xCenter, int yCenter, int x, int y) {
        int d1 = xCenter - x, d2 = yCenter - y;
        return d1 * d1 + d2 * d2 <= radius * radius;
    }

    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // as the points are discrete we can go with brute force approach
        // we will check all discrete points whether it inside the circle or not
        for(int i = x1; i <= x2; i++) {
            for(int j = y1; j <= y2; j++) {
                if(insideCircle(radius, xCenter, yCenter, i, j)) {
                    return true; // overlaps wtith circle
                }
            }
        }

        // no points inside the circle
        return false;            
    }
}