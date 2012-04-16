package edu.mit.rerun;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import edu.mit.rerun.SimpleGestureFilter.SimpleGestureListener;

public class SwipeTestActivity extends Activity implements edu.mit.rerun.SimpleGestureFilter.SimpleGestureListener{

	private SimpleGestureFilter detector; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		detector = new SimpleGestureFilter(this,this);
	}

	@Override 
	public boolean dispatchTouchEvent(MotionEvent me){ 
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me); 
	}

	public void onSwipe(int direction) {
		String str = "";

		switch (direction) {

		case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
		break;
		case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
		break;
		case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
		break;
		case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
		break;

		} 
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	public void onDoubleTap() {
		Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show(); 
	}

}