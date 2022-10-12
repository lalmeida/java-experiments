package blog.tragedycc;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WatchlistStoreTest {

    public static final String OIL_MARKET = "OIL";
    public static final String GOLD_MARKET = "GOLD";
    WatchlistStore watchlistStore = new WatchlistStore();

//    @Test
//    public void initialize() {
//        watchlistStore.initialize(
//                callback -> {
//                    callback.accept("OIL");
//                    callback.accept("GOLD");
//                }
//        );
//    }


    @Test
    public void initialize() {

        //Given we have only 2 markets (Oil & Gold) in the Watchlist
        watchlistStore.initialize(
                new WatchlistDao() {
                    @Override
                    public void loadAll(Consumer<? super String> action) {
                        action.accept(OIL_MARKET);
                        action.accept(GOLD_MARKET);
                    }
                }
        );

        //When I inspect  my watchlist
        List<String> wl = watchlistStore.getWatchlists();

        //Then I find only Gold and Oil
        assertTrue(wl.size() ==2, "Watchlist should have 2 markets");
        assertTrue(wl.contains(GOLD_MARKET), "Watchlist should contain "+ GOLD_MARKET);
        assertTrue(wl.contains(OIL_MARKET), "Watchlist should contain "+ OIL_MARKET);

    }



}