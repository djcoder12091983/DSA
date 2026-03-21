import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the surfaceArea function below.
    static int surfaceArea(int[][] A, int H, int W) {
        int area = 2 * (H * W); // top and bottom area
        // two sides
        for(int i = 0; i < H; i++) {
            int h = 0;
            for(int j = 0; j < W; j++) {
                int d = A[i][j] - h;
                if(d < 0) {
                    d = d * -1;
                }
                area += d;
                
                h = A[i][j];
            }
            area += h; // last
        }
        // another two sides
        for(int i = 0; i < W; i++) {
            int h = 0;
            for(int j = 0; j < H; j++) {
                int d = A[j][i] - h;
                if(d < 0) {
                    d = d * -1;
                }
                area += d;
                
                h = A[j][i];
            }
            area += h; // last
        }
        return area;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] HW = scanner.nextLine().split(" ");

        int H = Integer.parseInt(HW[0]);

        int W = Integer.parseInt(HW[1]);

        int[][] A = new int[H][W];

        for (int i = 0; i < H; i++) {
            String[] ARowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < W; j++) {
                int AItem = Integer.parseInt(ARowItems[j]);
                A[i][j] = AItem;
            }
        }

        int result = surfaceArea(A, H, W);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
