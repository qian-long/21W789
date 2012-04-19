package edu.mit.rerun.view;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import edu.mit.rerun.R;
import edu.mit.rerun.client.Client;
import edu.mit.rerun.client.ClientException;
import edu.mit.rerun.model.Filter;
import edu.mit.rerun.model.ReuseItem;
import edu.mit.rerun.utils.ItemListAdapter;
import edu.mit.rerun.view.ItemListActivity.RefreshListTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * This class is the Activity for viewing items on a map. - shows items on a map
 * with pins - clicking on pins pops up a balloon showing basic summary of item
 * (title and location) - clicking on balloon navigates to
 * ItemDetailActivity.java
 * 
 */
public class ItemMapActivity extends MapActivity {
    public static final String TAG = "ItemMapActivity";
    public static final int MIT_CENTER_LAT = 42359425;
    public static final int MIT_CENTER_LONG = -71094735;
    MapView mapView;
    List<Overlay> mapOverlays;
    ReuseItemizedOverlay itemizedOverlay;
    private int zoomLat;
    private int zoomLong;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemmap);
        Bundle extras = getIntent().getExtras();
        final String title = extras.getString("title");
        final String description = extras.getString("description");
        final String location = extras.getString("location");
        final int latitude = extras.getInt("latitude");
        final int longitude = extras.getInt("longitude");
        zoomLat = latitude;
        zoomLong = longitude;

        RefreshListTask task = new RefreshListTask();
        if (isConnected(this)) {
            // TODO: get actual username
            task.execute();

        } else {
            Toast.makeText(this, "Internet Error", Toast.LENGTH_SHORT);
        }
    }

    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    private void refreshMap(List<ReuseItem> objects) {
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapOverlays = mapView.getOverlays();

        Drawable drawable = this.getResources().getDrawable(R.drawable.map_pin);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        itemizedOverlay = new ReuseItemizedOverlay(drawable, this, mapView);

        MapController controller = mapView.getController();
        int centerLat = MIT_CENTER_LAT;
        int centerLong = MIT_CENTER_LONG;

        // fix where map zooms to on start
        if (objects != null && objects.size() != 0 && zoomLat != 0 && zoomLong != 0) {
            centerLat = zoomLat;
            centerLong = zoomLong;
        }
        controller.setZoom(17);
        controller.animateTo(new GeoPoint(centerLat, centerLong));

        // adds items to overlay
        for (ReuseItem item : objects) {
            if (item.getLatitude() != 0 && item.getLongitude() != 0) {
                GeoPoint gp = new GeoPoint(item.getLatitude(),
                        item.getLongitude());
                ReuseItemOverlayItem overlayItem = new ReuseItemOverlayItem(gp,
                        item.getTitle(), item.getLocation(), item.getId(), item);
                overlayItem.setMarker(drawable);
                itemizedOverlay.addOverlay(overlayItem);
            }
        }

        mapOverlays.add(itemizedOverlay);
    }

    public class RefreshListTask extends
            AsyncTask<Void, byte[], List<ReuseItem>> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(ItemMapActivity.this, "",
                    "Refreshing Items List", true);
        }

        @Override
        protected List<ReuseItem> doInBackground(Void... params) {
            List<ReuseItem> items = null;
            try {
                Log.i("RefreshListTask", "filter null");
                items = Client.getItemList("");

            } catch (ClientException e) {
                // e.printStackTrace();
                Log.i(TAG, e.getMessage());
            }
            return items;
        }

        @Override
        protected void onPostExecute(List<ReuseItem> objects) { // happens in
            // UI thread
            // Bad practice, but meh, it'd be better if java had tuples
            if (objects == null) {
                Toast.makeText(getApplicationContext(),
                        "Error getting data, please try again later",
                        Toast.LENGTH_SHORT).show();
            }
            refreshMap(objects);
            dialog.dismiss();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ItemDetailActivity.ITEM_DELETE) {
            RefreshListTask task = new RefreshListTask();
            if (isConnected(this)) {
                // TODO: get actual username
                task.execute();

            } else {
                Toast.makeText(this, "Internet Error", Toast.LENGTH_SHORT);
            }
        }
    }
    /**
     * Checks to see if user is connected to wifi or 3g
     * 
     * @return
     */
    private boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {

            networkInfo = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if (!networkInfo.isAvailable()) {
                networkInfo = connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            }
        }
        return networkInfo == null ? false : networkInfo.isConnected();

    }
}
