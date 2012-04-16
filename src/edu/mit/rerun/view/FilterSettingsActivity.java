package edu.mit.rerun.view;

import edu.mit.rerun.R;
import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

/**
 * This Activity is where users can change their filters. 
 *
 */
public class FilterSettingsActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filter_list);

		//		String[] filters = getResources().getStringArray(R.array.filter_list_array);
		//		setListAdapter(new ArrayAdapter<String>(this, R.layout.filter_item, filters));
		//		ListView lv = getListView();
		//		lv.setTextFilterEnabled(true);

		Button button = (Button) findViewById(R.id.editFilterButton1);
		button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), EditFilterActivity.class);
				startActivity(intent);
			}
		});
	}
}