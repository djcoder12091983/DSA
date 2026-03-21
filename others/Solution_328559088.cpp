class MinStack {
private:
    // let's try with O(N) space
    // but it can be done using O(1)
    stack<int> main_stack, min_stack;
public:
    /** initialize your data structure here. */
    MinStack() {
        // already initialized
    }
    
    void push(int x) {
        main_stack.push(x);
        if(min_stack.empty() ) {
            min_stack.push(x);
        } else {
            // two cases arised
            if(x <= min_stack.top()) {
                min_stack.push(x);
            } else {
                min_stack.push(min_stack.top());
            }
        }
    }
    
    void pop() {
        main_stack.pop();
        min_stack.pop();
    }
    
    int top() {
        return main_stack.top();
    }
    
    int getMin() {
        return min_stack.top();
    }
};

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack* obj = new MinStack();
 * obj->push(x);
 * obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->getMin();
 */