// palindrome check
bool palindrome(string &A) {
    int N = A.length();
    for(int i = 0; i < N/2; i++) {
        if(A[i] != A[N - i - 1]) {
            // sequence break
            return false;
        }
    }
    return true;
}

// solve recusively
vector<vector<string>> solve(string &A, int start, int end) {
    // TODO [start,end] could be memoized
    vector<vector<string>> partitions;
    if(start > end) {
        // base case
        vector<string> row;
        partitions.push_back(row); // insert empty row
        
        return partitions;
    }
    for(int i = start; i <= end; i++) {
        string t = A.substr(start, i - start + 1);
        if(palindrome(t)) {
            // now check other possiblities
            vector<vector<string>> t_partitions = solve(A, i + 1, end);
            // result merge
            for(vector<string> row : t_partitions) {
                vector<string> new_row;
                new_row.push_back(t);
                for(string a : row) {
                    new_row.push_back(a);
                }
                
                partitions.push_back(new_row);
            }
        }
    }
    
    return partitions;
}

vector<vector<string>> Solution::partition(string A) {
    // solution
    int N = A.size();
    return solve(A, 0, N - 1);
}
