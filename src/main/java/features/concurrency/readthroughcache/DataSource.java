package features.concurrency.readthroughcache;

public interface DataSource {

    public String retrieveData(Integer id) throws InterruptedException;

}
