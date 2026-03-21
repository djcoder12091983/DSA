import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    
    static String texts[] = {
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
        "ten",
        "eleven",
        "twelve",
        "thirteen",
        "fouteen",
        "fifteen",
        "sixteen",
        "seventeen",
        "eighteen",
        "nineteen"
    };
    static String texts1[] = {
        "twenty",
        "thirty",
        "fourty",
        "fifty"
    };
    
    static String number2word(int n) {
        if(n < 20) {
            return texts[n - 1];
        }
        if(n % 10 == 0) {
            return texts[n / 10 - 2];
        }
        String text = String.valueOf(n);
        StringBuilder word = new StringBuilder(texts1[text.charAt(0) - 50]);
        word.append(" ").append(texts[text.charAt(1) - 49]);
        return word.toString();
    }
    
    static String minute(int m) {
        if(m == 15) {
            return "quarter";
        } else {
            return number2word(m) + " minute" + (m > 1 ? "s" : "");
        }
    }

    // Complete the timeInWords function below.
    static String timeInWords(int h, int m) {
        String htext = number2word(h);
        if(m == 0) {
            // o'
            return htext + " o' clock";
        } else if(m <= 30) {
            // past
            if(m == 30) {
                return "half past " + htext;
            }
            String mtext = minute(m);
            return mtext + " past " + htext;
        } else {
            // to
            int rem = 60 - m;
            String mtext = minute(rem);
            return mtext + " to " + number2word((h + 1) % 12);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int h = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String result = timeInWords(h, m);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
