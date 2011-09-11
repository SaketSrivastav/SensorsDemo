package com.sensorsdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainListActivity extends ListActivity {

	SensorManager mSensorManager;
	List<Sensor> mSensorList;
	ArrayAdapter<String> adapter;

	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
		ArrayList<String> mSensorNames = new ArrayList<String>();
		
		for (Sensor sensor : mSensorList) {
			mSensorNames.add(sensor.getName());
		}
		
		adapter = new ArrayAdapter<String>(MainListActivity.this,
				android.R.layout.simple_list_item_1, mSensorNames);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(MainListActivity.this, MainActivity.class);
		startActivity(intent);
	}
}
