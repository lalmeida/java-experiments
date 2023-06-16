package features.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Concurrency {


    public static List<Integer> parallelSum100(Map<String, Integer> map, int executionTimes) throws InterruptedException {

        List<Integer> sumList = new ArrayList<>(1000);
        for (int i = 0; i < executionTimes; i++) {
            map.put("test", 0);
            ExecutorService executorService = Executors.newFixedThreadPool(4);
            for (int j = 0; j < 10; j++) {
                executorService.execute( () -> {

                    for (int k = 0; k < 10; k++) {

                        map.computeIfPresent(
                                "test",
                                (key, value) -> value + 1
                        );

                    }

                });
            }
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            sumList.add( map.get("test"));
        }

        return sumList;

    }

    public static void LongPutIncrementingValue(Map<String, Integer> map, String key, int timeInSeconds) throws InterruptedException, ExecutionException, TimeoutException {

        ExecutorService updateService = Executors.newSingleThreadExecutor();
        updateService.submit(() ->
            map.compute(key, (k,v) -> {
                try {
                    Thread.sleep(timeInSeconds * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return ++v;
            })
        );

        updateService.shutdown();

    }
}
