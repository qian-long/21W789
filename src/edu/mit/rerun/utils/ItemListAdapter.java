package edu.mit.rerun.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

public class ItemListAdapter extends ArrayAdapter<ReuseItem> {

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
                    Intent intent = new Intent(v.getContext(),
                            ItemDetailActivity.class);
                    intent.putExtra("item_id", item.getId());
                    intent.putExtra("title", item.getTitle());
                    intent.putExtra("description", item.getDescription());
                    intent.putExtra("location", item.getLocation());
                    intent.putExtra("latitude", item.getLatitude());
                    intent.putExtra("longitude", item.getLongitude());
                    context.startActivity(intent);
                }
            });
            TextView title = (TextView) view.findViewById(R.id.item_title);
            TextView timestamp = (TextView) view.findViewById(R.id.item_timestamp);
            title.setText(item.getTitle());
//            timestamp.setText(item.getTime());
            try {
                long mins = getDifferenceInMinutes(item.getTime());
                String time = "";
                if (mins > 60 && mins <= 1440) {
                    long hour = mins / 60;
                    time = "over " + hour + " hours ago";
                }
                else if (mins > 1440 && mins <= 10080) {
                    time = "over 24 hours ago";
                }
                else if (mins > 10080) {
                    time = "over a week ago";
                }
                else {
                    time = mins + " mins ago";
                }
                timestamp.setText(time);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                timestamp.setText("error");
//                e.printStackTrace();
            }
        }
        return view;
    }

    private static SimpleDateFormat df = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    
    private long getDifferenceInMinutes(String sdf) throws ParseException {
        Date d1=df.parse(sdf);
        System.out.println(d1);
        long d1Ms=d1.getTime();
//        System.out.println(d1Ms);
//        System.out.println(System.currentTimeMillis());
//        System.out.println(System.currentTimeMillis()-d1Ms);
        return (long) ((System.currentTimeMillis()-d1Ms)/1000/60);
    }
}
