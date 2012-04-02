package edu.mit.rerun.view;

import edu.mit.rerun.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        
        button.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ItemListActivity.class);
                startActivity(intent);
            }
        });

    }
}
