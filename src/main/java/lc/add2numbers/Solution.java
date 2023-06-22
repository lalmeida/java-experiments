package lc.add2numbers;

import java.math.BigDecimal;
import java.math.BigInteger;
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
        ListNode node = new ListNode(0);
        ListNode start = node;

        while (true) {
            int value1 = 0;
            if (l1 != null) {
                value1 = l1.val;
                l1 = l1.next;
            }

            int value2 = 0;
            if (l2 != null) {
                value2 = l2.val;
                l2 = l2.next;
            }

            int sum = value1 + value2 + carry;
            carry = sum/10;
            node.val = sum%10;


            if (l1 != null || l2 != null) {
                node.next = new ListNode(0);
                node = node.next;
            } else if (carry != 0) { // both l1 & l2 are null
                node.next = new ListNode(1);
                break;
            } else {
                break;
            }

        }
        return start;
    }

}