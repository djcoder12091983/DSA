class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0, j = 0;
        
        int N = pushed.length, M = popped.length;
        
        while(i < N && j < M) {
        
            if(!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            } else {
                stack.push(pushed[i]);
                i++;
            }
        }
        
        // check whether pop order is OK
        while(!stack.isEmpty() && stack.peek() == popped[j]) {
            stack.pop();
            j++;
        }
        
        return stack.isEmpty();
        
    }
}