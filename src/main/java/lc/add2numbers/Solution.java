package lc.add2numbers;

import java.math.BigDecimal;
import java.math.BigInteger;

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

        BigInteger n1 = decodeNumber(l1);
        BigInteger n2 = decodeNumber(l2);

        BigInteger sum = n1.add(n2);

        return encodeNumber(sum);

    }


    public static ListNode encodeNumber(BigInteger number) {

        int numberOfDigits = (number.compareTo(BigInteger.ZERO)==0) ?
                1 :
                (int) (1 + Math.floor (Math.log10(number.doubleValue())));

        ListNode node = null;
        ListNode previous = null;
        for (int i=numberOfDigits-1; i >=0; i--) {
            //long digit = number.divide() / (long) (Math.pow(10,i));
            BigInteger digit = number.divide(BigInteger.TEN.pow(i));
            number = number.subtract(digit.multiply(BigInteger.TEN.pow(i)));

            node = new ListNode( digit.intValue() );

            if (previous!=null) {
                node.next = previous;
            }
            previous = node;

        }
        return node;
    }



    public static BigInteger decodeNumber(ListNode node) {


        BigInteger number= BigInteger.ZERO;
        for (int power=0; node != null  ; power++) {
            number = number.add( BigInteger.valueOf(node.val).multiply(BigInteger.TEN.pow(power)) );
            node = node.next;
        }
        return number;
    }

}