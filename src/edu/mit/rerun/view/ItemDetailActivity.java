package edu.mit.rerun.view;


import edu.mit.rerun.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class is the Activity for viewing detailed information about an item
 * 	-sender
 * 	-location
 * 	-title
 * 	-description
 * 
 * Has map icon and claim icon(when close enough to item)
 * 
 * Menu Item: 
 * 
 */
public class ItemDetailActivity extends Activity{
    public static final String TAG = "ItemDetailActivity";
    private String itemId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);
        
        Bundle extras = getIntent().getExtras();
        itemId = extras.getString("item_id");
        final String title = extras.getString("title");
        final String description = extras.getString("description");
        final String location = extras.getString("location");
        final int latitude = extras.getInt("latitude");
        final int longitude = extras.getInt("longitude");
        
        ImageButton mapButton = (ImageButton)findViewById(R.id.see_map_button);
        ImageButton claimButton = (ImageButton)findViewById(R.id.claim_button);
        TextView titleView = (TextView) findViewById(R.id.item_name);
        TextView descriptionView = (TextView) findViewById(R.id.item_description);
        mapButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ItemMapActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("description", description);
                intent.putExtra("location", location);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                startActivity(intent);
            }
            
        });
        
        claimButton.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                //stub
                Toast.makeText(v.getContext(), "Item Claimed Stub", Toast.LENGTH_SHORT).show();
            }
        });
        
        titleView.setText(title);
        descriptionView.setText(description);

    }
    
}
