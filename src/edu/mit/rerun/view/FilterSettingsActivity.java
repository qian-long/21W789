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
		setContentView(R.layout.filter_settings);
		
	}
}