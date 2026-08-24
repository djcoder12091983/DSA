// https://leetcode.com/problems/line-reflection/

class Solution {

    // find the middle of all the xpoints so that each point have reflection
    double[] middle(Set<Integer> xpoints) {
        int N = xpoints.size();
        int points[] = new int[N];
        int i = 0;
        for(int x : xpoints) {
            points[i++] = x;
        }
        Arrays.sort(points);

        // now find the middle
        
        double mid = (points[0] + points[N - 1]) / 2.0;
        //System.out.println("Middle: " + mid);
        // in case of odd number of points median would be the middle
        if(N % 2 == 1 && points[N/2] != mid) {
            return new double[]{0, 0}; // mnot possible
        }

        i = 0;
        while(i < N/2) {
            double left = mid - points[i];
            double right = points[N - 1 - i] - mid;
            // System.out.println(left + " " + right + " " + (left != right));
            if(left != right) {
                // not a reflection
                return new double[]{0, 0};
            }

            i++;
        }

        return new double[]{mid, 1}; // middle is the reflection
    }

    public boolean isReflected(int[][] points) {
        // we will group x parallel points then we will try to balance all points
        // which grouped on a same like

        // first we will sort the bumbers based x axis then group based on same y co-ordinates
        // Arryas.sort(points, (p1, p2) -> p1[0] - p2[0]);

        // note: we will handle duplicate points easily, so we will store x points in set, grouped by y value
        // for find middle line we will sort there

        HashMap<Integer, Set<Integer>> xp = new HashMap<>();

        for(int point[] : points) {

            int y = point[1];
            if(!xp.containsKey(y)) {
                xp.put(y, new HashSet<>());
            }

            xp.get(y).add(point[0]);
        }

        // now iterate over all grouped x-parallel then seewhether they share common reflection (middle-line)
        Iterator<Set<Integer>> i = xp.values().iterator();
        double mid[] = middle(i.next());
        // System.out.println("Mid: " + mid);
        if(mid[1] == 0) {
            // middle not found
            return false;
        }

        while(i.hasNext()) {
            double midt[] = middle(i.next());
            if(midt[1] == 0 || midt[0] != mid[0]) {
                // either middle not found for current group or found middle is same as previous group
                return false;
            }
        }

        return true; // found the middle

    }
}