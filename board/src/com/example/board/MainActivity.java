package com.example.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
	ItemObject itemObject;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		TabHost mTab = getTabHost();
		
		LayoutInflater inflater = LayoutInflater.from(this);
		inflater.inflate(R.layout.activity_main, mTab.getTabContentView(), true );
		
		mTab.addTab(mTab.newTabSpec("tag")
				.setIndicator("일반")
				.setContent(R.id.opt_general));
		
		mTab.addTab(mTab.newTabSpec("tag")
				.setIndicator("일반2")
				.setContent(R.id.opt_general2));
		
		mTab.addTab(mTab.newTabSpec("tag")
				.setIndicator("일반3")
				.setContent(R.id.opt_general3)	);
		
		//첫번째 탭
		//데이터 원본 준비
		ArrayList<String> arGeneral = new ArrayList<String>();
		arGeneral.add("김유신");
		arGeneral.add("이순신");
		arGeneral.add("강감찬");
		arGeneral.add("을지문덕");
		
		//어댑터 준비
		ArrayAdapter<String> Adapter;
		Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arGeneral);
		
		//어댑터 연결
		ListView list = (ListView) findViewById(R.id.listView1);
		list.setAdapter(Adapter);
		
		Button btnWrite = (Button) findViewById(R.id.button_writeForm);
		btnWrite.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, WriteActivity.class);
				startActivity(intent);
			}
		});
		
		
		//2번재 탭
		 //Load File
        BufferedReader jsonReader = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.items)));
        StringBuilder jsonBuilder = new StringBuilder();
        try {
			for (String line = null; (line = jsonReader.readLine()) != null;) {
				jsonBuilder.append(line).append("\n");
			}
			
			 //Parse Json
	        JSONTokener tokener = new JSONTokener(jsonBuilder.toString());
	        JSONArray jsonArray = new JSONArray(tokener);
	 
	        //ArrayList<String> fields = new ArrayList<String>();
	        ArrayList<ItemObject> fields = new ArrayList<ItemObject>(); 
	       
	        
	        
	        for (int index = 0; index < jsonArray.length(); index++) {
	            //Set both values into the listview
	            JSONObject jsonObject = jsonArray.getJSONObject(index);

	            itemObject = new ItemObject();
	            itemObject.setTitle(jsonObject.getString("title"));
	            itemObject.setUserId(jsonObject.getString("userId"));
	            itemObject.setContent(jsonObject.getString("content"));
	            
	            fields.add(itemObject);
	            //fields.add(jsonObject.getString("title") + " - " + jsonObject.getString("userId") + " - " + jsonObject.getString("content"));
	        }
	 
	        ListView list2 = (ListView) findViewById(R.id.listView2);
//	        list2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fields));
	        
	        //ItemObject를 출력할 Adapter 클래스가 필요	        	        
	        //list2.setAdapter(new ArrayAdapter<ItemObject>(this, android.R.layout.simple_list_item_1, fields));
	        list2.setAdapter(new ItemAdapter(this, R.layout.listitem, fields));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //3번째 텝
        
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
