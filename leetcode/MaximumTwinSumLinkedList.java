// https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/

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
    public int pairSum(ListNode head) {
        if(head == null) {
            return 0; // edge case
        }
        // trying to solve using O(1) space

        // first find middle
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while(fast != null && fast.next != null) {
            prev = slow;
            fast = fast.next.next;
            slow = slow.next;
        }

        // middle at slow pointer
        
        // detach the middle from first half
        prev.next = null;

        // reverse the second half
        prev = null;
        ListNode node = slow;
        while(node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }

        // now prev will point to new head of reversed linked list
        // now apply two pointer to do twin pair sum and maximize it
        ListNode p1 = head;
        ListNode p2 = prev;

        int ans = 0;

        while(p1 != null && p2 != null) {
            
            ans = Math.max(ans, p1.val + p2.val); // twin sum

            p1 = p1.next;
            p2 = p2.next;
        }

        return ans;
    }
}