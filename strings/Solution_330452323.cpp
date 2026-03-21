class Solution {
public:
    int longestCommonSubsequence(string A, string B) {
        // standard 2D DP problem
        int l1 = A.length(), l2 = B.length();
        int LCS[l1 + 1][l2 + 1];
        for(int i = 0; i <= l1; i++) {
            for(int j = 0; j <= l2; j++) {
                LCS[i][j] = 0;
            }
        }
        for(int i = 1; i <= l1; i++) {
            for(int j = 1; j <= l2; j++) {
                if(A[i - 1] == B[j - 1]) {
                    LCS[i][j] = 1 + LCS[i - 1][j - 1];
                } else {
                    LCS[i][j] = max(LCS[i][j - 1], LCS[i - 1][j]);
                }
            }
        }
        return LCS[l1][l2];
    }
};