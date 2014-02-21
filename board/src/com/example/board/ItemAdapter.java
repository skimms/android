package com.example.board;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter{
	Context context;
	LayoutInflater inflater;
	ArrayList<ItemObject> itemObject;
	int layout;
	
	public ItemAdapter(Context context, int layout, ArrayList<ItemObject> itemObject){
		this.context = context;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.itemObject = itemObject;
		this.layout = layout;
	}
	
	public int getCount(){
		return itemObject.size();
	}
	
	public String getItem(int position){
		return itemObject.get(position).getTitle();
	}
	
	public long getItemId(int position){
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		final int pos = position;
		if(convertView == null){
			convertView = inflater.inflate(layout, parent, false);
		}
		
		TextView text1 = (TextView) convertView.findViewById(R.id.item1);
		text1.setText(itemObject.get(position).getTitle());
		
		TextView text2 = (TextView) convertView.findViewById(R.id.item2);
		text2.setText(itemObject.get(position).getUserId());
		
		TextView text3 = (TextView) convertView.findViewById(R.id.item3);
		text3.setText(itemObject.get(position).getContent());
		
		return convertView;
	}
}
