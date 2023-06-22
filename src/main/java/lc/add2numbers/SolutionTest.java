package lc.add2numbers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {


    Solution solution = new Solution();


    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "0, 0, 0",
            "0, 1, 1",
            "2, 0, 2",
            "9, 9, 8->1",
            "1->1, 2->2, 3->3",
            "1->5, 5->2, 6->7",
            "1->7, 7->7, 8->4->1",
            "1->1->1, 2->2->2, 3->3->3",
            "1->9->9, 9->9->8, 0->9->8->1",
            "1, 9->9->8, 0->0->9",
            "1->9->9, 2, 3->9->9",
            "9->9->9, 2, 1->0->0->1"
    })
    public void shouldSumProperly(
            @ConvertWith(ListNodeParser.class) ListNode l1,
            @ConvertWith(ListNodeParser.class) ListNode l2,
            @ConvertWith(ListNodeParser.class) ListNode sum) {
        checkSum(l1, l2, sum);
    }

    private void checkSum(ListNode l1, ListNode l2, ListNode sum) {
        assertEquals(sum, solution.addTwoNumbers(l1, l2));
        assertEquals(sum, solution.addTwoNumbers(l2, l1));
    }

    @Test
    public void encode_decode_3 () {

        ListNode l1 = new ListNode(3);

        assertEquals(l1, Solution.encodeNumber(BigInteger.valueOf(3)) );
        assertEquals(BigInteger.valueOf(3), Solution.decodeNumber(l1) );

    }


    @Test
    public void encode_decode_9 () {

        ListNode l1 = new ListNode(9);

        assertEquals(l1, Solution.encodeNumber(BigInteger.valueOf(9)) );
        assertEquals(BigInteger.valueOf(9), Solution.decodeNumber(l1) );

    }


    @Test
    public void encode_decode_13 () {

        ListNode l1 = new ListNode(3, new ListNode(1));



        assertEquals(l1, Solution.encodeNumber(BigInteger.valueOf(13)) );
        assertEquals(BigInteger.valueOf(13), Solution.decodeNumber(l1) );

    }


    @Test
    public void encode_decode_109 () {

        ListNode l1 = new ListNode( 9, new ListNode(0, new ListNode(1)));



        assertEquals(l1, Solution.encodeNumber(BigInteger.valueOf(109)) );
        assertEquals(BigInteger.valueOf(109), Solution.decodeNumber(l1) );

    }

    @Test
    public void encode_decode_9991 () {

        ListNode l1 = new ListNode( 1, new ListNode( 9, new ListNode(9, new ListNode(9))));


        assertEquals(l1, Solution.encodeNumber(BigInteger.valueOf(9991)) );
        assertEquals(BigInteger.valueOf(9991), Solution.decodeNumber(l1) );

    }

    @Test
    public void encode_decode_Zero () {

        ListNode l1 = new ListNode( 0);

        assertEquals(l1, Solution.encodeNumber(BigInteger.ZERO) );
        assertEquals(BigInteger.ZERO, Solution.decodeNumber(l1) );

    }



}
