import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LargestNonCoprimeSubmatrix {
    
    int[][] matrix;
    int n, m;
    
    // n X m
    public LargestNonCoprimeSubmatrix(int n, int m) {
        this.n = n;
        this.m = m;
        matrix = new int[n][m];
    }
    
    static int gcd(int a, int b) {
        if (a < b) {
            // exchange
            int t = a;
            a = b;
            b = t;
        }
        int r;
        while((r = a % b) != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return b;
    }
    
    // non-coprime column range
    static int[] rangeNonCoprime(int col[]) {
        int maxs = 0, maxe = 0;
        int start = 0;
        int gcd = col[0];
        int l = col.length;
        for(int i = 1; i < l; i++) {
            int a = col[i];
            gcd = gcd(a, gcd);
            if(gcd == 1) {
                // break sequence
                int prevdiff = maxe - maxs;
                int curdiff = i - 1 - start;
                if(prevdiff < curdiff) {
                    // track  max
                    maxe = i - 1;
                    maxs = start;
                }
                // reset
                gcd = a;
                start = i;
            }
        }
        
        // last
        int prevdiff = maxe - maxs;
        int curdiff = l - 1 - start;
        if(prevdiff < curdiff) {
            // track  max
            maxe = l - 1;
            maxs = start;
        }
        
        return new int[] {maxs, maxe};
    }
    
    int largestArea() {
        int maxarea = Integer.MIN_VALUE;
        int left, top, right, bottom;
        for(int i = 0; i < m; i++) {
            int col[] = new int[n];
            for(int k = 0; k < n; k++) {
                col[k] = matrix[k][i];
            }
            int ranges[] = rangeNonCoprime(col);
            left = i;
            top = ranges[0];
            right = i;
            bottom = ranges[1];
            int area;
            if(top == bottom) {
                area = 1;
            } else {
                area = (right - left + 1) * (bottom - top + 1);
            }
            if(area > maxarea) {
                // track max
                maxarea = area;
            }
            for(int j = i + 1; j < m; j++) {
                // new columns
                int prevcol[] = col;
                col = new int[n];
                for(int k = 0; k < n; k++) {
                    // associative gcd
                    col[k] = gcd(prevcol[k], matrix[k][j]);
                    if(col[k] != 1) {
                        // row-wise track
                        area = j - i  + 1;
                        if(area > maxarea) {
                            // track max
                            maxarea = area;
                        }
                    }
                }
                ranges = rangeNonCoprime(col);
                top = ranges[0];
                right = j;
                bottom = ranges[1];
                if(top == bottom) {
                    area = 1;
                } else {
                    area = (right - left + 1) * (bottom - top + 1);
                }
                if(area > maxarea) {
                    // track max
                    maxarea = area;
                }
            }
        }
        
        return maxarea;
    }
    
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/dspace/debasis/NDL/HackerRank/data/lncosm_input10.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());
        LargestNonCoprimeSubmatrix p = new LargestNonCoprimeSubmatrix(n, m);
        boolean required = false;
        for(int i = 0; i < n; i++) {
            tokens = new StringTokenizer(reader.readLine());
            for(int j = 0; j < m; j++) {
                int a = Integer.parseInt(tokens.nextToken());
                if(a != 1) {
                    required = true;
                }
                p.matrix[i][j] = a;
            }
        }
        System.out.println(required ? p.largestArea() : 0);
        reader.close();
    }
}