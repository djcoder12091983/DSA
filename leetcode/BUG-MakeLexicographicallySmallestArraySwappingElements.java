// https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements
// TODO - need to FIX BUG -- also bit MESSY -- NEED to simplify

class Solution {

    int find(int data[][], int x, int idx) {
        int l = 0, r = data.length - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(data[mid][0] > x) {
                r = mid - 1;
            } else if(data[mid][0] < x) {
                l = mid + 1;
            } else {
                // find proper index
                if(data[mid][1] > idx) {
                    r = mid - 1;
                } else if(data[mid][1] < idx) {
                    l = mid + 1;
                } else {
                    // found
                    return mid;
                }
            }
        }

        return -1; // this won't happen
    }

    public int[] lexicographicallySmallestArray(int[] A, int limit) {
        // we will sort the elements and see how current element is ahead right side elements
        // which share a consecutive difference <= limit
        int N = A.length;
        int data[][] = new int[N][2];
        for(int i = 0; i < N; i++) {
            data[i] = new int[]{A[i], i};
        }

        // sort based on values
        Arrays.sort(data, new Comparator<int[]>(){
            @Override
            public int compare(int[] x, int[] y) {
                int c = x[0] - y[0];
                if(c != 0) {
                    return c;
                }

                return x[1] - y[1];
            }
        });

        // this will track which elements are visited on right side against current element
        boolean V[] = new boolean[N];
        Arrays.fill(V, false);

        int ans[] = new int[N];
        int k = 0;
        for(int i = 0; i < N; i++) {
            if(!V[i]) {
                // not visited
                int idx = find(data, A[i], i);
                int j = idx;
                List<Integer> t = new ArrayList<>();
                // we will consider the elements in the group which is in limit range
                // can be swappable
                while(j > 0 && data[j][0] - data[j - 1][0] <= limit) {
                    if(!V[data[j][1]]) {
                        V[data[j][1]] = true;
                        t.add(data[j][0]);
                    }
                    j--;
                }
                if(!V[data[j][1]]) {
                    V[data[j][1]] = true;
                    t.add(data[j][0]);
                }

                //System.out.println("i: " + i + " t: " + t);

                int l = t.size();
                for(int p = l - 1; p >= 0; p--) {
                    ans[k++] = t.get(p);
                }
            }
        }

        return ans;
    }
}