package edu.mit.rerun.view;

import java.util.ArrayList;
import java.util.List;

import edu.mit.rerun.R;
import edu.mit.rerun.client.Client;
import edu.mit.rerun.client.ClientException;
import edu.mit.rerun.model.ReuseItem;
import edu.mit.rerun.utils.ItemListAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class is the Activity for viewing detailed information about an item
 * -sender -location -title -description
 * 
 * Has map icon and claim icon(when close enough to item)
 * 
 * Menu Item:
 * 
 */
public class ItemDetailActivity extends Activity {
    public static final String TAG = "ItemDetailActivity";
    public static final int ITEM_DELETE = 0;
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

        ImageButton mapButton = (ImageButton) findViewById(R.id.see_map_button);
        ImageButton claimButton = (ImageButton) findViewById(R.id.claim_button);
        TextView titleView = (TextView) findViewById(R.id.item_name);
        TextView locationView = (TextView) findViewById(R.id.item_location);
        TextView descriptionView = (TextView) findViewById(R.id.item_description);
        mapButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),
                        ItemMapActivity.class);
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
                // stub
                Toast.makeText(v.getContext(), "Item Claimed Stub",
                        Toast.LENGTH_SHORT).show();
                final AlertDialog confirm = new AlertDialog.Builder(v
                        .getContext()).create();
                confirm.setTitle("Claiming Item");
                confirm.setMessage("Are you sure you are claiming this item? This will delete the item from the database");
                confirm.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                    int which) {
                                confirm.dismiss();
                                DeleteTask task = new DeleteTask();
                                task.execute(itemId);
                            }

                        });

                confirm.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                    int which) {
                                confirm.dismiss();
                            }
                        });
                confirm.show();
            }
        });

        titleView.setText(title);
        locationView.setText(location);
        descriptionView.setText(description);

    }
    
    public class DeleteTask extends AsyncTask<String, byte[], Boolean> {
        private ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(ItemDetailActivity.this, "",
                    "Deleting item from database", true);
        }
        @Override
        protected Boolean doInBackground(String... arg0) {
            boolean success = false;
            try {
                success = Client.deleteItem(arg0[0]);
            } catch (ClientException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.i(TAG, e.getMessage());

            }
            return success;
        }
        
        @Override
        protected void onPostExecute(Boolean success) { // happens in
            // UI thread
            // Bad practice, but meh, it'd be better if java had tuples
            if (!success) {
                Toast.makeText(getApplicationContext(),
                        "Error posting, please try again later",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Delete Success",
                        Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            setResult(ITEM_DELETE);
            finish();
        }
    }

}
