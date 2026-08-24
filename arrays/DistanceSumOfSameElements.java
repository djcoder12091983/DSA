import java.util.*;

class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int res[] = main.solution(new int[]{4, 3, 4, 2, 4, 3});
        for(int x : res) {
            System.out.print(x + " ");
        }
    }
	
	// alternate findSum
	void findSum_1(List<Integer> index, long res[]) {
        int N = index.size();
        long s = 0;
        for(int i = 1; i < N; i++) {
            int k = index.get(i);
            int x = k - index.get(i - 1);
            s += 1L * x * i;
            res[k] += s; // contribution
        }

        s = 0;
        for(int i = N - 2; i >=0; i--) {
            int k = index.get(i);
            int x = index.get(i + 1) - k;
            s += 1L * x * (N - 1 - i);
            res[k] += s; // contribution
        }
    }
    
    void findSum(List<Integer> index, long res[]) {
        int N = index.size();
        if(N == 1) {
            res[index.get(0)] = 0;
            return;
        }
        long dist[] = new long[N];
        dist[0] = 0;
        for(int i = 1; i < N; i++) {
            dist[i] = index.get(i) - index.get(i - 1);
        }
        
        long totalleft[] = new long[N];
        long ls[] = new long[N];
        ls[0] = 0;
        totalleft[0] = 0;
        for(int i = 1; i < N; i++) {
            totalleft[i] = ls[i - 1] + totalleft[i-1] + dist[i];
            ls[i] = ls[i - 1] + dist[i];
        }

        // right dist update
        dist[N - 1] = 0;
        for(int i = N - 2; i >= 0; i--) {
            dist[i] = index.get(i + 1) - index.get(i);
        }

        long totalright[] = new long[N];
        long rs[] = new long[N];
        rs[N - 1] = 0;
        totalright[N - 1] = 0;
        for(int i = N - 2; i >= 0; i--) {
            totalright[i] = rs[i + 1] + totalright[i + 1] + dist[i];
            rs[i] = rs[i + 1] + dist[i];
        }

        //System.out.println(data1 + " " + Arrays.asList(totalleft));
        //System.out.println(Arrays.asList(rs) + " " + Arrays.asList(totalright));
        
        res[index.get(0)] = totalleft[N - 1];
        res[index.get(N - 1)] = totalright[0];
        for(int i = 1; i < N - 1; i++) {
            long left = totalleft[N - 1] - totalleft[i] - ls[i] * (N - 1 - i);
            long right = totalright[0] - totalright[i] - rs[i] * i;
            //System.out.println(left + " " + right);
            //System.out.println(totalright[0] + " " + totalright[i] + " " + rs[i]);
            res[index.get(i)] = left + right;
        }
    }
    
    int[] solution(int A[]) {
        int N = A.length;
        long res[] = new long[N];
        Map<Integer, List<Integer>> data = new HashMap<>();
        for(int i = 0; i < N; i++) {
            int x = A[i];
            if(!data.containsKey(x)) {
                data.put(x, new ArrayList<>());
            }
            
            data.get(x).add(i);
        }
        
        for(int key : data.keySet()) {
            findSum(data.get(key), res);
        }
        
        return res;
    }
}