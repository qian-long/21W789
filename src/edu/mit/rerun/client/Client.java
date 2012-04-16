package edu.mit.rerun.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.text.Html;

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
    public static final String ADD_USER_URL = "http://127.0.0.1:8080/add_user/";
    
    public static List<ReuseItem> getItemList(String userId) {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        try {
            request.setURI(new URI(GET_ITEMS_URL));
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String json = Html.fromHtml(EntityUtils.toString(entity)).toString();
            JSONTokener tokener = new JSONTokener(json);
            JSONArray jsonArray = new JSONArray(tokener);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    
    public static void addFilter(String userId, Filter filter) {
        
    }
    
    public static void addKeyWordtoFilter(String userId, String filterName, String keyword) {
        
    }
    
    public static void removeFilter(String userId, Filter filter) {
        
    }
    
    public static void removeKeyWordfromFilter(String userId, String filterName, String keyword) {
        
    }
    
    public static void postItem(String userId, ReuseItem item) {
        
    }
    
    public static void addUser(String userId, String email, String password) {
        HttpClient client = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost();
//        postRequest.setParams(params)
    }
}
