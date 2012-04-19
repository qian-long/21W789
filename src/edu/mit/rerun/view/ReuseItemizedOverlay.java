package edu.mit.rerun.view;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.MapView.LayoutParams;

import edu.mit.rerun.R;
import edu.mit.rerun.model.ReuseItem;



/**
 * An overlay showing item locations in ItmMapActivity. Some of the code
 * is taken from
 * https://github.com/MIT-Mobile/MIT-Mobile-for-Android/blob/master
 * /src/edu/mit/mitmobile2/maps/MITItemizedOverlay.java
**/
public class ReuseItemizedOverlay extends ItemizedOverlay<ReuseItemOverlayItem> {
    private ArrayList<ReuseItemOverlayItem> mOverlayItems = new ArrayList<ReuseItemOverlayItem>();
    private Context mContext;
    private MapView mMapView;
    private BubbleOverlayView bubbleView;
    private View clickableRegion;
    private Drawable mDefaultMarker;
    public ReuseItemizedOverlay(Drawable defaultMarker, Context ctx, MapView mapView) {
        super(boundCenterBottom(defaultMarker));
        populate();
        mContext = ctx;
        mMapView = mapView;
        mDefaultMarker = defaultMarker;
    }

    @Override
    protected ReuseItemOverlayItem createItem(int i) {
        return mOverlayItems.get(i);
    }

    @Override
    public int size() {
        return mOverlayItems.size();
    }

    
    public void addOverlay(ReuseItemOverlayItem overlay) {
        mOverlayItems.add(overlay);
        populate();
    }
    
    /**
     * Pops up bubble when pin is tapped
     */
    @Override
    protected boolean onTap(int index) {
        // return super.onTap(index);

        makeBubble(mOverlayItems.get(index));
        return true;
    }
    
    /**
     * Removes any balloons when touching elsewhere on map.
     */
    @Override
    public boolean onTouchEvent(MotionEvent e, MapView mapView) {
        if (mapView != null && bubbleView != null) {
            mapView.removeView(bubbleView);
        }
        return super.onTouchEvent(e, mapView);

    }
    
    protected void makeBubble(final ReuseItemOverlayItem item) {
        GeoPoint gp = item.getPoint();

        mMapView.removeView(bubbleView);

//        bubbleView = new BubbleOverlayView(mContext, mBubbleOffset);
        bubbleView = new BubbleOverlayView(mContext, Math.round((float).75*mDefaultMarker.getIntrinsicHeight()));

        clickableRegion = bubbleView.findViewById(R.id.balloon_inner_layout);

        clickableRegion.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                View l = ((View) v.getParent())
                        .findViewById(R.id.balloon_main_layout);
                Drawable d = l.getBackground();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    int[] states = { android.R.attr.state_pressed };
                    if (d.setState(states)) {
                        d.invalidateSelf();
                    }
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    int newStates[] = {};
                    if (d.setState(newStates)) {
                        d.invalidateSelf();
                    }

                    handleTap(item);

                    return true;
                } else {
                    return false;
                }
            }
        });

        bubbleView.setData(item);

        MapView.LayoutParams params = new MapView.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, gp, MapView.LayoutParams.BOTTOM_CENTER);

        params.mode = MapView.LayoutParams.MODE_MAP;
        bubbleView.setLayoutParams(params);

        bubbleView.setVisibility(View.VISIBLE);


        mMapView.getController().animateTo(gp);

        mMapView.addView(bubbleView);


    }
    
    /**
     * Balloon tap brings user to item info page.
     * @param item
     */
    protected void handleTap(ReuseItemOverlayItem item) {
        Intent intent = new Intent(mContext, ItemDetailActivity.class);
        ReuseItem ri = item.getReuseItem();
        intent.putExtra("item_id", ri.getId());
        intent.putExtra("title", ri.getTitle());
        intent.putExtra("location", ri.getLocation());
        intent.putExtra("description", ri.getDescription());
        intent.putExtra("latitude", ri.getLatitude());
        intent.putExtra("latitude", ri.getLongitude());
        mContext.startActivity(intent);

    }
    
}
