package lc.add2numbers;

import java.util.Objects;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Objects.requireNonNull(l1);
        Objects.requireNonNull(l2);

        int carry = 0;
        ListNode dummy = new ListNode(0); //dummy node

        ListNode previous = dummy;
        ListNode current;

        while (l1 != null || l2 != null) {
            int value1 = 0;
            if (l1 != null) {
                value1 = l1.val;
                l1 = l1.next; // advancing pointer
            }

            int value2 = 0;
            if (l2 != null) {
                value2 = l2.val;
                l2 = l2.next; // advancing pointer
            }

            int sum = value1 + value2 + carry;
            carry = sum/10;
            current = new ListNode(sum%10);
            previous.next = current; // linking previous node to current node

            previous = current; // advancing pointer

        }

        if (carry == 1) {
            current = new ListNode(carry);
            previous.next = current;  // linking previous node to current node
        }

        return dummy.next;
    }

}