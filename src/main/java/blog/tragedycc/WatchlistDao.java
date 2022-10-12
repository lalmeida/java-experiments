package blog.tragedycc;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class WatchlistDao {

    private final String[] ALL = new String[]{"Gold", "IGG", "Oil"};

    private final List<String> allWatchlists = Arrays.stream(ALL).toList();

    public void loadAll(Consumer<? super String> action) {
        allWatchlists.forEach(action);
    }

}
