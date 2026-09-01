// https://leetcode.com/problems/the-latest-time-to-catch-a-bus/
// TODO -- incomplete idea -- may need to think latter. It's missing some cases, so dropped this implementation.

class Solution {

    // passenger to bus mapping
    int findBus(int buses[], int l, int x) {
        int r = buses.length - 1;
        int ans = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(buses[mid] < x) {
                // not possible
                l = mid + 1;
            } else {
                // possible to map but i need earliest
                ans = mid;
                r = mid - 1;
            }
        }

        return ans;
    }

    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        // sort is needed for arrangements passengers vs buses mapping
        Arrays.sort(buses);
        Arrays.sort(passengers);

        int N = buses.length;
        int M = passengers.length;
        int i = 0; // passenger index
        int j = 0; // bus index we will move when limit is reached
        int c = 0; // how many passengers loaded
        int load[] = new int[capacity*N];
        int k = 0;
        while(i < N && j < M) {
            int bus = findBus(buses, passengers[i]);
            if(bus == -1) {
                // no more bus available
                break;
            } else {
                load[k++] = passengers[i]; // load the passengers[i]
                if(c == capacity) {
                    // limit reached then we will move next bus
                    j++;
                    c = 0; // reset loaded passengers
                }
            }

            i++;
        }
    }
}