package lc.add2numbers;

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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        long n1 = decodeNumber(l1);
        long n2 = decodeNumber(l2);

        long sum = n1+n2;

        return encodeNumber(sum);

    }


    public static ListNode encodeNumber(long number) {

        int numberOfDigits = (number == 0) ?
                1 :
                (int) (1 + Math.floor (Math.log10(number)));

        ListNode node = null;
        ListNode previous = null;
        for (int i=numberOfDigits-1; i >=0; i--) {
            long digit = number / (long) (Math.pow(10,i));
            number -= digit * (long) (Math.pow(10,i));

            node = new ListNode( (int) digit);

            if (previous!=null) {
                node.next = previous;
            }
            previous = node;

        }
        return node;
    }



    public static long decodeNumber(ListNode node) {
        long number=0;
        for (int power=0; node != null  ; power++) {
            number += node.val * Math.pow(10, power);
            node = node.next;
        }
        return number;
    }

}