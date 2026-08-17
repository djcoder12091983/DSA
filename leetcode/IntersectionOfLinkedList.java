// https://leetcode.com/problems/intersection-of-two-linked-lists/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode A, ListNode B) {
        int l1 = 0;
        ListNode x = A;
        while(x != null) {
            x = x.next;
            l1++;
        }

        int l2 = 0;
        x = B;
        while(x != null) {
            x = x.next;
            l2++;
        }

        // idea is get the difference of two linked list then balance two linked linked lists
        // by moving ahead by extra length of longer linked list
        // then keep on moving till common node found; 

        ListNode p1 = A, p2 = B;
        if(l1 >= l2) {
            int d = l1 - l2;
            while(d > 0) {
                p1 = p1.next;
                d--;
            }
        } else {
            int d = l2 - l1;
            while(d > 0) {
                p2 = p2.next;
                d--;
            }
        }

        while(p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }
}