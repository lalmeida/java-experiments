package blog.tragedycc;

import java.util.ArrayList;
import java.util.List;

public class WatchlistStore {

    private final List<String> watchlists = new ArrayList<>();

    public void initialize(WatchlistDao watchlistDao) {
        watchlistDao.loadAll (watchlistRow -> watchlists.add(watchlistRow));
    }

    List<String> getWatchlists() {
        return watchlists;
    }

}
