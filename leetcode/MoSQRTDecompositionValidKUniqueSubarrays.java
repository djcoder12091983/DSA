// https://leetcode.com/problems/valid-k-unique-subarrays-i/
// TODO: need to understand MO's SQRT decomposition algorithm

class Solution {

    int blockSize;
    Map<Integer, Integer> frequencyMapOdd = new HashMap<>();
    Map<Integer, Integer> frequencyMapEven = new HashMap<>();
    int data[];

    class Query implements Comparable<Query> {
        int L, R, idx;

        Query(int L, int R, int idx) {
            this.L = L;
            this.R = R;
            this.idx = idx;
        }

        // Sort queries based on Square Root Decomposition
        @Override
        public int compareTo(Query other) {
            int b1 = this.L / blockSize;
            int b2 = other.L / blockSize;

            if(b1 != b2) {
                return b1 - b2;
            }
            
            // Zig-zag optimization to reduce right-pointer movement
            if((b1 & 1) == 1) {
                return this.R - other.R;
            } else {
                return other.R - this.R;
            }
        }
    }

    // Add element to the active range
    void add(int idx) {
        int x = data[idx];
        // note: the element will be missing or in even or odd map, because we are adding may be a new element
        if(frequencyMapEven.containsKey(x)) {
            // in even so we move to odd
            int fc = frequencyMapEven.get(x);
            frequencyMapEven.remove(x);
            frequencyMapOdd.put(x, fc + 1);
        } else if(frequencyMapOdd.containsKey(x)) {
            // in odd so we move to even
            int fc = frequencyMapOdd.get(x);
            frequencyMapOdd.remove(x);
            frequencyMapEven.put(x, fc + 1);
        } else {
            // first time entry so it will be in odd
            frequencyMapOdd.put(x, 1);
        }
    }

    // Remove element from the active range
    void remove(int idx) {
        int x = data[idx];
        // note: here either it's in even map or in odd map, because we delete existing element
        if(frequencyMapEven.containsKey(x)) {
            // in even so we move to odd
            int fc = frequencyMapEven.get(x);
            frequencyMapEven.remove(x);
            // always frequency count will be >= 2
            frequencyMapOdd.put(x, fc - 1);
        } else {
            // in odd so we move to even
            int fc = frequencyMapOdd.get(x);
            frequencyMapOdd.remove(x);
            if(fc > 1) {
                frequencyMapEven.put(x, fc - 1);
            }
        }
    }

    // check current window is valid or not
    boolean valid(int k) {
        if(frequencyMapEven.size() + frequencyMapOdd.size() == k && frequencyMapOdd.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean[] validSubarrays(int[] A, int k, int[][] Q) {
        int N = A.length;
        // Set the block size for sqrt decomposition
        this.blockSize = Double.valueOf(Math.max(1, Math.sqrt(N))).intValue();
        
        this.data = A;
        N = Q.length;
        Query[] queries = new Query[N];
        for(int i = 0; i < N; i++) {
            queries[i] = new Query(Q[i][0], Q[i][1], i);
        }

        // sort and process query
        Arrays.sort(queries);

        boolean[] answers = new boolean[N];

        // Initialize current range boundaries
        // An empty range starting just before index 0
        int curr_L = 0;
        int curr_R = -1;

        // Process queries sequentially
        for (Query query : queries) {
            // Expand range outwards
            while (curr_L > query.L) {
                curr_L--;
                add(curr_L);
            }
            while (curr_R < query.R) {
                curr_R++;
                add(curr_R);
            }

            // Shrink range inwards
            while (curr_L < query.L) {
                remove(curr_L);
                curr_L++;
            }
            while (curr_R > query.R) {
                remove(curr_R);
                curr_R--;
            }

            // Save the answer mapping it back to its original query position
            answers[query.idx] = valid(k);
        }

        return answers;
    }
}