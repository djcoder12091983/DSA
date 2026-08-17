// https://leetcode.com/problems/split-linked-list-in-parts/

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
    public ListNode[] splitListToParts(ListNode head, int k) {
        // first we will see how can arrange the parts
        int parts[] = new int[k];
        Arrays.fill(parts, 0);

        int size = 0;
        ListNode node = head;
        while(node != null) {
            node = node.next;
            size++;
        }

        // arrangements
        if(size < k) {
            for(int i = 0; i < size; i++) {
                parts[i] = 1;
            }
        } else {
            // TODO could be simplified
            int x = Double.valueOf(Math.ceil(1.0 * size / k)).intValue();

            for(int i = 0; i < k; i++) {
                parts[i] = x;
                if(x * (i + 1) + (x - 1) * (k - i - 1) == size) {
                    // we can put x - 1 in the remaining slots
                    for(int j = i + 1; j < k; j++) {
                        parts[j] = x - 1;
                    }
                    break;
                }
            }
        }

        // now split linked list into parts
        ListNode ans[] = new ListNode[k];
        node = head;
        for(int i = 0; i < k; i++) {
            int c = parts[i];
            //System.out.println("i: " + i + " c: " + c);
            ans[i] = node; // part head node
            ListNode prev = null;
            while(c > 0) {
                c--;
                prev = node;
                node = node.next;
            }

            if(prev != null) {
                prev.next = null; // break the link
            }
        }

        return ans;
    }
}