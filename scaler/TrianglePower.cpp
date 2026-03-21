typedef long long int LL;

// note: avoid division 2
typedef struct trinagle {
    LL x1, y1;
    LL x2, y2;
    LL x3, y3;
    LL A;
    
    LL t_area(LL x1, LL y1, LL x2, LL y2, LL x3, LL y3) {
        return llabs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
    }
    
    trinagle(LL x1, LL y1, LL x2, LL y2, LL x3, LL y3) {
        this->x1 = x1;
        this->y1 = y1;
        this->x2 = x2;
        this->y2 = y2;
        this->x3 = x3;
        this->y3 = y3;
        A = t_area(x1, y1, x2, y2, x3, y3);
    }
    
    // strictly inside trinagle
    bool inside_trinagle(LL x, LL y) {
        LL A1 = t_area(x, y, x2, y2, x3, y3);
        LL A2 = t_area(x1, y1, x, y, x3, y3);
        LL A3 = t_area(x1, y1, x2, y2, x, y);
        if(A1 && A2 && A3) {
            return A == A1 + A2 + A3;
        } else {
            return false;
        }
    }
} trinagle;

vector<int> Solution::solve(vector<vector<int> > &A) {
    int N = A.size();
    vector<trinagle> triangles;
    // form triangles
    for(int i = 0; i < N; i++) {
        for(int j = i + 1; j < N; j++) {
            for(int k = j + 1; k < N; k++) {
                LL x1 = A[i][0], y1 = A[i][1];
                LL x2 = A[j][0], y2 = A[j][1];
                LL x3 = A[k][0], y3 = A[k][1];
                triangles.push_back(trinagle(x1, y1, x2, y2, x3, y3));
            }
        }
    }
    
    vector<int> P(N - 2);
    for(int i = 0; i < N - 2; i++) {
        P[i] = 0;
    }
    // now check with each point whether they enclosed by selected triangle
    int L = triangles.size();
    for(int i = 0; i < L; i++) {
        int p = 0;
        for(int j = 0; j < N; j++) {
            LL x = A[j][0], y = A[j][1];
            bool ok = triangles[i].inside_trinagle(x, y);
            if(ok) {
                p++;
            }
        }
        P[p]++;
    }
    return P;
}
