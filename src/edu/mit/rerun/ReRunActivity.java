package edu.mit.rerun;

import edu.mit.rerun.SimpleGestureFilter.SimpleGestureListener;
import edu.mit.rerun.view.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReRunActivity extends Activity implements SimpleGestureListener {

    /** Called when the activity is first created. */
    private SimpleGestureFilter detector;
    private boolean hello = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        detector = new SimpleGestureFilter(this, this);
        Button next = (Button) findViewById(R.id.logInButton);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // TODO- temporary code here for view change

                setContentView(R.layout.postitem);
            }

        });
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    public void onSwipe(int direction) {
        String str = "";

        switch (direction) {

        case SimpleGestureFilter.SWIPE_RIGHT:
            str = "Swipe Right";
            break;
        case SimpleGestureFilter.SWIPE_LEFT:
            str = "Swipe Left";
            break;
        case SimpleGestureFilter.SWIPE_DOWN:
            str = "Swipe Down";
            break;
        case SimpleGestureFilter.SWIPE_UP:
            str = "Swipe Up";
            break;

        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void onDoubleTap() {
        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
    }


}