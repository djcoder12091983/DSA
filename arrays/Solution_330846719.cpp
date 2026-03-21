class Solution {
public:
    int maximalSquare(vector<vector<char>>& M) {
        int rows = M.size();
        if(rows == 0) {
            return 0;
        }
        bool col[rows];
        int cols = M[0].size();
        int max_sqr = 0;
        for(int i = 0; i < cols; i++) {
            int c1 = 0, t = 0;
            for(int j = 0; j < rows; j++) {
                col[j] = M[j][i] == '1';
                if(col[j]) {
                    t++;
                } else {
                    c1 = max(c1, t);
                    t = 0;
                }
            }
            c1 = max(c1, t);
            if(c1 == 0) {
                continue;
            }
            int l = 1;
            max_sqr = max(max_sqr, min(l, c1));
            for(int j = i + 1; j < cols; j++) {
                int c1 = 0, t = 0;;
                for(int k = 0; k < rows; k++) {
                    col[k] &= M[k][j] == '1';
                    if(col[k]) {
                        t++;
                    } else {
                        c1 = max(c1, t);
                        t = 0;
                    }
                }
                c1 = max(c1, t);
                if(c1 == 0) {
                    break;
                }
                l++;
                max_sqr = max(max_sqr, min(l, c1));
            }
        }
        return max_sqr * max_sqr;
    }
};