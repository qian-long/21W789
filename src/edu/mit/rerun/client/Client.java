package edu.mit.rerun.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.text.Html;
import android.util.Log;

import edu.mit.rerun.model.Filter;
import edu.mit.rerun.model.ReuseItem;

/**
 * This class is responsible for connecting to and retrieving data from the
 * backend.
 * 
 */
public class Client {
    public static final String TAG = "CLIENT";
    public static final String GET_ITEMS_URL = "http://qlong.scripts.mit.edu/rerun/query/";
    public static final String DELETE_ITEM_URL = "http://qlong.scripts.mit.edu/rerun/delete_item/?item_id=%s";
//    public static final String RETRIEVE_ITEM_URL = "http://18.111.105.86:8000/query/?item_id=%s";
    public static final String CHANGE_FILTER_URL = "http://127.0.0.1:8080/change_filter";
    public static final String POST_ITEM_URL = "http://qlong.scripts.mit.edu/rerun/post_item/";
    public static final String ADD_USER_URL = "http://127.0.0.1:8080/add_user/";

    public static List<ReuseItem> getItemList(String userId) throws ClientException {
        List<ReuseItem> items = new ArrayList<ReuseItem>();
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
//        String uri = String.format(GET_ITEMS_URL, userId);
        try {
            request.setURI(new URI(GET_ITEMS_URL));
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String json = Html.fromHtml(EntityUtils.toString(entity))
                    .toString();
            JSONTokener tokener = new JSONTokener(json);
            JSONArray jsonArray = new JSONArray(tokener);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id = object.getString("pk");
                JSONObject fields = object.getJSONObject("fields");
                items.add(new ReuseItem(id, fields.getString("sender"), fields
                        .getString("title"), fields.getString("description"),
                        fields.getString("location"), fields.getString("time"),
                        fields.getInt("latitude"), fields.getInt("longitude")));

            }
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ClientException("URISyntaxException");

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ClientException("ClientProtocolException");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ClientException("IOException");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ClientException("JSONEXception");

        }

        return items;
    }

    
    public static List<ReuseItem> getFilteredItems(String username, Filter filter) throws ClientException {
        List<ReuseItem> items = new ArrayList<ReuseItem>();
        JSONObject obj = new JSONObject();
        try {
            obj.put(filter.getFiltername(), filter.getKeyWordsString());
            Log.i(TAG, obj.toString());
            StringEntity stringentity = new StringEntity(obj.toString());
            HttpClient client = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost();
            postRequest.setURI(new URI(GET_ITEMS_URL));

            postRequest.addHeader("Accept", "application/json");
            postRequest.addHeader("Content-type", "application/json");
            postRequest.setEntity(stringentity);
            HttpResponse response = client.execute(postRequest);
            HttpEntity httpentity = response.getEntity();
            String json = Html.fromHtml(EntityUtils.toString(httpentity))
                    .toString();
            JSONTokener tokener = new JSONTokener(json);
            JSONArray jsonArray = new JSONArray(tokener);
            for (int i = 0; i < jsonArray.length(); i++) {
                
                JSONObject object = jsonArray.getJSONObject(i);
                String id = object.getString("pk");
                Log.i(TAG, id);
                JSONObject fields = object.getJSONObject("fields");
                items.add(new ReuseItem(id, fields.getString("sender"), fields
                        .getString("title"), fields.getString("description"),
                        fields.getString("location"), fields.getString("time"),
                        fields.getInt("latitude"), fields.getInt("longitude")));

            }
            
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ClientException("JSONException");

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ClientException("UnsupportedEncodingException");

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ClientException("ClientProtocolException");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ClientException("IOException");

        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ClientException("URISyntaxException");

        }

        return items;
    }
    /*
    public static ReuseItem getItem(String itemId) throws ClientException {
        ReuseItem item = null;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        String uri = String.format(GET_ITEMS_URL, itemId);
        try {
            request.setURI(new URI(GET_ITEMS_URL));
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String json = Html.fromHtml(EntityUtils.toString(entity))
                    .toString();
            JSONTokener tokener = new JSONTokener(json);
            JSONArray jsonArray = new JSONArray(tokener);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject object = jsonArray.getJSONObject(i);
//                String id = object.getString("pk");
//                JSONObject fields = object.getJSONObject("fields");
//                items.add(new ReuseItem(id, fields.getString("sender"), fields
//                        .getString("title"), fields.getString("description"),
//                        fields.getString("location"), fields.getString("time"),
//                        fields.getInt("latitude"), fields.getInt("longitude")));
//
//            }
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ClientException("URISyntaxException");

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ClientException("ClientProtocolException");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ClientException("IOException");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ClientException("JSONEXception");

        }

        return item;
    }*/
    public static void addFilter(String userId, Filter filter) {

    }

    public static void addKeyWordtoFilter(String userId, String filterName,
            String keyword) {

    }

    public static void removeFilter(String userId, Filter filter) {

    }

    public static void removeKeyWordfromFilter(String userId,
            String filterName, String keyword) {

    }
    
    /**
     * Deletes item from the backend server
     * @param itemId
     * @return
     * @throws ClientException
     */
    public static boolean deleteItem(String itemId) throws ClientException {
        boolean success = false;
        String uri = String.format(DELETE_ITEM_URL, itemId);
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        try {
            request.setURI(new URI(uri));
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                success = true;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new ClientException("URISyntaxException");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new ClientException("ClientProtocolException");

        } catch (IOException e) {
            e.printStackTrace();
            throw new ClientException("IOException");

        }
        return success;
    }

    /**
     * 
     * @param userId
     * @param params
     * @return
     *      True of post successful, False otherwise
     * @throws ClientException 
     */
    public static boolean postItem(String userId, ReuseItem params) throws ClientException {
        boolean success = false;
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", params.getId());
            obj.put("title", params.getTitle());
            obj.put("sender", params.getSender());
            obj.put("description", params.getDescription());
            obj.put("location", params.getLocation());
            obj.put("latitude", params.getLatitude());
            obj.put("longitude", params.getLongitude());
            StringEntity stringentity = new StringEntity(obj.toString());

            HttpClient client = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost();
            postRequest.setURI(new URI(POST_ITEM_URL));

            postRequest.addHeader("Accept", "application/json");
            postRequest.addHeader("Content-type", "application/json");
            postRequest.setEntity(stringentity);
            HttpResponse response = client.execute(postRequest);
            if (response.getStatusLine().getStatusCode() == 200) {
                success = true;
            }
            
        } catch (JSONException e) {
            e.printStackTrace();
            throw new ClientException("JSONException");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ClientException("UnsupportedEncodingException");

        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new ClientException("URISyntaxException");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new ClientException("CliendProtocolException");

        } catch (IOException e) {
            e.printStackTrace();
            throw new ClientException("IOException");

        }
        return success;
    }

}
