import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int l = s.length();
        for(int i = 0; i < l; i++) {
            char ch = s.charAt(i);
            Integer c = map.get(ch);
            if(c == null) {
                c = 1;
            } else {
                c = c.intValue() + 1;
            }
            map.put(ch, c);
        }
        //System.out.println(map);
        Map<Integer, Integer> ftrack = new HashMap<Integer, Integer>();
        for(int c : map.values()) {
            Integer c1 = ftrack.get(c);
            if(c1 == null) {
                ftrack.put(c, 1);
            } else {
                ftrack.put(c, c1.intValue() + 1);
            }
            if(ftrack.size() > 2) {
                // not possible
                return "NO";
            }
        }
        //System.out.println(ftrack);
        if(ftrack.size() == 1) {
            // possible
            return "YES";
        }
        Iterator<Integer> i = ftrack.keySet().iterator();
        int first = i.next();
        int second = i.next();
        if((first == 1 && ftrack.get(first) == 1) || (second == 1 && ftrack.get(second) == 1)) {
            // possible
            return "YES";
        }
        int d = second - first;
        if(d < 0) {
            d *= -1;
        }
        if(d == 1 && ftrack.values().contains(1)) {
            // possible
            return "YES";
        } else {
            // not possible
            return "NO";
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
