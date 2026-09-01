// https://leetcode.com/problems/remove-linked-list-elements/

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
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode node = dummy.next;
        ListNode prev = dummy;
        while(node != null) {
            if(node.val == val) {
                prev.next = node.next; // remove the link and fix the pointer
                node = prev.next;
            } else {
                // move forward
                prev = node;
                node= node.next;
            }
        }

        return dummy.next;
    }
}