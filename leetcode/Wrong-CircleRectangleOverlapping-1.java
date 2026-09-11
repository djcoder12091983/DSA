class Solution {

    // NOTE: two rectangles that only touch at the corner or edges DO overlap
    boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int x11 = rec1[0], y11 = rec1[1];
        int x12 = rec1[2], y12 = rec1[3];

        int x21 = rec2[0], y21 = rec2[1];
        int x22 = rec2[2], y22 = rec2[3];

        // System.out.println(x11 + " " + y11 + " " + x12 + " " + y12);
        // System.out.println(x21 + " " + y21 + " " + x22 + " " + y22);

        // condition is broken to udnerstand the situation
        // NOTE: two rectangles that only touch at the corner or edges DO overlap

        // we will find negate condition
        if(x21 > x12) {
            // second rectangle on right side
            return false;
        }

        if(x22 < x11) {
            // second one is on left side
            return false;
        }

        if(y21 > y12) {
            // second one is on top
            return false;
        }

        if(y22 < y11) {
            // second one is on bottom
            return false;
        }

        return true;
    }

    // check x, y point inside circle
    boolean insideCircle(int radius, int xCenter, int yCenter, int x, int y) {
        int d1 = xCenter - x, d2 = yCenter - y;
        return d1 * d1 + d2 * d2 <= radius * radius;
    }

    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // https://leetcode.com/problems/rectangle-area/description/
        // we will borrow the idea from here
        // first we will form a rectangle around circle and try to see whether it overlaps with other one

        int rec2[] = new int[]{x1, y1, x2, y2};
        int rec1[] = new int[]{xCenter - radius, yCenter - radius, xCenter + radius, yCenter + radius};

        boolean flag = isRectangleOverlap(rec1, rec2);
        // if they don't overlap then anyways it will be always false
        if(!flag) {
            return false;
        }

        // but it overlaps then we will check four corners outside the circle or not
		// TODO need to think!
        int corners[][] = {
            {x1, y1},
            {x2, y2},
            {x1, y2},
            {x2, y1}
        };

        for(int i = 0; i < 4; i++) {
            if(insideCircle(radius, xCenter, yCenter, corners[i][0], corners[i][1])) {
                return true;
            }
        }

        return false;            
    }
}