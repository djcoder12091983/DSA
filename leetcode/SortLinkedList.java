// https://leetcode.com/problems/sort-list/
// TODO -- recursive merge instead of iterative merge
// or else can we think of reuse existing node instead of new creation - O(1) space

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

    ListNode mid(ListNode head) {
        if(head.next.next == null) {
            // size is 2
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    ListNode sort(ListNode head) {
        if(head == null || head.next == null) {
            // 0 or 1 already sorted
            return head;
        }

        ListNode middle = mid(head);
        // partition
        ListNode head1 = head;
        ListNode head2 = middle.next;
        middle.next = null;

        // call recursively to sort two list
        head1 = sort(head1);
        head2 = sort(head2);

        // merge two sorted list
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        while(head1 != null && head2 != null) {
            if(head1.val <= head2.val) {
                node.next = new ListNode(head1.val);
                head1 = head1.next;
            } else {
                node.next = new ListNode(head2.val);
                head2 = head2.next;
            }
            node = node.next;
        }

        while(head1 != null) {
            node.next = new ListNode(head1.val);
            head1 = head1.next;
            node = node.next;
        }

        while(head2 != null) {
            node.next = new ListNode(head2.val);
            head2 = head2.next;
            node = node.next;
        }

        return dummy.next;
    }

    public ListNode sortList(ListNode head) {
        return sort(head);
    }
}