package edu.mit.rerun.view;

import edu.mit.rerun.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);
        
        Button mapButton = (Button)findViewById(R.id.see_map_button);
        Button claimButton = (Button)findViewById(R.id.claim_button);
        
        mapButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ItemMapActivity.class);
                startActivity(intent);
            }
            
        });
        
        claimButton.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                //stub
                Toast.makeText(v.getContext(), "Item Claimed Stub", Toast.LENGTH_SHORT);
            }
        });

    }
}
