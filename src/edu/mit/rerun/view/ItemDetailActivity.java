package edu.mit.rerun.view;


import edu.mit.rerun.R;
import edu.mit.rerun.model.ReuseItem;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class is the Activity for viewing detailed information about an item
 * 	-sender
 * 	-location
 * 	-title
 * 	-description
 * 
 * Has map icon and claim icon(when close enough to item)
 * 
 * Menu Item: 
 * 
 */
public class ItemDetailActivity extends Activity{
    public static final String TAG = "ItemDetailActivity";
    private String itemId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);
        
        Bundle extras = getIntent().getExtras();
        itemId = extras.getString("item_id");
        final String title = extras.getString("title");
        final String description = extras.getString("description");
        final String location = extras.getString("location");
        final int latitude = extras.getInt("latitude");
        final int longitude = extras.getInt("longitude");
        
        ImageButton mapButton = (ImageButton)findViewById(R.id.see_map_button);
        ImageButton claimButton = (ImageButton)findViewById(R.id.claim_button);
        TextView titleView = (TextView) findViewById(R.id.item_name);
        TextView descriptionView = (TextView) findViewById(R.id.item_description);
        mapButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ItemMapActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("description", description);
                intent.putExtra("location", location);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                startActivity(intent);
            }
            
        });
        
        claimButton.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                //stub
                Toast.makeText(v.getContext(), "Item Claimed Stub", Toast.LENGTH_SHORT).show();
            }
        });
        
        titleView.setText(title);
        descriptionView.setText(description);

    }
    
    /**
     * Checks to see if user is connected to wifi or 3g
     * 
     * @return
     */
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ItemDetailActivity.this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {

            networkInfo = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if (!networkInfo.isAvailable()) {
                networkInfo = connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            }
        }
        return networkInfo == null ? false : networkInfo.isConnected();
    }
    
    /*
    public class RefreshTask extends AsyncTask<String, byte[], ReuseItem> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "RefreshTask onPreExecute");
            dialog = ProgressDialog.show(ItemDetailActivity.this, "",
                    "Refreshing Printer Data", true);
        }

        @Override
        protected ReuseItem doInBackground(String... params) { // This runs on a
                                                          // different thread
           // String result = "";
//            ReuseItem item = null;
//            if (isConnected()) {
//                try {
//                     
//                     printer = query.get(id);
//                    //result = refresh();
//                } catch (ClientException e) {
//                    // e.printStackTrace();
//                    Log.e(TAG, "RefreshTask Parse NUBFAIL");
//                    //result = PrinterInfoActivity.REFRESH_ERROR;
//                }
//            } else {
//                //result = PrinterInfoActivity.REFRESH_ERROR;
//            }
//            //return result;
//            return printer;
        }

        @Override
        protected void onCancelled() {
            Log.i(TAG, "RefreshTask Cancelled.");
        }

        @Override
        protected void onPostExecute(ReuseItem item) {

            if (item == null) {
                Toast.makeText(getApplicationContext(),
                        "Error getting data, please try again later",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG,
                        "RefreshTask onPostExecute: Completed with an Error.");
            }
//            displayInfo(printer);
            //TextView tv = (TextView) findViewById(R.id.printer_info_text);
            //tv.setText(result);
            dialog.dismiss();

        }
    }*/
}
