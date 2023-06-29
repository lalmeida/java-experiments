package features.concurrency.readthroughcache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReadThroughCacheTest {

    @Mock
    public DataSource stubDataSource;

    public ReadThroughCache rtCache;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        rtCache =  new ReadThroughCacheImpl(stubDataSource);
    }

    @Test
    public void givenTheCacheDoesNotContainElementX_whenRetrievingX_thenCacheShouldReturnValueFromDataSourceAndCacheItForNextAccess() throws Exception {

        when(stubDataSource.retrieveData(1)).thenReturn("initial-value");

        // if cache is empty, get from data source
        assertEquals("initial-value", rtCache.retrieveData(1), "should retrieve data from data source");
        verify(stubDataSource, VerificationModeFactory.only()).retrieveData(1);

        // if cache is populated, no more calls to data source
        assertEquals("initial-value", rtCache.retrieveData(1), "should not retrieve data from data source");
        verify(stubDataSource, VerificationModeFactory.noMoreInteractions()).retrieveData(1);

    }


    @Test
    public void givenTheCacheDoesNotContainElementX_whenSeveralThreadsAreRetrievingX_thenCacheShouldAccessDataSourceOnlyOnce() throws Exception {

        when(stubDataSource.retrieveData(2)).thenAnswer(invocation -> {
           Thread.sleep(500);
           return "slow-to-retrieve-value";
        });


        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10 ; i++) {
            executorService.submit(() -> rtCache.retrieveData(2));
        }

        executorService.shutdown();
        executorService.awaitTermination(3, TimeUnit.SECONDS);

        verify(stubDataSource, VerificationModeFactory.only()).retrieveData(2);
    }

}
