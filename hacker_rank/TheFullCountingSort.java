import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class TheFullCountingSort {
    
    static void countSort(Object map[], List<String> texts) {
        int l = texts.size() / 2;
        StringBuilder res = new StringBuilder();
        for(Object o : map) {
            if(o == null) {
                continue;
            }
            List<Integer> list = (List<Integer>)o;
            for(int p : list) {
                if(p < l) {
                    res.append("- ");
                } else {
                    res.append(texts.get(p)).append(" ");
                }
            }
        }
        System.out.println(res);
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Object map[] = new Object[100];
        List<String> texts = new ArrayList<String>(n);
        for(int i = 0; i < n; i++) {
            StringTokenizer tokens = new StringTokenizer(reader.readLine());
            int p = Integer.parseInt(tokens.nextToken());
            texts.add(tokens.nextToken());
            List<Integer> list = (List<Integer>)map[p];
            if(list == null) {
                list = new LinkedList<Integer>();
                map[p] = list;
            }
            list.add(i);
        }
        countSort(map, texts);
        reader.close();
    }
}