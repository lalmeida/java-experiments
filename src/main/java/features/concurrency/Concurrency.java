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

    public static void parallelGetAndLongPut(ConcurrentHashMap<String, Integer> map) throws InterruptedException {
        map.put("test", 0);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit( () -> map.compute("test",
                (k, v) ->   {
                    v++;
                    System.out.println("New Value:" + v);
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return v;
                })

        );

        Thread.sleep(1000);

        for (int i=0; i < 10; i++) {
            executorService.submit( () ->
                    {
                        //new FutureTask(() -> {
                            Integer value = map.get("test");
                            System.out.println("Value retrieved: " + value);
                            return value;
                        //});

                    }
            );
        }


        executorService.shutdown();
        executorService.awaitTermination(10 , TimeUnit.SECONDS);

        System.out.println("Value: " + map.get("test"));

    }
}
