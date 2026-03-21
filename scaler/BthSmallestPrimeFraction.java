public class Solution {
    class Fraction implements Comparable<Fraction> {
        int p, q;
        Double f;
        int left, right;
        
        Fraction(int p, int q, int left, int right) {
            this.p = p;
            this.q = q;
            double x = p, y = q;
            f = new Double(x/y);
            this.left = left;
            this.right = right;
        }
        
        @Override
        public int compareTo(Fraction frac) {
            return this.f.compareTo(frac.f);
        }
    }
    
    public ArrayList<Integer> solve(ArrayList<Integer> A, int B) {
        // note: use the same approach to solve merging K sorted arrays
        Queue<Fraction> min = new PriorityQueue<>();
        // A[row][0]/A[row][col-1] is the minimum for each row
        int N = A.size();
        for(int i = 0; i < N - 1; i++) {
            min.add(new Fraction(A.get(i), A.get(N - 1), i, N - 1));
        }
        // now pick top (minimum) then selected A[left]/A[right-1] value selected to push into min heap
        Fraction bmin = null;
        while(--B >= 0) {
            Fraction top = min.poll();
            // System.out.println(top.p + " " + top.q + " => " + top.left + " " + top.right);
            bmin = top; // current minimum
            int left = top.left, right = top.right - 1;
            if(left < right) {
                // next fraction selected
                min.add(new Fraction(A.get(left), A.get(right), left, right));
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<Integer>(2);
        ans.add(bmin.p);
        ans.add(bmin.q);
        return ans;
    }
}
