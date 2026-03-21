class Solution {
public:
    int majorityElement(vector<int>& A) {
        // standard moore voting algorithm
        int a = A[0];
        int c = 1;
        int n = A.size();
        for(int i = 0; i < n; i++) {
            if(A[i] == a) {
                c++;
            } else {
                c--;
            }
            if(c == 0) {
                a = A[i];
                c = 1;
            }
        }
        return a;
    }
};