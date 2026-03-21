import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class QueensAttackII {
    
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object obj) {
            Point p = (Point)obj;
            return p.x == x && p.y == y;
        }
        
        @Override
        public int hashCode() {
            int h = 13;
            h += h * 31 + x;
            h += h * 31 + y;
            return h;
        }
    }
    
    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int rq, int cq, int[][] obstacles) {
        Set<Point> points = new HashSet<Point>();
        for(int i = 0; i < k; i++) {
            int x = obstacles[i][0];
            int y = obstacles[i][1];
            points.add(new Point(n - x, y - 1));
        }
        int c = 0;
        // north movement
        for(int i = n - (rq + 1); i >= 0; i--) {
            if(points.contains(new Point(i, cq - 1))) {
                break;
            }
            c++;
        }
        //System.out.println("N: " + c);
        // south movement
        for(int i = n - (rq - 1); i < n; i++) {
            if(points.contains(new Point(i, cq - 1))) {
                break;
            }
            c++;
        }
        //System.out.println("S: " + c);
        // east movement
        for(int i = cq - 2; i >= 0; i--) {
            if(points.contains(new Point(n - rq, i))) {
                break;
            }
            c++;
        }
        //System.out.println("E: " + c);
        // west movement
        for(int i = cq; i < n; i++) {
            if(points.contains(new Point(n - rq, i))) {
                break;
            }
            c++;
        }
        //System.out.println("W: " + c);
        // north east move
        for(int i = n - (rq + 1), j = cq - 2; i >= 0 && j >= 0; i--, j--) {
            if(points.contains(new Point(i, j))) {
                break;
            }
            c++;
        }
        //System.out.println("NE: " + c);
        // south west move
        for(int i = n - (rq - 1), j = cq; i < n && j < n; i++, j++) {
            if(points.contains(new Point(i, j))) {
                break;
            }
            c++;
        }
        //System.out.println("SW: " + c);
        // north west move
        for(int i = n - (rq + 1), j = cq; i >= 0 && j < n; i--, j++) {
            if(points.contains(new Point(i, j))) {
                break;
            }
            c++;
        }
        //System.out.println("NW: " + c);
        // south east move
         for(int i = n - (rq - 1), j = cq - 2; i < n && j >= 0; i++, j--) {
             if(points.contains(new Point(i, j))) {
                break;
            }
            c++;
        }
        //System.out.println("SE: " + c);
        return c;
    }
    
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/dspace/debasis/NDL/HackerRank/data/qaII_input13.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokens.nextToken());
        int k = Integer.parseInt(tokens.nextToken());
        tokens = new StringTokenizer(reader.readLine());
        int rq = Integer.parseInt(tokens.nextToken());
        int cq = Integer.parseInt(tokens.nextToken());
        int obstacles[][] = new int[k][2];
        for(int i = 0; i < k; i++) {
            tokens = new StringTokenizer(reader.readLine());
            obstacles[i][0] = Integer.parseInt(tokens.nextToken());
            obstacles[i][1] = Integer.parseInt(tokens.nextToken());
        }
        System.out.println(queensAttack(n, k, rq, cq, obstacles));
        reader.close();
    }
}