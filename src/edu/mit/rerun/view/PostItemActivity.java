package edu.mit.rerun.view;

import edu.mit.rerun.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



/**
 * This class is the Activity for posting an item.
 * User should input title, description, location.
 * Post will be emailed to reuse when user presses "Post" button.
 * 
 * Menu Item: 
 *
 */
public class PostItemActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.postitem);
		Button postButton = (Button) findViewById(R.id.post_button);

		postButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO 
				Toast.makeText(getApplicationContext(), "Post Item stub", Toast.LENGTH_SHORT);
			}
		});
	}

}
