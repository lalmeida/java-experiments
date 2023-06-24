package features.concurrency.readthroughcache;

import java.util.concurrent.ExecutionException;

public interface ReadThroughCache {

    public String retrieveData(Integer id) throws ExecutionException, InterruptedException;

}
