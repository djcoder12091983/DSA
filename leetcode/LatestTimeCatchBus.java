// https://leetcode.com/problems/the-latest-time-to-catch-a-bus/

class Solution {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        // sort is needed for arrangements passengers vs buses mapping
        Arrays.sort(buses);
        Arrays.sort(passengers);

        int N = buses.length;
        int M = passengers.length;

        // bus based load
        int load[] = new int[N];
        Arrays.fill(load, 0);

        // i for bus and j for passenger
        int i = 0, j = 0;
        
        int c = 0;
        while(i < N && j < M) {
            if(buses[i] >= passengers[j]) {
                c++;
                j++;

                if(c == capacity) {
                    load[i] = c; // bas based load
                    c = 0; // reset load count for bus
                    i++; // next bus
                }
            } else {
                load[i] = c;
                c = 0; // reset load count for bus
                i++; // next bus
            }
        }

        j = j - 1;
        if(load[N - 1] == capacity) {
            // last bus fully loaded
            // we will start from last passenger arrival time
            // and see which one latest blank on left side
            i = passengers[j];
        } else {
            // we will start from last bus departure time
            // and see which one latest blank on left side
            i = buses[N - 1];
        }

        while(j >= 0) {
            if(i != passengers[j]) {
                // found latest blank arrival time
                return i;
            }

            i--;
            j--;
        }

        return i; // it will be at least >= 1
    }
}