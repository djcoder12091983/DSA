public class Solution {
    
    // fibo last 2 elements
    class Fibo {
        int f1, f2, lastidx;
        
        Fibo(int f1, int f2, int lastidx) {
            this.f1 = f1;
            this.f2 = f2;
            this.lastidx = lastidx;
        }
    }
    
    // binary search on sorted array by range and given X
    int bs(int A[], int l, int r, int x) {
        while(l <= r) {
            int mid = (l + r) / 2;
            if(A[mid] == x) {
                return mid;
            } else if(A[mid] < x) {
                // right side
                l = mid + 1;
            } else {
                // left side
                r = mid - 1;
            }
        }
        return -1; // not found yet
    }
    
    public int solve(int[] A) {
        int N = A.length;
        List<Fibo> track = new ArrayList<>();
        // setup first two elements
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                track.add(new Fibo(A[i], A[j], j));
            }
        }
        // now create incremental subsequence
        // BFS style
        int maxs = 1;
        while(!track.isEmpty()) {
            maxs++; // current maximum subseqeunce
            List<Fibo> t = new ArrayList<>();
            for(Fibo fibo : track) {
                int f1 = fibo.f1;
                int f2 = fibo.f2;
                int f = f1 + f2; // next required
                if(fibo.lastidx < N - 1) {
                    int idx = bs(A, fibo.lastidx + 1, N - 1, f);
                    if(idx != -1) {
                        // found next fibo
                        t.add(new Fibo(f2, f, idx));
                    }
                }
            }
            track = t; // next subsequences
        }
        if(maxs == 2) {
            return 0;
        }
        return maxs;
    }
}
