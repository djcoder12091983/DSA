// TODO need to handle edge case to avoid cycle
// https://leetcode.com/problems/swapping-nodes-in-a-linked-list/

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
    public ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode node = head;
        ListNode prev = dummy;
        while(k > 1) {
            prev = node;
            node = node.next;
            k--;
        }
        ListNode p1 = prev;

        // now move one pointer from head and another from current position
        ListNode t = head;
        prev = dummy;
        while(node.next != null) {
            prev = t;
            node = node.next;
            t = t.next;
        }

        ListNode p2 = prev;

        // now we have 2 prev pointers of target nodes to be swapped
        ListNode t1 = p1.next;
        ListNode t2 = p2.next;

        ListNode next1 = t1.next;
        ListNode next2 = t2.next;

        // System.out.println("P1: " + p1.val + " t1: " + t1.val + " next1: " + next1.val);
        // System.out.println("P2: " + p2.val + " t2: " + t2.val + " next2: " + next2.val);

        if(t1.next == t2) {
            // TODO need to think can we generalize teh logic for all cases
            // edge - case
            p1.next = t2;
            t2.next = t1;
            t1.next = next2;
        } else {
            p1.next = t2;
            t2.next = next1;
            p2.next = t1;
            t1.next = next2;
        }

        return dummy.next;
    }
}