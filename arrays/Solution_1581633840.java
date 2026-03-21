class Solution {

    class Interval {
        int s, e, p;

        Interval(int s, int e, int p) {
            this.s = s;
            this.e = e;
            this.p = p;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        
        int N = startTime.length;
        ArrayList<Interval> intervals = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            intervals.add(new Interval(startTime[i], endTime[i], profit[i]));
        }

        //HashMap<Integer, Integer> DP = new HashMap<>();
        Collections.sort(intervals, (i1, i2) -> i1.s - i2.s);

        // resursive DP
        //return maximizeProfit(intervals, 0, DP);

        // ANOTHER APPROACH
        // DP with iteration and suffix maximum profit
        int suffixProfit[] = new int[N];
        suffixProfit[N - 1] = intervals.get(N - 1).p;

        int idx = N - 2;
        while(idx >= 0) {

            Interval i = intervals.get(idx); 
            int e = i.e;
            int local = i.p;

            // apply BS to find suitable interval
            int left = idx + 1, right = N - 1;
            int found = -1;
            while(left <= right) {
                int mid = (left + right)/2;
                int s = intervals.get(mid).s;

                if(e <= s) {
                    found = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            if(found != -1) {
                // found, now at that point based on suffix maximum we will use that
                local += suffixProfit[found];
            }

            // update suffix maximum profit
            suffixProfit[idx] = Math.max(local, suffixProfit[idx + 1]);

            idx--;
        }

        return suffixProfit[0];
    }

    int maximizeProfit(ArrayList<Interval> intervals, int index, HashMap<Integer, Integer> DP) {
        int N = intervals.size();

        if(DP.containsKey(index)) {
            return DP.get(index);
        }

        int result = 0;
        for(int i = index; i < N; i++) {
            int e = intervals.get(i).e;
            int p = intervals.get(i).p;
            int local = p;
            for(int j = i + 1; j < N; j++) {
                int s = intervals.get(j).s;
                if(s >= e) {
                    local = Math.max(local, p + maximizeProfit(intervals, j, DP));
                }
            }

            result = Math.max(result, local);
        }

        DP.put(index, result);

        //System.out.println("Index: " + index + " " + result);

        return result;
    }
}