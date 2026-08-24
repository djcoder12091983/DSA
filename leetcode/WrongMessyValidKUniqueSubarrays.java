// MO's algorithm (SQRT decomposition)
// TODO: tracking ODD and EVEN frequency seems to be bit complex
class Solution {

    static final int LIMIT = 100000;

    int blockSize;
    // HashMap<Integer, Integer> frequencyMap = new HashMap<>();
    // numbers are in range 1 to 100000
    int frequencyMap[] = new int[LIMIT + 1];
    int evenFrequency = 0, oddFrequency = 0;
    int frequencyMapSize = 0;
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
        frequencyMap[x]++;
        if(frequencyMap[x] == 1) {
            // first time entry
            frequencyMapSize++;
        }

        // note: balance odd and even frequency it's bit tricky
        if(frequencyMap[x] % 2 == 0) {
            evenFrequency++;
            if(oddFrequency > 0) {
                oddFrequency--;
            }
        } else {
            oddFrequency++;
            if(evenFrequency > 0) {
                evenFrequency--;
            }
        }
    }

    // Remove element from the active range
    void remove(int idx) {
        int x = data[idx];
        frequencyMap[x]--;
        if(frequencyMap[x] == 0) {
            frequencyMapSize--;
        }

        // note: balance odd and even frequency it's bit tricky
        if(frequencyMap[x] % 2 == 0) {
            if(oddFrequency > 0) {
                oddFrequency--;
            }
            if(frequencyMap[x] > 0) {
                evenFrequency++;
            }
        } else {
            if(evenFrequency > 0) {
                evenFrequency--;
            }
            if(frequencyMap[x] > 0) {
                oddFrequency++;
            }
        }
    }

    // check current window is valid or not
    boolean valid(int k) {
        if(frequencyMapSize == k && evenFrequency == k) {
            return true;
        } else {
            return false;
        }
    }

    public boolean[] validSubarrays(int[] A, int k, int[][] Q) {

        // reset frequencyMap
        Arrays.fill(frequencyMap, 0);

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

            System.out.println("L: " + query.L + " R: " + query.R + " OF: " + oddFrequency + " EF: " + evenFrequency + " Size: " + frequencyMapSize);

            // Save the answer mapping it back to its original query position
            answers[query.idx] = valid(k);
        }

        return answers;
    }
}