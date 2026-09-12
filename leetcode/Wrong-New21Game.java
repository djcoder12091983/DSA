https://leetcode.com/problems/new-21-game/
// TODO -- How to complute Probability!

class Solution {

    int solve(int points, int n, int k, int maxPts) {
        if(points >= k) {
            return 1; // always points will be less than <= n
        }

        int ans = 0;
        for(int i = 1; i <= maxPts; i++) {
            if(points + i <= n) {
                // valid score
                ans += solve(points + i, n, k, maxPts);
            } else {
                break;
            }
        }

        return ans;
    }

    public double new21Game(int n, int k, int maxPts) {
        int total = solve(0, n, k, maxPts);
		// TODO -- How to complute Probability!
        return 1.0 * total / maxPts;
    }
}