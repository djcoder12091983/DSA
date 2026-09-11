// https://leetcode.com/problems/angle-between-hands-of-a-clock/

class Solution {
    public double angleClock(int hour, int minutes) {
        // idea is like how many degrees each pointer travels
        // for minutes -- it's easy like each minute degree contribution in 360 angle
        double mdegree = 360.0/60 * minutes;
        // for hours -- it's bit tricky like each hour box crosses 30 degree
        // then each hour box 30 degree further broken down into minutes
        // meaning for each minute how hours pointer moves that's the delta part
        double hdegree = hour % 12 * 30 + 0.5 * minutes;

        double angle = Math.abs(mdegree - hdegree);
        if(angle > 180) {
            // then we need to consider remaining angle
            angle = 360 - angle;
        }

        return angle;
    }
}