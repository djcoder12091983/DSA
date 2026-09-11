// https://leetcode.com/problems/find-maximum-area-of-a-triangle

class Solution {
    public long maxArea(int[][] coords) {
        // the idea is that for every x-parallel or y-parallel we will find 
        // maximum base and maximum height
        // To do that we will first group by x points for y-parallel and vice versa
        // then each group we will find base by taking min and max difference then 
        // find maximum distance for opposite co-ordinate by taking the distance from min and max value

        Map<Integer, int[]> xparallel = new HashMap<>(); // group by y value and track min max of x
        Map<Integer, int[]> yparallel = new HashMap<>(); // group by x value and track min max of y

        int minx = Integer.MAX_VALUE, maxx = 0;
        int miny = Integer.MAX_VALUE, maxy = 0;

        int N = coords.length;
        for(int i = 0; i < N; i++) {
            int x = coords[i][0];
            int y = coords[i][1];

            if(xparallel.containsKey(y)) {
                int[] minmax = xparallel.get(y);
                minmax[0] = Math.min(minmax[0], x);
                minmax[1] = Math.max(minmax[1], x);
            } else {
                xparallel.put(y, new int[]{x, x});
            }

            if(yparallel.containsKey(x)) {
                int[] minmax = yparallel.get(x);
                minmax[0] = Math.min(minmax[0], y);
                minmax[1] = Math.max(minmax[1], y);
            } else {
                yparallel.put(x, new int[]{y, y});
            }

            minx = Math.min(minx, x);
            maxx = Math.max(maxx, x);
            miny = Math.min(miny, y);
            maxy = Math.max(maxy, y);
        }

        // now iterate of xparallel and yparallel
        long ans = 0;
        for(int y : xparallel.keySet()) {
            int minmax[] = xparallel.get(y);
            int base = minmax[1] - minmax[0];
            int height = Math.max(Math.abs(y - miny), Math.abs(y - maxy));

            ans = Math.max(ans, 1L * base * height);
        }

        for(int x : yparallel.keySet()) {
            int minmax[] = yparallel.get(x);
            int base = minmax[1] - minmax[0];
            int height = Math.max(Math.abs(x - minx), Math.abs(x - maxx));

            ans = Math.max(ans, 1L * base * height);
        }

        if(ans == 0) {
            // no triangle exists
            return -1;
        }

        return ans;
    }
}