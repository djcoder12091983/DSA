vector<int> createSpf(int n){
    vector<int> spf(n+1);
    for(int i = 2; i <= n; i++) {
        spf[i] = i;
    }
    for(int i = 2;i*i <= n ; i++){
        if(spf[i] == i){
            spf[i]   = i;
            for(int j = i*i ;j <= n ;j= j+i){
                if(spf[j] == j){
                    spf[j] = i;
                }
            }
        }
    }
    return spf;
}
vector<int> Solution::solve(vector<int> &A) {
    int n = A.size();
    vector<int> spf = createSpf(1000000);
    vector<int> countArr;
    for(int i = 0 ; i < n;i++){
        int num = A[i];
        int tot = 1;
        while(num > 1){
            int smallestFactor = spf[num];
            int count = 0;
            while(num%smallestFactor == 0){
                count++;
                num = num/smallestFactor;
            }
            tot = tot * (count+1);
        }
        countArr.push_back(tot);
    }
    return countArr;
}