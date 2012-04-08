package edu.mit.rerun;

import edu.mit.rerun.view.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReRunActivity extends Activity {
	/** Called when the activity is first created. */
	private boolean hello = true;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);

		Button next = (Button) findViewById(R.id.logInButton);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				// TODO- temporary code here for view change
				//System.out.println("hello");
				setContentView(R.layout.postitem);
			}

		});
	}
}