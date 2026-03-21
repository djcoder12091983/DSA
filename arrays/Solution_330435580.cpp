class Solution {
public:
    bool canJump(vector<int>& A) {
        // BFS based DP approach
        int N = A.size();
        bool steps[N];
        memset(steps, false, sizeof(steps));
        steps[0] = true;
        for(int i = 0; i < N; i++) {
            int limit = A[i];
            if(steps[i] && limit) {
                for(int j = 1; i + j < N && j <= limit; j++) {
                    steps[i + j] = true;
                }
            }
            // note: it could be used inside inner loop
            if(steps[N - 1]) {
                break;
            }
        }
        return steps[N - 1];
    }
};