class MedianFinder {

    // maintains two heap to track first half and second half
    // in case of odd elements first heap will have extra element

    // first half will max heap and second one will be min 
    // so that we eaily compare both tops and shuffle across

    PriorityQueue<Integer> Q1 = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> Q2 = new PriorityQueue<>();

    int N = 0;

    public MedianFinder() {
        // you can intialize the variables here
    }
    
    public void addNum(int x) {
        Q1.add(x);
        N++;
        // now shuffle across the maintain the order Q1 top <= Q2 top
        if(!Q2.isEmpty() && Q1.peek() > Q2.peek()) {
            // shuffle
            int a = Q1.poll(), b = Q2.poll();
            Q1.add(b);
            Q2.add(a);
        }

        // now balance heaps, absolute difference will be always <= 1
        if(N % 2 == 0) {
            // even, Q1 will contain the extra elements
            Q2.add(Q1.poll());
        }
    }
    
    public double findMedian() {
        if(N % 2 == 0) {
            // even
            return (Q1.peek() + Q2.peek()) / 2.0;
        } else {
            // odd
            return Q1.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */