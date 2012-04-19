package edu.mit.rerun.view;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

import edu.mit.rerun.model.ReuseItem;

public class ReuseItemOverlayItem extends OverlayItem {

    private String id;
    private ReuseItem item;
    public ReuseItemOverlayItem(GeoPoint point, String title, String snippet, String id, ReuseItem item) {
        super(point, title, snippet);
        this.id = id;
        this.item = item;
    }
    
    public String getId() {
        return id;
    }
    
    public ReuseItem getReuseItem() {
        return item;
    }
}
