package lc.add2numbers;

import lc.add2numbers.ListNode;

public class SolutionTestUtils {

    /**
     * Convert an array of integers in a linked list of ListNode´s.
     * @param values array of values in reverse order. I.e for representing
     *              number 12345 , please pass [5,4,3,2,1]
     */
    public static ListNode buildLinkedList(int... values) {

        ListNode node = null;
        ListNode nextNode = null;
        for (int i = values.length - 1; i >= 0; i--) {
            if (values[i] < 0 || values[i] > 9) {
                throw new IllegalArgumentException("Invalid node value!");
            } else {
                node = new ListNode(values[i], nextNode);
                nextNode = node;
            }
        }
        return node;
    }
}
