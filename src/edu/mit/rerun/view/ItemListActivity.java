package edu.mit.rerun.view;

import java.util.ArrayList;
import java.util.List;

import edu.mit.rerun.R;
import edu.mit.rerun.client.Client;
import edu.mit.rerun.client.ClientException;
import edu.mit.rerun.model.ReuseItem;
import edu.mit.rerun.utils.DatabaseAdapter;
import edu.mit.rerun.utils.ItemListAdapter;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
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

		//TODO- should be set to whatever the user left the filter as
		filterName.setText("Show All");

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

		RefreshListTask task = new RefreshListTask();
		if (isConnected(this)) {
			//TODO: get actual username
			task.execute("");

		} else {
			Toast.makeText(this, "Internet Error", Toast.LENGTH_SHORT);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ADD_FILTER_RESULT) {
			Intent intent = new Intent((Context) this,
					FilterSettingsActivity.class);
			startActivity(intent);

		}
	}


	public class RefreshListTask extends
	AsyncTask<String, byte[], List<ReuseItem>> {
		private ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(ItemListActivity.this, "",
					"Refreshing Items List", true);
		}

		@Override
		protected List<ReuseItem> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<ReuseItem> items = null;
			try {
				items = Client.getItemList(params[0]);
			} catch (ClientException e) {
				// e.printStackTrace();               
			}
			return items;
		}

		@Override
		protected void onPostExecute(List<ReuseItem> objects) { // happens in
			// UI thread
			// Bad practice, but meh, it'd be better if java had tuples
			if (objects == null) {
				Toast.makeText(getApplicationContext(),
						"Error getting data, please try again later",
						Toast.LENGTH_SHORT).show();
			}
			setListAdapter(new ItemListAdapter(mContext,
					(ArrayList<ReuseItem>) objects));
			dialog.dismiss();
		}

	}

	/**
	 * Checks to see if user is connected to wifi or 3g
	 * 
	 * @return
	 */
	private boolean isConnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
		.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = null;
		if (connectivityManager != null) {

			networkInfo = connectivityManager
			.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

			if (!networkInfo.isAvailable()) {
				networkInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			}
		}
		return networkInfo == null ? false : networkInfo.isConnected();

	}
}
