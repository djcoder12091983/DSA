public class Solution {
    
    class Range {
        int start, end; // range
        
        Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int hashCode(){
            int h = 31;
            h += h * 17 + start;
            h += h * 17 + end;
            return h;
        }
        
        @Override
        public boolean equals(Object o) {
            Range res = (Range)o;
            return res.start == start && res.end == end;
        }
    }
    
    // recursive solution result for each possibilities
    class Result {
        long cost; // minimized cost
        List<Integer> points; // order of weak points cut
        
        Result(long cost, List<Integer> points) {
            this.cost = cost;
            this.points = points;
        }
    }
    
    Result solve(ArrayList<Integer> B, int start, int end, Map<Range, Result> DP) {
        if(B.isEmpty()) {
            return new Result(0, new ArrayList<>());
        }
        Range range = new Range(start, end);
        if(DP.containsKey(range)) {
            return DP.get(range);
        }
        // check all possibilities
        List<Integer> points = null;
        int N = B.size();
        // minimum cost computation
        long cost = Long.MAX_VALUE;
        int l = end - start;
        for(int i = 0; i < N; i++) {
            int point = B.get(i);
            
            // partition list
            ArrayList<Integer> B1 = new ArrayList<>();
            ArrayList<Integer> B2 = new ArrayList<>();
            for(int j = 0; j < i; j++) {
                B1.add(B.get(j));
            }
            for(int j = i + 1; j < N; j++) {
                B2.add(B.get(j));
            }
            
            // recursive call for remaining point
            Result left = solve(B1, start, point, DP);
            Result right = solve(B2, point, end, DP);
            long t = l + left.cost + right.cost;
            if(t < cost) {
                // new minimum
                points = new ArrayList<>();
                points.add(point);
                points.addAll(left.points);
                points.addAll(right.points);
                cost = t;
            } else if(t == cost) {
                // find lexographically smallest
                List<Integer> points1 = new ArrayList<>();
                points1.add(point);
                points1.addAll(left.points);
                points1.addAll(right.points);
                // compare with existing points and change if required
                boolean change = false;
                int N1 = points.size();
                for(int j = 0; j < N1; j++) {
                    int p1 = points1.get(j), p2 = points.get(j);
                    if(p1 < p2) {
                        change = true;
                        break;
                    } else if(p1 > p2) {
                        break;
                    }
                }
                
                if(change) {
                    points = points1;
                }
            }
        }
        
        // store the data into DP and return back
        Result res = new Result(cost, points);
        DP.put(range, res);
        return res;
    }
    
    public ArrayList<Integer> rodCut(int A, ArrayList<Integer> B) {
        Map<Range, Result> DP = new HashMap<>(); // stored result
        
        Collections.sort(B); // sort weak points
        
        // recursive solve
        Result res = solve(B, 0, A, DP);
        
        ArrayList<Integer> ans = new ArrayList(res.points);
        return ans;
    }
}
