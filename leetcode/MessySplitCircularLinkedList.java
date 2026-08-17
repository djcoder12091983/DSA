https://leetcode.com/problems/split-a-circular-linked-list/

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
    public ListNode[] splitCircularLinkedList(ListNode list) {
        ListNode head1 = list;

        // TODO need to simplify the odd even case logic

        // find middle
        ListNode slow = head1.next, fast = head1.next.next;
        ListNode prev = head1;
        int size = 1;
        while(fast != head1 && fast.next != head1) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
            size++;
        }
        
        // prev.next = null;
        // TODO can we simplify this edge case for odd case
        // we will update as per odd even length
        ListNode tail1 = prev;
        

        // we will start from slow to create second half circular linked list
        ListNode head2 = slow;
        
        while(slow.next != head1) {
            slow = slow.next;
            size++;
        }

        size++;

        // TODO can we simplify this edge case
        // based on odd even size we will handle odd case where extra node will be removed
        // added to tail to first list
        if(size % 2 == 1) {
            ListNode temp = head2.next;
            head2.next = head1;
            head2 = temp;
        } else {
            // for even it's evenly partitioned
            tail1.next = head1;
        }

        slow.next = head2;

        return new ListNode[]{head1, head2};
    }
}