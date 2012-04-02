package edu.mit.rerun.view;

import edu.mit.rerun.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * This class is the Activity for viewing lists of items that can be filtered.
 * 
 * Menu Item: 
 * 
 */
public class ItemListActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
        ImageButton postButton = (ImageButton) findViewById(R.id.postButton);
        ImageButton addButton = (ImageButton) findViewById(R.id.addButton);
        
        postButton.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), PostItemActivity.class);
                startActivity(intent);
            }
        });
        
        addButton.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), EditFilterActivity.class);
                startActivity(intent);
            }
        });
    }
}
