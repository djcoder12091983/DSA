class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>> &A){
        
        vector <int> ans;
        
        int n = A.size();
        int m = A[0].size();
        
        int L = 0;
        int R = m-1;
        int T = 0;
        int B = n-1;
        
        int limit = n*m;
        int c = 0;
        
        while(L<=R && T<=B){
            
            //cout << "T1: " << L << " " << R << "\n";
            //1
            for(int u=L;u<=R;u++){
                ans.push_back(A[T][u]);
            }
            T++;
            
            if(ans.size() >= limit) {
                break;
            }
            
            //print(ans);
            
            //cout << "T2: " << T << " " << B << "\n";
            //2
            for(int u =T;u<=B;u++){
                ans.push_back(A[u][R]);
            }
            R--;
            
            if(ans.size() >= limit) {
                break;
            }
            
            //print(ans);
            
            //cout << "T3: " << R << " " << L << "\n";
            //3
            for(int u=R;u>=L;u--){
                ans.push_back(A[B][u]);
            }
            B--;
            
            if(ans.size() >= limit) {
                break;
            }
            
            //print(ans);
        
            //4
            //cout << "T4: " << B << " " << T << "\n";
            for(int u=B;u>=T;u--){
                ans.push_back(A[u][L]);
            }
            L++;
            
            //print(ans);
        }
        
        return ans;
        
    }
    
    void print( vector <int> &ans) {
        for(int x : ans) {
            cout << x << " ";
        }
        cout<<"\n";
    }
};