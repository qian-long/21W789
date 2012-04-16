package edu.mit.rerun.view;

import java.util.ArrayList;

import edu.mit.rerun.model.ReuseItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ItemListAdapter extends ArrayAdapter<ReuseItem>{

    private ArrayList<ReuseItem> items;
    private LayoutInflater inflator;
    private Context context;

    
    public ItemListAdapter(Context context, ArrayList<ReuseItem> items) {
        super(context, 0, items);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.items = items;
        inflator = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ReuseItem item = items.get(position);
        if (item != null) {
            
        }
        return view;
    }
}
