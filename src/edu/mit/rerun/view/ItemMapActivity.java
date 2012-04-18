package edu.mit.rerun.view;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import edu.mit.rerun.R;
import edu.mit.rerun.model.ReuseItem;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

/**
 * This class is the Activity for viewing items on a map. - shows items on a map
 * with pins - clicking on pins pops up a balloon showing basic summary of item
 * (title and location) - clicking on balloon navigates to
 * ItemDetailActivity.java
 * 
 */
public class ItemMapActivity extends MapActivity {
    public static final int MIT_CENTER_LAT = 42359425;
    public static final int MIT_CENTER_LONG = -71094735;
    MapView mapView;
    List<Overlay> mapOverlays;
    ReuseItemizedOverlay itemizedOverlay;

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
        // dummy data
        List<ReuseItem> items = new ArrayList<ReuseItem>();
        items.add(new ReuseItem("id1", "sender1", "title1", "description1", "location1", "4/16/2012", 42358881,-71090137));
        items.add(new ReuseItem("id2", "sender2", "title2", "description2", "location2", "4/16/2012", 42358072,-71092701));
        items.add(new ReuseItem("id3", "sender3", "title3", "description3", "location3", "4/16/2012", 42359000,-71094702));

        refreshMap(items);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    private void refreshMap(List<ReuseItem> objects) {
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapOverlays = mapView.getOverlays();

        Drawable drawable = this.getResources().getDrawable(
                R.drawable.map_pin);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        itemizedOverlay = new ReuseItemizedOverlay(drawable, this, mapView);

        MapController controller = mapView.getController();
        int centerLat = MIT_CENTER_LAT;
        int centerLong = MIT_CENTER_LONG;

        // fix where map zooms to on start
        if (objects != null && objects.size() != 0) {
            centerLat = objects.get(0).getLatitude();
            centerLong = objects.get(0).getLongitude();
        }
        controller.setZoom(17);
        controller.animateTo(new GeoPoint(centerLat, centerLong));

        //adds items to overlay
        for (ReuseItem item : objects) {
            GeoPoint gp = new GeoPoint(item.getLatitude(), item.getLongitude());
            ReuseItemOverlayItem overlayItem = new ReuseItemOverlayItem(gp,
                    item.getTitle(), item.getLocation(), item.getId());
            overlayItem.setMarker(drawable);
            itemizedOverlay.addOverlay(overlayItem);
        }
        
        mapOverlays.add(itemizedOverlay);
    }

}
