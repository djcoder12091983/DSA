// https://leetcode.com/problems/random-pick-with-blacklist/

class Solution {

    // store available ranges, start and end available index and actual index range
    // so that we can apply binary search to find eaxt index block
    class Range {
        int start, end;
        int index1, index2; // actual index

        Range(int start, int end, int index1, int index2) {
            this.start = start;
            this.end = end;
            this.index1 = index1;
            this.index2 = index2;
        }

		// this is for testing purpose
		// TODO DEBUG!
		/*
        @Override
        public String toString() {
            return "[Range: " + start + ", " + end + " Idx: " + index1 + ", " + index2 + "]";
        }
		*/
    }

    List<Range> available = new ArrayList<>();
    Random random = new Random();

    int total = 0;

    public Solution(int n, int[] blacklist) {

        int s = blacklist.length;
        if(s == 0) {
            // all are available
            total = n;
            available.add(new Range(0, n - 1, 1, n));
        } else {

            // we will store available numbers, store in range becase of large value
            Arrays.sort(blacklist);

            int start = 0;
            int i = 0;
            while(i < s) {

                int end = blacklist[i] - 1;
                if(end >= start) {
                    int t = total;
                    total += end - start + 1;
                    available.add(new Range(start, end, t + 1, total));
                }

                int j = i;
                // track consecutives
                while(j < s - 1 && blacklist[j] + 1 == blacklist[j + 1]) {
                    j++;
                }

                // reset start
                start = blacklist[j] + 1;
                i = j + 1;
            }

            if(n - 1 >= start) {
                // valid last range
                int t = total;
                total += n - start;
                available.add(new Range(start, n - 1, t+1, total));
            }
        }

        // System.out.println(available);
    }
    
    public int pick() {
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

        // ans willl be always found
        idx = idx - ans.index1;
        return ans.start + idx;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n, blacklist);
 * int param_1 = obj.pick();
 */