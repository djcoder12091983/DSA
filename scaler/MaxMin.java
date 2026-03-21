public class Solution {
    
    // data along with index
    class Data {
        long data;
        int idx;
        Data(long data, int idx) {
            this.data = data;
            this.idx = idx;
        }
    }
    
    static long MODULO = 1000000007;
    
    // min/max frequency sum
    long sum(Queue<Data> track, int N) {
        long sum = 0;
        // index track to see how elements occur min/max in subarrays
        TreeSet<Integer> index = new TreeSet<>();
        while(!track.isEmpty()) {
            Data d = track.poll();
            int idx = d.idx;
            Integer left = index.floor(idx);
            if(left == null) {
                left = -1;
            }
            int diff1 = idx - left - 1;
            sum += (d.data * diff1) % MODULO;
            Integer right = index.ceiling(idx);
            if(right == null) {
                right = N;
            }
            int diff2 = right - idx - 1;
            sum += (d.data * diff2) % MODULO;
            // cross subarray
            sum += (d.data * diff1 * diff2) % MODULO;
            // track index
            index.add(idx);
        }
        return sum % MODULO;
    }
    
    public int solve(int[] A) {
        // note: need to see an elements how many times occur as max in subarrays
        // similar for minimum
        
        // min frequency count
        Queue<Data> min = new PriorityQueue<>(new Comparator<Data>() {
            @Override
            public int compare(Data d1, Data d2) {
                return Long.valueOf(d1.data).compareTo(Long.valueOf(d2.data));
            }
        });
        int N = A.length;
        for(int i = 0; i < N; i++) {
            min.add(new Data(A[i], i));
        }
        long minsum = sum(min, N);
        // max frequency count
        Queue<Data> max = new PriorityQueue<>(new Comparator<Data>() {
            @Override
            public int compare(Data d1, Data d2) {
                // reverse ordering
                return Long.valueOf(d2.data).compareTo(Long.valueOf(d1.data));
            }
        });
        for(int i = 0; i < N; i++) {
            max.add(new Data(A[i], i));
        }
        long maxsum = sum(max, N);
        long diff = maxsum - minsum; // difference sum
        if(diff < 0) {
            // negative modulo
            diff += MODULO;
        }
        
        return Long.valueOf(diff).intValue(); // always it will be in integer range
    }
}
