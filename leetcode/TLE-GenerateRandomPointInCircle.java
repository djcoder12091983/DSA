// https://leetcode.com/problems/generate-random-point-in-a-circle
// TODO -- we can think of generate random points based on given inner sqaure

class Solution {

    // idea is like we will create a outer - sqaure box
    // we will keep on generating some points
    // if it's inside the circle then stop the process

    double centerx, centery;
    double radius;

    Random random = new Random();

    boolean inside(double x, double y) {
        double t1 = x - centerx;
        double t2 = y - centery;
        double dist = t1 * t1 + t2 * t2;
        return dist <= radius;
    }

    public Solution(double radius, double x_center, double y_center) {
        this.centerx = x_center;
        this.centery = y_center;
        this.radius = radius;
    }
    
    public double[] randPoint() {

        // outer - square range
        double x1 = centerx - radius;
        double x2 = centerx + radius;
        double y1 = centery - radius;
        double y2 = centery + radius;

        double xdiff = x2 - x1;
        double ydiff = y2 - y1;
        
        double ans[] = new double[2];
        boolean found = false;
        while(!found) {
			// as random double values may not always generate inside the circle
			// by given a outer square range
			// TODO -- we can think of generate random points based on given inner sqaure
            double x = x1 + random.nextDouble(xdiff);
            double y = y1 + random.nextDouble(ydiff);

            if(inside(x, y)) {
                ans[0] = x;
                ans[1] = y;
                found = true;
            }
        }

        return ans;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */