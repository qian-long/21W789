package edu.mit.rerun.view;

import java.io.FileOutputStream;
import java.io.IOException;
import edu.mit.rerun.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This Activity is for users to login via Touchstone.
 * 
 */
public class LoginActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);
		Button button = (Button)findViewById(R.id.logInButton);

		final EditText usernameField = (EditText) findViewById(R.id.username);
		final EditText passwordField = (EditText) findViewById(R.id.password);

		button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				try {
					String username = usernameField.getText().toString();
					String password = passwordField.getText().toString();

					FileOutputStream usernameOS = openFileOutput("username_file", Context.MODE_PRIVATE);
					usernameOS.write(username.getBytes());
					usernameOS.close();

					FileOutputStream passwordOS = openFileOutput("password_file", Context.MODE_PRIVATE);
					passwordOS.write(password.getBytes());
					passwordOS.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Intent intent = new Intent(v.getContext(), ItemListActivity.class);
				startActivity(intent);
			}
		});

	}
}
