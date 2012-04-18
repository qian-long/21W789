package edu.mit.rerun.view;

import java.util.ArrayList;
import java.util.List;

import edu.mit.rerun.R;
import edu.mit.rerun.model.ReuseItem;
import edu.mit.rerun.utils.DatabaseAdapter;
import edu.mit.rerun.utils.ItemListAdapter;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This class is the Activity for viewing lists of items that can be filtered.
 * 
 * Menu Item: 
 * 
 */
public class ItemListActivity extends ListActivity {
	public static final int ADD_FILTER_RESULT = 0;
	private Context mContext = this;
	public DatabaseAdapter dba;
	private int currentFilterIndex = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_item_list);

		Button showAllButton = (Button) findViewById(R.id.showAllBtn);

		ImageButton filterButton = (ImageButton) findViewById(R.id.filterButton);
		ImageButton postButton = (ImageButton) findViewById(R.id.postButton);
		ImageButton addButton = (ImageButton) findViewById(R.id.addButton);

		ImageButton forwardFilterButton = (ImageButton) findViewById(R.id.forwardFilterBtn);
		ImageButton backFilterButton = (ImageButton) findViewById(R.id.backFilterBtn);
		ImageButton refreshButton = (ImageButton) findViewById(R.id.refreshBtn);

		final TextView filterName = (TextView) findViewById(R.id.filterName);

		dba = new DatabaseAdapter(mContext);

		showAllButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				filterName.setText("All Items");
				//TODO- actually hook up to filters
			}
		});

		filterButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), FilterSettingsActivity.class);
				startActivity(intent);
			}
		});

		postButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), PostItemActivity.class);
				startActivity(intent);
			}
		});

		addButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), EditFilterActivity.class);
				//				startActivity(intent);
				startActivityForResult(intent, ADD_FILTER_RESULT);
			}
		});

		forwardFilterButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dba.open();
				currentFilterIndex++;
				if (currentFilterIndex == dba.getAllFilters().size()) {
					filterName.setText("Show All");
					currentFilterIndex = 0;
				}
				String displayName = dba.getAllFilters().get(currentFilterIndex).getFiltername();
				filterName.setText(displayName);

				dba.close();

				//TODO- actually hook up to filters
			}
		});

		backFilterButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dba.open();
				currentFilterIndex--;
				if (currentFilterIndex < 0 ) {
					currentFilterIndex = dba.getAllFilters().size()-1;
				}
				String displayName = dba.getAllFilters().get(currentFilterIndex).getFiltername();
				filterName.setText(displayName);
				dba.close();			
			}
			//TODO- actually hook up to filters
		});

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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ADD_FILTER_RESULT) {
			Intent intent = new Intent((Context)this, FilterSettingsActivity.class);
			startActivity(intent);

		}
	}


}
