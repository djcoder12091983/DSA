typedef long long int LL; 

class Solution {
private:
    vector<vector<int>> result;
    
    // recursive call to generate permuation
    void permute_call(vector<int> &A, int l, int r) { 
        if (l == r) {
            // store result
            vector<int> B;
            for(int a : A) {
                B.push_back(a);
            }
            result.push_back(B);
        } else { 
            // permutation made  
            for(int i = l; i <= r; i++) {
                // swapping
                swap(A[l], A[i]);
                // recursive call  
                permute_call(A, l + 1, r);
                // backtrack
                swap(A[l], A[i]);
            }
        }
    }
public:
    vector<vector<int>> permute(vector<int>& A) {
        // assume small set
        permute_call(A, 0, A.size() - 1);
        return result;
    }
};