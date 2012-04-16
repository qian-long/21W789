package edu.mit.rerun.view;

import java.util.ArrayList;
import java.util.List;

import edu.mit.rerun.R;
import edu.mit.rerun.model.ReuseItem;
import android.app.Activity;
import android.app.ListActivity;
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
public class ItemListActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_item_list);
//        ImageButton postButton = (ImageButton) findViewById(R.id.postButton);
//        ImageButton addButton = (ImageButton) findViewById(R.id.addButton);
//        
//        postButton.setOnClickListener(new View.OnClickListener() {
//            
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Intent intent = new Intent(v.getContext(), PostItemActivity.class);
//                startActivity(intent);
//            }
//        });
//        
//        addButton.setOnClickListener(new View.OnClickListener() {
//            
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Intent intent = new Intent(v.getContext(), EditFilterActivity.class);
//                startActivity(intent);
//            }
//        });
        List<ReuseItem> samples = new ArrayList<ReuseItem>();
        samples.add(new ReuseItem("id", "sender", "title", "description", "location", "4/16/2012", 123456, 234456));
        samples.add(new ReuseItem("id", "sender", "title", "description", "location", "4/16/2012", 123456, 234456));
        samples.add(new ReuseItem("id", "sender", "title", "description", "location", "4/16/2012", 123456, 234456));
        samples.add(new ReuseItem("id", "sender", "title", "description", "location", "4/16/2012", 123456, 234456));
        samples.add(new ReuseItem("id", "sender", "title", "description", "location", "4/16/2012", 123456, 234456));
        samples.add(new ReuseItem("id", "sender", "title", "description", "location", "4/16/2012", 123456, 234456));

        ItemListAdapter adapter = new ItemListAdapter(this, (ArrayList<ReuseItem>)samples);
        setListAdapter(adapter);

    }
}
