package features.concurrency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.Collections;
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
    public void synchronizedHashMapDoesBlockGets() throws InterruptedException, ExecutionException, TimeoutException {
        final String TEST_KEY = "test";

        Map<String, Integer>  map = Collections.synchronizedMap(new HashMap<String, Integer>());
        map.put(TEST_KEY, 0);

        Concurrency.LongPutIncrementingValue(map, TEST_KEY, 2);
        Thread.sleep(100); //waiting for long Put to start

        long start = System.currentTimeMillis();
        map.get("different_key");
        long end = System.currentTimeMillis();

        assertTrue(end - start > 1500, "Get should be blocked and wait for put to finish.");

    }

    /**
     * From ConcurrentHashMap javadoc´s
     * "Retrieval operations (including get) generally do not block"
     * Let´s test it!
     */
    @Test
    public void concurrentHashMapDoesNotBlockGets() throws InterruptedException, ExecutionException, TimeoutException {
        final String TEST_KEY = "test";

        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put(TEST_KEY, 0);

        Concurrency.LongPutIncrementingValue(map, TEST_KEY, 2);
        Thread.sleep(100); //waiting for long Put to start

        assertTimeout(Duration.ofSeconds(1), () -> map.get(TEST_KEY),
                "ConcurrentHashMap gets should not get blocked");
    }


}
