package edu.mit.rerun;

import android.app.Activity;
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
        setContentView(R.layout.main);
        final TextView view = (TextView) findViewById(R.id.text1);
        view.setText("hello, 21w.789");
        Button button = (Button) findViewById(R.id.button1);
        
        button.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (hello) {
                    view.setText("goodbye, 21w.789");
                    hello = false;
                }
                else {
                    view.setText("hello, 21w.789");
                    hello = true;
                }
            }
        });
    }
}