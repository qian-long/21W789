package edu.mit.rerun.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.mit.rerun.R;
import edu.mit.rerun.model.Filter;
import edu.mit.rerun.utils.DatabaseAdapter;
import edu.mit.rerun.utils.FilterSettingsListAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

/**
 * This Activity is where users can change their filters.
 * 
 */
public class FilterSettingsActivity extends ListActivity {
    public static final String TAG = "FilterSettingsActivity";
    private DatabaseAdapter mDbAdapter;
    private List<Filter> filters;
    
    // Comparator to sort filters alphabetically
    public class FilterComparator implements Comparator<Filter> {
        
        public int compare(Filter item1, Filter item2) {
            return item1.getFiltername().compareTo(item2.getFiltername());
        }

    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.filter_settings);
        mDbAdapter = new DatabaseAdapter(this);
        ImageButton postButton = (ImageButton) findViewById(R.id.postButton);
        ImageButton addButton = (ImageButton) findViewById(R.id.addButton);
        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton);

        postButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),
                        PostItemActivity.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),
                        EditFilterActivity.class);
                startActivity(intent);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(),
                        ItemListActivity.class);
                startActivity(intent);
            }
        });

        // populating filters from database
        mDbAdapter.open();
        filters = mDbAdapter.getAllFilters();
        mDbAdapter.close();
        
        Collections.sort(filters, new FilterComparator());
        setListAdapter(new FilterSettingsListAdapter(this, (ArrayList<Filter>)filters));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDbAdapter.open();
        filters = mDbAdapter.getAllFilters();
        mDbAdapter.close();
        Collections.sort(filters, new FilterComparator());
        setListAdapter(new FilterSettingsListAdapter(this, (ArrayList<Filter>)filters));

    }

}