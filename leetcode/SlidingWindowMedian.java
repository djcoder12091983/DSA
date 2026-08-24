// https://leetcode.com/problems/sliding-window-median/
// TODO need to work on execution time, may need to optimize some DS and all

class Solution {

    // as the window is sliding PQ may not help as it helps to solve streaming median
    // because in sliding window i need to remove the elements so i may need to use BST
    // here we will use two BST to manage two halves

    // note : map is required for handling position, so that it would be easy to track the position
    // during removal from window, in case same element share same tree then position would help to delete
    // first tree will contain first half
    TreeMap<Integer, HashSet<Integer>> first = new TreeMap<>();
    // so the second one
    TreeMap<Integer, HashSet<Integer>> second = new TreeMap<>();

    // these variable will track how many elements are there in tree
    // tree size will fail because it will track unique elements only
    int s1 = 0, s2 = 0;

    // remove x by position p
    void remove(int x, int p) {
        boolean found = false;
        if(first.containsKey(x)) {
            HashSet<Integer> position = first.get(x);
            if(position.contains(p)) {
                // found it
                found = true;
                position.remove(p); // remove the position
                if(position.isEmpty()) {
                    // remove the entire key
                    first.remove(x);
                }

                // remove from first tree
                s1--;
            }
        }

        if(!found) {
            // not found
            // look into second one
            HashSet<Integer> position = second.get(x);
            position.remove(p);

            if(position.isEmpty()) {
                // remove the entire key
                second.remove(x);
            }

            // remove from second tree
            s2--;
        }
    }

    // remove from map and return the index which is removed
    int remove(int x, TreeMap<Integer, HashSet<Integer>> map) {
        HashSet<Integer> position = map.get(x);
        int p = position.iterator().next();

        position.remove(p);
        if(position.isEmpty()) {
            map.remove(x); // remove the entire key
        }

        return p;
    }

    // add to tree by position
    void add(int x, int p, TreeMap<Integer, HashSet<Integer>> map) {
        if(!map.containsKey(x)) {
            map.put(x, new HashSet<Integer>(2));
        }

        map.get(x).add(p);
    }

    // add a number and balance tree, such that first half will go to first tree 
    // and second will go to second
    void add(int x, int p) {

        // in case of odd number of elements first tree will contain extra one

        add(x, p, first); // add to first by position then balance
        // always added to first
        s1++;

        if(s1 - s2 > 1) {
            // need to balance so we will take extra one (the maximum value) put into second one
            int top = first.lastKey();
            p = remove(top, first); // remove maximum from first and put into second one

            add(top, p, second);

            // here it's balanced
            s1--;
            s2++;
        } else {
            // maximum from first tree and minimum from second map
            if(!second.isEmpty()) {
                int top1 = first.lastKey();
                int top2 = second.firstKey();

                if(top1 > top2) {
                    // violating that first half contains some elements from second one
                    int p1 = remove(top1, first);
                    int p2 = remove(top2, second);

                    // swap the positions
                    add(top2, p2, first);
                    add(top1, p1, second);
                }
            }
        }
    }

    double median() {
        if(s1 == s2) {
            // even case
            return (0.0 + first.lastKey() + second.firstKey()) / 2.0;
        } else {
            // odd case
            return first.lastKey();
        }
    }

    public double[] medianSlidingWindow(int[] A, int k) {
        // first window
        int i = 0;
        while(i < k) {
            int x = A[i];
            add(x, i); // add by position and balance two halves
            
            i++;
        }

        int N = A.length;
        double ans[] = new double[N - k + 1];
        int p1 = 0;
        int p2 = k;
        i = 0;

        //System.out.println(first + " " + second);
        ans[i++] = median();

        // slid window and find median for each window
        while(p2 < N) {
            // remove A[p1] from window
            remove(A[p1], p1);

            // add A[p2]
            add(A[p2], p2);

            //System.out.println(first + " " + second);
            ans[i++] = median();

            p1++;
            p2++;
        }

        return ans;
    }
}