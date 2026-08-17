// https://leetcode.com/problems/ugly-number-iii/
// TODO handle duplicates

class Solution {
    // this is the extension of -
    // https://leetcode.com/problems/ugly-number-ii/
    public int nthUglyNumber(int n, int a, int b, int c) {
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
        int t[] = new int[]{a, b, c};
        Arrays.sort(t); // sort the number to generate the number in sorted order
        for(int i = 0; i < 3; i++) {
            Q.add(new Data(t[i], i));
        }
        
        // in "https://leetcode.com/problems/ugly-number-ii/" question the first number is 1
        long x = 0; // this time first number is not 1, it starts from 2
        while(n > 0) {
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