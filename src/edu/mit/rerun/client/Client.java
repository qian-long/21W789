package edu.mit.rerun.client;

import java.util.List;

import edu.mit.rerun.model.Filter;
import edu.mit.rerun.model.ReuseItem;

/**
 * This class is responsible for connecting to and retrieving data from the backend.
 *
 */
public class Client {
    public static final String GET_ITEMS_URL = "http://127.0.0.1:8080/query/?user=%s";
    public static final String CHANGE_FILTER_URL = "http://127.0.0.1:8080/change_filter";
    public static final String POST_ITEM_URL = "http://127.0.0.1:8080/post_item";
    
    public static List<ReuseItem> getItemList(String userId, List<Filter> filters) {
        return null;
    }
}
