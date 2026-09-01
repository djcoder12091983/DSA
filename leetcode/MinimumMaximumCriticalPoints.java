// https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/description
// TODO -- may need to solve without using extra space

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // we will store LL into an array otherwise we need to pass LL twice in forward direction
        // and backward direction by reversing it

        ListNode node = head;
        List<Integer> data = new ArrayList<>();
        while(node != null) {
            data.add(node.val);
            node = node.next;
        }

        // store the index of local maxima and minima
        List<Integer> index = new ArrayList<>();
        for(int i = 1; i < data.size() - 1; i++) {
            int x = data.get(i);
            int prev = data.get(i - 1);
            int next = data.get(i + 1);

            if(prev < x && x > next) {
                // maxima
                index.add(i);
            } else if(prev > x && x < next) {
                // minima
                index.add(i);
            }
        }

        // as index sorted we can compute maximum distance by taing first and last
        // and minimum one by seeing consecutive one
        int N = index.size();
        if(N <= 1) {
            // not possible
            return new int[]{-1, -1};
        }

        int max = index.get(N - 1) - index.get(0);
        int min = max + 1;
        for(int i = 0; i < N - 1; i++) {
            min = Math.min(min, index.get(i + 1) - index.get(i));
        }

        return new int[]{min, max};
    }
}