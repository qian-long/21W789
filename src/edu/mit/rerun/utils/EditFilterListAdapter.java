package edu.mit.rerun.utils;

import java.util.ArrayList;

import edu.mit.rerun.R;


import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class EditFilterListAdapter extends ArrayAdapter<String>{
	public static final String TAG="EditFilterListAdapter";
	private ArrayList<String> keywords;
	private LayoutInflater inflator;
	private Context context;
	private Context parentContext;

	public EditFilterListAdapter(Context context, ArrayList<String> keywords, Context parentContext) {
		super(context, 0, keywords);
		this.context = context;
		this.parentContext = parentContext;
		this.keywords = keywords;
		inflator = (LayoutInflater) context
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/*
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final ArrayList<String> keywords = this.keywords;
        View view = convertView;
        String keyword = keywords.get(position);
        if (view == null) {
            holder = new ViewHolder();
            view = inflator.inflate(R.layout.list_entry_keyword, null);
            holder.text = (EditText) view.findViewById(R.id.keyword_input);
            holder.text.setImeOptions(EditorInfo.IME_ACTION_DONE);

            //update adapter
            holder.text.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                    Toast.makeText(context, "afterTextChanged: " + position + " " + s.toString(), Toast.LENGTH_SHORT).show();


                    keywords.set(position, s.toString());

                }

                public void beforeTextChanged(CharSequence s, int start,
                        int count, int after) {
                    // TODO Auto-generated method stub
//                    ToastCmakeText(context, "beforeTextChanged" + position, Toast.LENGTH_SHORT).show();

                }

                public void onTextChanged(CharSequence s, int start,
                        int before, int count) {
                    // TODO Auto-generated method stub
//                    Toast.makeText(context, "onTextChanged", Toast.LENGTH_SHORT).show();

                }

            });
            view.setTag(holder);

            view.setOnClickListener(null);
            view.setOnLongClickListener(null);
            view.setLongClickable(false);

            Button delete = (Button) view.findViewById(R.id.delete_btn);

            delete.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    // TODO Auto-generated method stub
//                    Intent intent = new Intent(v.getContext(), EditFilterActivity.class);
//                    context.startActivity(intent);
                    Toast.makeText(context, "deleting keyword: " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            holder = (ViewHolder) view.getTag();

            //Fill EditText with the value you have in data source
            if (keyword != null){
                holder.text.setText(keyword);
            }
//            holder.text.setId(position);



            (new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    // TODO Auto-generated method stub
                    if (!hasFocus) {
                        final int position = v.getId();
                        final EditText input = (EditText) v;
                        String word = keywords.get(position);
                        word = input.getText().toString(); 
                    }
                }

            });


        }
        holder.text.setText(keywords.get(position));
        return view;
    }

    class ViewHolder {
        EditText text;
    }
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		String keyword = keywords.get(position);
		if (keyword != null) {
			convertView = inflator.inflate(R.layout.list_entry_keyword, null);
			convertView.setOnClickListener(null);
			convertView.setOnLongClickListener(null);
			convertView.setLongClickable(false);

			final TextView text = (TextView) convertView.findViewById(R.id.keyword);
			text.setText(keyword);

			ImageButton delete = (ImageButton) convertView.findViewById(R.id.delete_btn);

			delete.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					keywords.remove(position);
					notifyDataSetChanged();
				}
			});
			Log.i(TAG, "set delete button listener");
			ImageButton edit = (ImageButton) convertView.findViewById(R.id.edit_btn);

			edit.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					final Dialog dialog = new Dialog(v.getContext());
					dialog.setContentView(R.layout.dialog_keyword_input);
					dialog.setTitle("Edit Keyword");
					dialog.setCancelable(false);

					Button addBtn = (Button) dialog.findViewById(R.id.save_btn);
					Button cancelBtn = (Button) dialog.findViewById(R.id.cancel_btn);
					final EditText input = (EditText) dialog.findViewById(R.id.keyword_input);
					
					addBtn.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							if (checkInput(input.getText().toString().trim())) {

								text.setText(input.getText().toString().trim());
							}
							dialog.dismiss();
						}
					});

					cancelBtn.setOnClickListener(new View.OnClickListener() {

						public void onClick(View v) {
							dialog.dismiss();
						}
					});

					dialog.show();
				}
			});
		}
		Log.i(TAG, "set EDIT button listener");

		return convertView;
	}

	//TODO
	private boolean checkInput(String input) {
		return true;
	}
}
