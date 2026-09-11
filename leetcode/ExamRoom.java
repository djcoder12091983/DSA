// https://leetcode.com/problems/exam-room/

// as the call can be atmost 10000 we may go with BRUTE-FORCE
// TODO we may need to think some optimized approach to handle large dataset

class ExamRoom {

    // we will manage occupancy sorted set
    TreeSet<Integer> occupied = new TreeSet<>();
    int seats;

    public ExamRoom(int n) {
        this.seats = n;
        // occupied.add(n); // dummy value
    }
    
    public int seat() {
        if(occupied.size() == 0) {
            occupied.add(0); // place at 0th index
            return 0;
        } else {
            // iterate over and see where it will fit best
            int prev = -1;
            Iterator<Integer> i = occupied.iterator();
            int d = -1;
            int seatIdx = -1;
            while(i.hasNext()) {
                int idx = i.next();
                int gap = idx - prev - 1; // gap available
                if(gap > 0) {
                    int fit,t;
                    if(prev == -1) {
                        // if there is no person at 0th index
                        fit = 0;
                        t = idx - 1;
                    } else {
                        fit = prev + (gap + 1) / 2; // where optimally fit at that available gap
                        t = fit - prev - 1; // created maximum gap
                    }
                    if(t > d) {
                        // found maximum gap
                        d = t;
                        seatIdx = fit;
                    }
                }

                prev = idx;
            }

            // last empty block
            if(prev < seats - 1) {
                int fit = seats - 1; // last slot
                int t = fit - prev - 1; // created maximum gap
                if(t > d) {
                    // found maximum gap
                    seatIdx = fit;
                }
            }

            occupied.add(seatIdx); // fill the seat
            return seatIdx; // found seat index
        }
    }
    
    public void leave(int p) {
        occupied.remove(p); // mark as empty
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */