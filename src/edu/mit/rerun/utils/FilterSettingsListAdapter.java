package edu.mit.rerun.utils;

import java.util.ArrayList;
import java.util.Comparator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class FilterSettingsListAdapter extends ArrayAdapter<Filter> {

    private ArrayList<Filter> filters;
    private LayoutInflater inflator;
    private Context context;
    
    //pointer to the database adapter
    private DatabaseAdapter mDbAdapter;

    public FilterSettingsListAdapter(Context context, ArrayList<Filter> filters, DatabaseAdapter dbAdapter) {
        super(context, 0, filters);
        // TODO Auto-generated constructor stub
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
            CheckBox use = (CheckBox) view.findViewById(R.id.filter_use);

            filterName.setText(filter.getFiltername());
            edit.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),
                            EditFilterActivity.class);
                    intent.putExtra("filterName", filter.getFiltername());
                    context.startActivity(intent);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    final AlertDialog confirm = new AlertDialog.Builder(v
                            .getContext()).create();
                    confirm.setTitle("Deleting Filter");
                    confirm.setMessage("Are you sure you want to delete your "
                            + filter.getFiltername() + " filter");
                    confirm.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                        
                        public void onClick(DialogInterface dialog, int which) {
                            String name = filters.get(position).getFiltername();
                            filters.remove(position);
                            mDbAdapter.open();
                            mDbAdapter.removeFilter(name);
                            mDbAdapter.close();
                            notifyDataSetChanged();
                            confirm.dismiss();
                        }
                    });
                    confirm.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                        
                        public void onClick(DialogInterface dialog, int which) {
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
