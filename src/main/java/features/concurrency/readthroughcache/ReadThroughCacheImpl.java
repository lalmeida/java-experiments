package features.concurrency.readthroughcache;


import java.util.concurrent.*;

public class ReadThroughCacheImpl implements ReadThroughCache {
    private final DataSource dataSource;

    private final ConcurrentHashMap<Integer, Future<String>> cache = new ConcurrentHashMap<>();

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public ReadThroughCacheImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String retrieveData(Integer id) throws ExecutionException, InterruptedException {
        return cache.computeIfAbsent(id, k -> executorService.submit( () -> dataSource.retrieveData(k)) ).get();
    }
}
