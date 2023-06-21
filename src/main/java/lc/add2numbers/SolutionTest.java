package lc.add2numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {


    @Test
    public void encode_decode_3 () {

        ListNode l1 = new ListNode(3);

        assertEquals(l1, Solution.encodeNumber(3) );
        assertEquals(3, Solution.decodeNumber(l1) );

    }


    @Test
    public void encode_decode_9 () {

        ListNode l1 = new ListNode(9);

        assertEquals(l1, Solution.encodeNumber(9) );
        assertEquals(9, Solution.decodeNumber(l1) );

    }


    @Test
    public void encode_decode_13 () {

        ListNode l1 = new ListNode(3, new ListNode(1));



        assertEquals(l1, Solution.encodeNumber(13) );
        assertEquals(13, Solution.decodeNumber(l1) );

    }


    @Test
    public void encode_decode_109 () {

        ListNode l1 = new ListNode( 9, new ListNode(0, new ListNode(1)));



        assertEquals(l1, Solution.encodeNumber(109) );
        assertEquals(109, Solution.decodeNumber(l1) );

    }

    @Test
    public void encode_decode_9991 () {

        ListNode l1 = new ListNode( 1, new ListNode( 9, new ListNode(9, new ListNode(9))));


        assertEquals(l1, Solution.encodeNumber(9991) );
        assertEquals(9991, Solution.decodeNumber(l1) );

    }

    @Test
    public void encode_decode_Zero () {

        ListNode l1 = new ListNode( 0);


        assertEquals(l1, Solution.encodeNumber(0) );
        assertEquals(0, Solution.decodeNumber(l1) );

    }

  

}
