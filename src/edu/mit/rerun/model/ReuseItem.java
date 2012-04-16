package edu.mit.rerun.model;

/**
 * Model to represent a Reuse Item
 * sender, latitude, longitude, title, description
 *
 */
public class ReuseItem {
    private String id;
    private String sender;
    private String title;
    private String description;
    private String location;
    private String time;
    private int latitude;
    private int longitude;
	public ReuseItem(String id, String sender, String title, String description, String location, String time, int latitude, int longitude) {
		this.id = id;
	    this.sender = sender;
		this.title = title;
		this.description = description;
		this.location = location;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		
	}
	
	//getters
	public String getId() {
	    return new String(id);
	}
	public String getSender() {
	    return new String(sender);
	}
	
	public String getTitle() {
	    return new String(title);
	}
	
	public String getDescription() {
	    return new String(description);
	}
	
	public String getLocation() {
	    return new String(location);
	}
	
	public String getTime() {
	    return new String(time);
	}
	
	public int getLatitude() {
	    return latitude;
	}
	
	public int getLongitude() {
	    return longitude;
	}
}
