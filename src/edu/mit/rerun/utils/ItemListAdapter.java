package edu.mit.rerun.utils;

import java.util.ArrayList;

import edu.mit.rerun.R;
import edu.mit.rerun.model.ReuseItem;
import edu.mit.rerun.view.ItemDetailActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
		final ReuseItem item = items.get(position);
		if (item != null) {
			view = inflator.inflate(R.layout.item_row, null);
			view.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(v.getContext(), ItemDetailActivity.class);
					intent.putExtra("item_id", item.getId());
					context.startActivity(intent);
				}
			});
			TextView title = (TextView) view.findViewById(R.id.item_title);
			TextView timestamp = (TextView) view.findViewById(R.id.item_timestamp);
			title.setText(item.getTitle());
			timestamp.setText(item.getTime());
		}
		return view;
	}
}
