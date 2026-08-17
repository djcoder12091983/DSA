// https://leetcode.com/problems/palindrome-linked-list/description/


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
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true; // edge case
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
        
        // TODO think how it handles odd number of elements and even as well
        // and how it's applied for odd length palindrome and even length as well

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
        // now apply two pointer to check for palindrome
        ListNode p1 = head;
        ListNode p2 = prev;

        while(p1 != null && p2 != null) {
            if(p1.val != p2.val) {
                // not possible to be plaindrome
                return false;
            }

            p1 = p1.next;
            p2 = p2.next;
        }

        return true; // if all matches
    }
}