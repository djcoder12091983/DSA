https://leetcode.com/problems/next-greater-element-ii/

class Solution {
    public int[] nextGreaterElements(int[] A) {
        // stack based approach
        int N = A.length;
        Stack<Integer> track = new Stack<>();
        // in this case to manage circular array we need to iterate the array two times
        int greater[] = new int[2*N];
        Arrays.fill(greater, -1); // by default -1
        // the idea is manage monotonic decreasing sequence and when it sees greater then check 
        // how many stack elements are less than current element and update accordingly by their index
        for(int i = 0; i < 2*N; i++) {
            if(track.isEmpty()) {
                // track index to update answer at that index when monitonic decreasing sequence breaks
                track.add(i);
            } else {
                int x = A[i % N]; // circular
                if(x > A[track.peek() % N]) {
                    // need to check how many stack elements are less than current element
                    while(!track.isEmpty() && x > A[track.peek() % N]) {
                        greater[track.pop()] = x; // update next greater
                    }
                }
                // manage monotonic decreasing sequence
                track.add(i);
            }
        }

        
        return Arrays.copyOfRange(greater, 0, N);
    }
}