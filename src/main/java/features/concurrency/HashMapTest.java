package features.concurrency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {


    @Test
    public void parallelSumParallelDoesNotWorkWithHashMap() throws InterruptedException {

        Map<String, Integer> map = new HashMap<>();
        List<Integer> sumList = Concurrency.parallelSum100(map, 100);

        assertNotEquals( 1, sumList.stream().distinct().count() ,
                "HashMap should produce different sums" );

        assertTrue( sumList
                .stream()
                .filter( sum -> sum != 100)
                .count() > 1 ,
                "HashMap should produce a few wrong (!=100) sums"
        );

    }

    @Test
    public void sumParallelDoesWorkWithConcurrentHashMap() throws InterruptedException {

        Map<String, Integer> map = new ConcurrentHashMap<>();
        List<Integer> sumList = Concurrency.parallelSum100(map, 100);

        System.out.println("Number of Different sums: " +
                sumList.stream().distinct().count()
        );

        System.out.println("Number of wrong sums (different than 100) " +
                sumList.stream().filter( sum -> sum != 100).count()
        );

        assertEquals( 1, sumList.stream().distinct().count() ,
                "ConcurrentHashMap should not produce different sums" );

        assertEquals(0,sumList
                        .stream()
                        .filter( sum -> sum != 100)
                        .count(),
                "ConcurrentHashMap should not produce wrong (!=100) sums");

    }

    @Test
    public void synchronizedHashMapBlocksGet() throws InterruptedException, ExecutionException, TimeoutException {

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        Concurrency.parallelGetAndLongPut(map);



    }

    // todo create a slow computation for  putIfAbsent/computeIfAbsent/computeIfPresent
    // and check if it blocks threads wanting to read that key-value pair

    // check & implement read-through cache

}
