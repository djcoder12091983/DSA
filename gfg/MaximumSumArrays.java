// { Driver Code Starts
#include<bits/stdc++.h>
using namespace std;


 int max_path_sum(int [], int [], int , int );

int main()
{
    int T;
    cin>>T;

    while(T--)
    {
        int N,M;
        cin>>N>>M;
        fflush(stdin);
        int a[N],b[M];
        for(int i=0;i<N;i++)
            cin>>a[i];
        for(int i=0;i<M;i++)
            cin>>b[i];
        int result = max_path_sum(a,b,N,M);
        cout<<result<<endl;
    }
}

// } Driver Code Ends


int max_path_sum(int A[], int B[], int l1, int l2) {
    vector<int> sum_a, sum_b, common;
    int i = 0, j = 0;
    int ta = 0, tb = 0;
    // partitioning by common elements
    while(i < l1 && j < l2) {
        if(A[i] < B[j]) {
            ta += A[i];
            i++;
        } else if(A[i] > B[j]) {
            tb += B[j];
            j++;
        } else {
            sum_a.push_back(ta);
            sum_b.push_back(tb);
            common.push_back(A[i]);
            // reset
            ta = tb = 0;
            // move both
            i++;
            j++;
        }
    }
    if(i < l1) {
        sum_b.push_back(tb);
        while(i < l1) {
            ta += A[i];
            i++;
        }
        sum_a.push_back(ta);
    }
    if(j < l2) {
        sum_a.push_back(ta);
        while(j < l2) {
            tb += B[j];
            j++;
        }
        sum_b.push_back(tb);
    }
    // now choose greedily on each slot
    int N = common.size();
    int max_sum = 0;
    for(i = 0; i < N; i++) {
        int x = max(sum_a[i], sum_b[i]);
        max_sum += x + common[i];
    }
    int M = sum_a.size();
    for(i = N; i < M; i++) {
        max_sum += max(sum_a[i], sum_b[i]);
    }
    return max_sum;
}