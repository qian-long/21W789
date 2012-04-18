package edu.mit.rerun.view;

import edu.mit.rerun.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * This class is the Activity for posting an item.
 * User should input title, description, location.
 * Post will be emailed to reuse when user presses "Post" button.
 * 
 * Menu Item: 
 *
 */
public class PostItemActivity extends Activity implements LocationListener{

	private int lat = 00000;
	private int lon = 00000;
    private LocationManager mlocationManager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.postitem);
		
        mlocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if (mlocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mlocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20*1000, 5, this);
        }
        if (mlocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            mlocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }

		
		Button postButton = (Button) findViewById(R.id.post_button);

		postButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO 
				//Toast.makeText(v.getContext(), "test", Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(),
						"Item Posted \n " + "Lat: " + lat + " Lon:" + lon, Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		lat = (int)(location.getLatitude()*10E6);
		lon = (int)(location.getLongitude()*10E6);
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "provider DISABLED", Toast.LENGTH_SHORT).show();
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "provider ENABLED", Toast.LENGTH_SHORT).show();		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
