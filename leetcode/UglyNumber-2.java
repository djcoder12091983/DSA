// https://leetcode.com/problems/ugly-number-ii/

class Solution {
    public int nthUglyNumber(int n) {

        class Data {
            long x;
            // this idx determies from which index we will generate multiples
            int idx;

            Data(long x, int idx) {
                this.x = x;
                this.idx = idx;
            }
        }
        
        // we will keep on generating numbers using 2 3 5
        PriorityQueue<Data> Q = new PriorityQueue<>((d1, d2) -> Long.compare(d1.x, d2.x));
        int t[] = new int[]{2, 3, 5};
        for(int i = 0; i < 3; i++) {
            Q.add(new Data(t[i], i));
        }
        
        long x = 1; // first number is 1
        while(n > 1) {
            Data d = Q.poll();
            x = d.x; // current minimum compound number having 2, 3, 5 prime factors

            for(int i = d.idx; i < 3; i++) {
                Q.add(new Data(1L * x * t[i], i));
            }

            n--;
        }

        return Long.valueOf(x).intValue();
    }
}