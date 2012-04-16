package edu.mit.rerun.view;

import edu.mit.rerun.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditFilterActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_filter);
        Button createFilterButton = (Button) findViewById(R.id.create_filter_button);
        
        createFilterButton.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FilterSettingsActivity.class);
                startActivity(intent);
            }
        });
        
    }
}
