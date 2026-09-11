// https://leetcode.com/problems/exam-room/
// INCOMPLETE -- TODO -- complete the implementation
// as the call can be atmost 10000 we may go with BRUTE-FORCE
// IDEA -- it's like maintaining large space and it's corresponding ranges
// now we will keep on filling people in large range and then we will split the range
// when people leave then from a map we will track left and right and from there we will get the range information
// and accordingly we will remove those from ranges sorted map

class ExamRoom {

    // this will track the range in between all seats are empty
    class Range {
        int left;
        int right;

        Range(int left, int right) {
            this.left = left;
            this.right = right;
        }

        int gap() {
            return right - left - 1;
        }
    }

    // ranges sorted based on large space
    TreeMap<Integer, TreeSet<Range>> ranges = new TreeMap<>(Collections.reverseOrder());

    public ExamRoom(int N) {
        Range range = new Range(-1, N);
        // TODO
    }
    
    public int seat() {
        
    }
    
    public void leave(int p) {
        
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */