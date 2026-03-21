// note: using BIT, esy to implement
class NumArray {
private:
    int N;
    int *BIT;
    vector<int> A;
    
    // get sum @ index
    int get_sum(int index) {
        int sum = 0;
        index++;
        while(index) {
            sum += BIT[index];
            // parent node
            index -= index & (-index);
        }
        return sum;
    }
    
    // update node @ index
    void update_sum(int index, int a) {
        index++;
        while(index <= N) {
            BIT[index] += a;
            // update index to that of parent in update View
            index += index & (-index);
        }
    }
public:
    NumArray(vector<int>& A) {
        this->N = A.size();
        
        // BIT construction
        BIT = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            BIT[i] = 0;
        }
        for(int i = 0; i < N; i++) {
            this->A.push_back(A[i]);
            update_sum(i, A[i]);
        }
    }
    
    void update(int index, int a) {
        int change = a - A[index];
        A[index] += change;
        update_sum(index, change);
    }
    
    int sumRange(int l, int r) {
        return get_sum(r) - get_sum(l - 1);
    }
    
    ~NumArray() {
        delete[] BIT;
    }
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * obj->update(i,val);
 * int param_2 = obj->sumRange(i,j);
 */