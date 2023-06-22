package lc.add2numbers;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {


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
