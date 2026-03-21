class Solution {
public:
    int maxProfit(vector<int>& A) {
        int N = A.size();
        int i = 0;
        int profit = 0;
        while(i < N - 1) {
            // find suitable position to buy
            while((i < N - 1) && (A[i + 1] <= A[i])) {
                i++;
            }
            if(i == N - 1) {
                // can't buy or sell anymore
                break;
            }
            int buy = i++;
            // find suitable position to sell
            while((i < N) && (A[i] >= A[i - 1])) {
                i++;
            }
            int sell = i - 1;
            profit += A[sell] - A[buy];
        }
        return profit;
    }
};