// https://leetcode.com/problems/design-front-middle-back-queue/
// TODO we can design it by BRUTE force like ARRAY or else we can think of implementing
// using two halves linked list, first linked list will contain middle

class FrontMiddleBackQueue {

    // as the calls limited to 1000
    // we can design it by BRUTE force like ARRAY or else we can think of implementing
    // using two halves linked list, first linked list will contain middle

    static final int LIMIT = 1000;
    int Q[] = new int[LIMIT];
    // front inclusive and rear exclusive
    int front = 0, rear = 0;
    //int size = 0;

    public FrontMiddleBackQueue() {
        // TODO
    }

    // DEBUG purpose
    void display() {
        System.out.println("Front: " + front + " Rear: " + rear);
        for(int i = front; i < rear; i++) {
            System.out.print(Q[i] + " ");
        }
        System.out.println();
    }
    
    public void pushFront(int val) {
        // shift all elements to right
        for(int i = rear - 1; i >= front; i--) {
            Q[i + 1] = Q[i];
        }
        Q[front] = val;
        rear++;

        // display();
    }
    
    public void pushMiddle(int val) {
        int mid = (front + rear) / 2;
        for(int i = rear - 1; i >= mid; i--) {
            Q[i + 1] = Q[i];
        }
        Q[mid] = val;
        rear++;

        // display();
    }
    
    public void pushBack(int val) {
        Q[rear] = val;
        rear++;

        // display();
    }
    
    public int popFront() {
        int size = rear - front;
        if(size == 0) {
            return -1;
        }

        int val =  Q[front];
        front++;

        // display();

        return val;
    }
    
    public int popMiddle() {
        int size = rear - front;
        if(size == 0) {
            return -1;
        }

        int mid = (front + rear - 1) / 2;
        int val = Q[mid];

        // shift right elements to left
        for(int i = mid; i < rear; i++) {
            Q[i] = Q[i+1];
        }

        rear--;
        // display();

        return val;
    }
    
    public int popBack() {
        int size = rear - front;
        if(size == 0) {
            return -1;
        }

        int val = Q[rear - 1];
        rear--;
        // display();

        return val;
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */