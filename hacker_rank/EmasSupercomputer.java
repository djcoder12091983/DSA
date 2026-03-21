import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class EmasSupercomputer {
    
    static class Point {
        int i, j;
        
        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
        
        @Override
        public int hashCode() {
            int h = 13 + i * 31;
            h += j * 31;
            return h;
        }
        
        @Override
        public boolean equals(Object o) {
            Point p = (Point)o;
            return p.i == i && p.j == j;
        }
    }
    
    static class Result {
        int left = -1, top = -1, right = -1, bottom = -1;
        int row = -1, col = -1;
        int area = 0;
        
        public Result() {
            // blank
        }
        
        Result(int row, int left, int right, int col, int top, int bottom) {
            this.row = row;
            this.left = left;
            this.right = right;
            this.col = col;
            this.top = top;
            this.bottom = bottom;
            this.area = (right - left) + 1 + (bottom - top);
        }
        
        boolean overlap(Result r) {
            Set<Point> points = new HashSet<Point>();
            for(int i = left; i <= right; i++) {
                points.add(new Point(row, i));
            }
            for(int i = top; i <= bottom; i++) {
                points.add(new Point(i, col));
            }
            for(int i = r.left; i <= r.right; i++) {
                if(points.contains(new Point(r.row, i))) {
                    // overlaps
                    return true;
                }
            }
            for(int i = r.top; i <= r.bottom; i++) {
                if(points.contains(new Point(i, r.col))) {
                    // overlaps
                    return true;
                }
            }
            return false;
        }
    }

    static Result findPlus(char char2d[][], int starti, int startj, int n , int m) {
        int left = 0, right = 0, top = 0, bottom = 0;
        // left movement
        for(int i = startj - 1; i >= 0; i--) {
            if(char2d[starti][i] == 'B') {
                break;
            }
            left++;
        }
        // right movement
        for(int i = startj + 1; i < m; i++) {
            if(char2d[starti][i] == 'B') {
                break;
            }
            right++;
        }
        // top movement
        for(int i = starti - 1; i >= 0; i--) {
            if(char2d[i][startj] == 'B') {
                break;
            }
            top++;
        }
        // bottom movement
        for(int i = starti + 1; i < n; i++) {
            if(char2d[i][startj] == 'B') {
                break;
            }
            bottom++;
        }
        // minimum length
        int min = Math.min(Math.min(left, right), Math.min(top, bottom));
        return new Result(starti, startj - min, startj + min, startj, starti - min, starti + min);
    }

    // Complete the twoPluses function below.
    static int twoPluses(char char2d[][], int n, int m) {
        List<Result> track = new ArrayList<Result>();
        for(int i = 1; i < n - 1; i++) {
            for(int j = 1; j < m - 1; j++) {
                if(char2d[i][j] == 'G') {
                    Result r = findPlus(char2d, i, j, n, m);
                    // track all possible plus
                    while(r.area >= 1) {
                        track.add(r);
                        // next possible plus
                        r = new Result(r.row, r.left + 1, r.right - 1, r.col, r.top + 1, r.bottom - 1);
                    }
                }
            }
        }
        // take first two fit bigger plus
        int l = track.size();
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < l; i++) {
            for(int j = i + 1; j < l; j++) {
                Result r1 = track.get(i);
                Result r2 = track.get(j);
                if(!r1.overlap(r2)) {
                    // two fit bigger plus
                    int a = r1.area * r2.area;
                    if(max < a) {
                        // track max area
                        max = a;
                    }
                }
            }
        }
        return max;
    }
    
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/dspace/debasis/NDL/HackerRank/data/esc_input10.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());
        char char2d[][] = new char[n][m];
        for(int i = 0; i < n; i++) {
            String r = reader.readLine();
            for(int j = 0; j < m; j++) {
                char2d[i][j] = r.charAt(j);
            }
        }
        System.out.println(twoPluses(char2d, n, m));
        reader.close();
    }
}