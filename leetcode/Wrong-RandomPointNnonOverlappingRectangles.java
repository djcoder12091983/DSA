// https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/
// TODO -- need analysis why it fails!
// TODO -- explore the behavior of random function nextXXX

class Solution {

    Random randoms[];

    Random random = new Random();
    
    int N;
    int points[][];

    public Solution(int[][] rects) {
        this.N = rects.length;
        randoms = new Random[this.N];
        for(int i = 0; i < N; i++) {
            randoms[i] = new Random();
        }

        this.points = rects;
    }
    
    public int[] pick() {
        int idx = random.nextInt(N);
        Random rand = randoms[idx];

        int x2 = points[idx][3];
        int x1 = points[idx][1];
        int y2 = points[idx][2];
        int y1 = points[idx][0];
        int xdiff = x2 - x1;
        int ydiff = y2 - y1;

        // System.out.println("1. " + x1 + " " + y1 + " " + x2 + " " + y2);
        // System.out.println("2. " + xdiff + " " + ydiff);

        int x = x1 + rand.nextInt(xdiff);
        int y = y1 + rand.nextInt(ydiff);

        return new int[]{x, y};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */