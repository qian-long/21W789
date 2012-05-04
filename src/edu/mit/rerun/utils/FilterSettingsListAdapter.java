package edu.mit.rerun.utils;

import java.util.ArrayList;
import java.util.Comparator;

import android.app.AlertDialog;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import edu.mit.rerun.R;
import edu.mit.rerun.model.Filter;
import edu.mit.rerun.view.EditFilterActivity;
import edu.mit.rerun.view.ItemListActivity;

public class FilterSettingsListAdapter extends ArrayAdapter<Filter> {
    public static final String TAG = "FilterSettingsListActivity";
    private ArrayList<Filter> filters;
    private LayoutInflater inflator;
    private Context context;

    // pointer to the database adapter
    private DatabaseAdapter mDbAdapter;

    public FilterSettingsListAdapter(Context context,
            ArrayList<Filter> filters, DatabaseAdapter dbAdapter) {
        super(context, 0, filters);
        this.context = context;
        this.filters = filters;
        mDbAdapter = dbAdapter;
        inflator = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final Filter filter = filters.get(position);
        if (filter != null) {
            view = inflator.inflate(R.layout.list_entry_filter_item, null);

            view.setOnClickListener(null);
            view.setOnLongClickListener(null);
            view.setLongClickable(false);

            TextView filterName = (TextView) view
                    .findViewById(R.id.filter_name);
            ImageButton edit = (ImageButton) view
                    .findViewById(R.id.filter_edit);
            ImageButton delete = (ImageButton) view
                    .findViewById(R.id.filter_delete);
            final CheckBox use = (CheckBox) view.findViewById(R.id.filter_use);

            filterName.setText(filter.getFiltername());
            mDbAdapter.open();
            Log.i(TAG,
                    filter.getFiltername() + ": "
                            + mDbAdapter.isFilterUsed(filter.getFiltername()));
            if (mDbAdapter.isFilterUsed(filter.getFiltername())) {
                use.setChecked(true);
            } else {
                use.setChecked(false);
            }
            mDbAdapter.close();
            use.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    mDbAdapter.open();
                    if (use.isChecked()) {
                        mDbAdapter.updatedFilterUse(filter.getFiltername(),
                                true);
                    } else {
                        mDbAdapter.updatedFilterUse(filter.getFiltername(),
                                false);

                    }
                    mDbAdapter.close();

                }
            });

            edit.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),
                            EditFilterActivity.class);
                    intent.putExtra("filterName", filter.getFiltername());
                    // context.startActivity(intent);
//                    ((Activity) context).startActivityForResult(intent,
//                            ItemListActivity.ADD_FILTER_RESULT);
                    ((Activity) context).startActivity(intent);

                }
            });

            delete.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    final AlertDialog confirm = new AlertDialog.Builder(v
                            .getContext()).create();
                    confirm.setTitle("Deleting Filter");
                    confirm.setMessage("Are you sure you want to delete your "
                            + filter.getFiltername() + " filter");
                    confirm.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    String name = filters.get(position)
                                            .getFiltername();
                                    filters.remove(position);
                                    mDbAdapter.open();
                                    mDbAdapter.removeFilter(name);
                                    mDbAdapter.close();
                                    notifyDataSetChanged();
                                    confirm.dismiss();
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

        }
        return view;
    }
}
