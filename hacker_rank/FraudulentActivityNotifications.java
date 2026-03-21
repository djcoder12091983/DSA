import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class FraudulentActivityNotifications {
    
    static void add(TreeMap<Integer, Integer> map, int a) {
        Integer c = map.get(a);
        if(c == null) {
            map.put(a, 1);
        } else {
            map.put(a, c.intValue() + 1);
        }
    }
    
    static boolean remove(TreeMap<Integer, Integer> map, int a) {
        Integer c = map.get(a);
        if(c != null) {
            if(c == 1) {
                // entry delete
                map.remove(a);
            } else {
                // count update
                map.put(a, c.intValue() - 1);
            }
            return true;
        } else {
            return false;
        }
    }

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        // sort
        int t[] = new int[d];
        for(int i = 0; i < d; i++) {
            t[i] = expenditure[i];
        }
        Arrays.sort(t);
        int mid = d / 2;
        // create two BST (descending and ascending order)
        TreeMap<Integer, Integer> first = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
            // descending
            @Override
            public int compare(Integer a, Integer b) { 
                return b.compareTo(a);
            }
        });
        // ascending
        TreeMap<Integer, Integer> second = new TreeMap<Integer, Integer>();
        for(int i = 0; i < mid; i++) {
            add(first, t[i]);
        }
        for(int i = mid; i < d; i++) {
            add(second, t[i]);
        }
        int c = 0;
        int l = expenditure.length;
        boolean even = (d & 1) == 0;
        for(int i = d; i < l; i++) {
            float median;
            int f1 = first.firstKey();
            int f2 = second.firstKey();
            if(even) {
                median = (f1 + f2) / 2.0f;
            } else {
                median = f2;
            }
            int exp = expenditure[i];
            //System.out.println(median + " " + exp);
            if(exp >= 2 * median) {
                // notifications
                c++;
            }
            // update tree (remove first and add current)
            int remove = expenditure[i - d];
            // try with both required if expenditures are duplicate
            if(remove(first, remove)) {
                // try with first
                if(exp <= f1) {
                    add(first, exp);
                } else {
                    // balance
                    remove(second, f2);
                    add(first, f2);
                    add(second, exp);
                }
            } else {
                // then try second
                remove(second, remove);
                if(exp >= f2) {
                    add(second, exp);
                } else {
                    // balance
                    remove(first, f1);
                    add(second, f1);
                    add(first, exp);
                }
            }
        }
        return c;
    }
    
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/home/dspace/debasis/NDL/HackerRank/data/fan_input05.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokens.nextToken());
        int d = Integer.parseInt(tokens.nextToken());
        tokens = new StringTokenizer(reader.readLine());
        int expenditure[] = new int[n];
        for(int i = 0; i < n; i++) {
            expenditure[i] = Integer.parseInt(tokens.nextToken());
        }
        System.out.println(activityNotifications(expenditure, d));
        reader.close();
    }
}