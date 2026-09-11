// https://leetcode.com/problems/generate-random-point-in-a-circle
// TODO -- as points are inside the inner square so we may miss some points
// can we think some hybrid method where if fails to generate points given outer square range
// we can try to generate given inner square range

class Solution {

    // idea is like we will create a inner - sqaure box
    // we will keep on generating some points
    // if it's inside the circle then stop the process

    double centerx, centery;
    double radius;

    Random random = new Random();

    public Solution(double radius, double x_center, double y_center) {
        this.centerx = x_center;
        this.centery = y_center;
        this.radius = radius;
    }
    
    public double[] randPoint() {

        // inner square range
        double t = radius / Math.sqrt(2);
        double x1 = centerx - t;
        double x2 = centerx + t;
        double y1 = centery - t;
        double y2 = centery + t;

        double xdiff = x2 - x1;
        double ydiff = y2 - y1;
        
        double x = x1 + random.nextDouble(xdiff);
        double y = y1 + random.nextDouble(ydiff);

        return new double[]{x, y};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */