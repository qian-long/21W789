package edu.mit.rerun.view;

import edu.mit.rerun.R;
import android.app.*;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

/**
 * This Activity is where users can change their filters. 
 *
 */
public class SettingsActivity extends ListActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.filter_list);

		String[] filters = getResources().getStringArray(R.array.filter_list_array);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.filter_item, filters));
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}