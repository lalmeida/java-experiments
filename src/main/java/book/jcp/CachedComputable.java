package book.jcp;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
* My solution to the last problem presented in the Part 1 of the book
*  Java Concurrency in Practice (by Brian Goetz and others)
*
* It is basically a simplified concurrent cache with no expiration policy or size limits.
*
* */
public class CachedComputable<A, V> implements Computable<A, V> {

    private final ConcurrentHashMap<A, FutureTask<V>> map = new ConcurrentHashMap<>();

    private final Computable<A, V> computable;

    public CachedComputable(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        FutureTask<V> newFutureTask = new FutureTask<>(() -> computable.compute(arg));

        FutureTask<V> cachedFutureTask = map.putIfAbsent(arg, newFutureTask);
        if (cachedFutureTask==null) { // if newFutureTask was added to the cache
            newFutureTask.run();
            cachedFutureTask=newFutureTask;
        }

        try {
            return cachedFutureTask.get();
        } catch (CancellationException e) {
            map.remove(cachedFutureTask);
            throw e;
        } catch (ExecutionException e) {
            map.remove(cachedFutureTask);
            throw new RuntimeException(e);
        }
    }

}
