// https://leetcode.com/problems/random-pick-with-weight/

class Solution {

    class Range {
        int idx;
        int index1, index2; // virtual index

        Range(int idx, int index1, int index2) {
            this.idx = idx;
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    List<Range> available = new ArrayList<>();
    Random random = new Random();

    int total;

    public Solution(int[] w) {
        int N = w.length;

        int idx = 0;
        total = 0;
        for(int i = 0; i < N; i++) {
            available.add(new Range(i, idx + 1, idx + w[i]));
            idx = idx + w[i]; // next start index
            total += w[i];
        }
    }
    
    public int pickIndex() {
        int idx = random.nextInt(total) + 1; // 1 based index
        // now find the index which block it belongs
        int l = 0, r = available.size() - 1;
        Range ans = null;
        while(l <= r) {
            int mid = (l + r) / 2;
            Range range = available.get(mid);
            if(idx < range.index1) {
                // left move
                r = mid - 1;
            } else if(idx > range.index2) {
                // right move
                l = mid + 1;
            } else {
                // index exists in this block
                ans = range;
                break;
            }
        }

        return ans.idx;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */