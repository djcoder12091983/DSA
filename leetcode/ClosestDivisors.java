// https://leetcode.com/problems/closest-divisors/

class Solution {

    void find(int t, int ans[]) {
        int i = 1;
        while(i * i <= t) {
            if(t % i == 0) {
                int f1 = i, f2 = t / i;
                if(f2 - f1 < ans[1] - ans[0]) {
                    ans[0] = f1;
                    ans[1] = f2;
                }
            }

            i++;
        }
    }

    public int[] closestDivisors(int num) {
        int ans[] = new int[]{1, num + 1};
        find(num + 1, ans);
        find(num + 2, ans);

        return ans;
    }
}