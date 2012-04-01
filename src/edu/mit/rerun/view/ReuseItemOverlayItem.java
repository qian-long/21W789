package edu.mit.rerun.view;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class ReuseItemOverlayItem extends OverlayItem {

    private String id;
    
    public ReuseItemOverlayItem(GeoPoint point, String title, String snippet, String id) {
        super(point, title, snippet);
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
}
