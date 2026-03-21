bool isSafe(int i, int j,  vector<vector<bool>> &visited, int A, int B){
    return (i>=0 && i<=A && j>=0 && j<=B && !visited[i][j]);
}
bool dfs(int i, int j,  vector<vector<bool>> &visited, int A, int B){
    vector<int> dir {0,-1,1};
    if(i==A && j == B){
        return true;
    }
    visited[i][j] = true;
    for(int m = 0; m<3; m++){
        for(int n = 0; n<3; n++){
            if(n == 0 && m == 0){
                continue;
            }
            int x = i + dir[n];
            int y = j + dir[m];
            if(isSafe(x,y,visited,A,B)){
                if(dfs(x,y,visited,A,B)){
                    return true;
                }
            }
        }
    }
    return false;
}
string Solution::solve(int A, int B, int C, int D, vector<int> &E, vector<int> &F) {
    if(A==0 || B == 0){
        return "NO";
    }
    vector<vector<bool>> visited(A+1,vector<bool>(B+1,false));
    for(int i = 0 ; i<=A; i++){
        for(int j = 0; j<=B; j++){
            for(int k = 0; k<C; k++){
                if(sqrt(pow(E[k]-i,2) +pow(F[k]-j,2))<= D){
                    visited[i][j] = true;
                    break;
                }
            }
        }
    }

    if(visited[0][0] == true || visited[A][B] == true){
        return "NO";
    }

    return dfs(0,0,visited,A,B)?"YES":"NO";
}
