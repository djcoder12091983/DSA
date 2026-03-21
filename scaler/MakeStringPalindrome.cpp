int Solution::solve(string A) {
    // ref: https://www.geeksforgeeks.org/minimum-characters-added-front-make-string-palindrome/
    string B = A;
    reverse(A.begin(), A.end());
    B.append(A);
    
    // now apply LPS of KMP algorithm
    int N = B.length();
    int l = A.length();
    int i = 0, j = l;
    int LPS[l];
    while(j < N) {
        if(B[i] == B[j]) {
            i++;
        } else {
            // reset
            i = 0;
            if(B[i] == B[j]) {
                i++;
            }
        }
        LPS[j - l] = i;
        j++;
    }
    //cout << l << " " << LPS[l - 1] << "\n";
    return l - LPS[l - 1];
}
