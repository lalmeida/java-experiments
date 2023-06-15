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

    public static void parallelGetAndLongPut(ConcurrentHashMap<String, Integer> map) throws InterruptedException, ExecutionException, TimeoutException {
        map.put("test", 0);

        ExecutorService updateService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 4; i++) {
            updateService.submit(() -> map.compute("test",
                    (k, v) -> {
                        System.out.println("Starting update: Old Value:" + v);
                        v++;
                        try {
                            Thread.sleep(5 * 1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Finishing update: New Value:" + v);
                        return v;
                    })

            );
        }

        ScheduledExecutorService readService = Executors.newScheduledThreadPool(2);
        Runnable task = () -> {

            try {
                System.out.println("Getting value: " + map.get("test"));
            } catch (Exception e ) {
                System.out.println("Exception! : " + e + ", cause: " + e.getCause());
            }

        };
        ScheduledFuture<?> sFuture =
                readService.scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS);


        // pause for scheduling to take place
        Thread.sleep(30 * 1000);

        System.out.println("Final sch Future: " + sFuture);


        updateService.shutdown();
        readService.shutdown();
        updateService.awaitTermination(0 , TimeUnit.SECONDS);
        readService.awaitTermination(0 , TimeUnit.SECONDS);

        System.out.println("Final Value: " + map.get("test"));
        System.out.println("Final sch Future: " + sFuture);

    }
}
