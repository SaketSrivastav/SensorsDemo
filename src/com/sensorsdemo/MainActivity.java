package com.sensorsdemo;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private TextView mSensorValuesLeft;
	private TextView mSensorValuesRight;
	private TextView mSensorValuesUp;
	private TextView mSensorValuesDown;
	private TextView mSensorValuesZUp;
	private TextView mSensorValuesZDown;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mSensorValuesLeft = (TextView) findViewById(R.id.txtSensorValueLeft);
		mSensorValuesRight = (TextView) findViewById(R.id.txtSensorValueRight);
		mSensorValuesUp = (TextView) findViewById(R.id.txtSensorValueUp);
		mSensorValuesDown = (TextView) findViewById(R.id.txtSensorValueDown);
		mSensorValuesZUp = (TextView) findViewById(R.id.txtSensorZValueUp);
		mSensorValuesZDown = (TextView) findViewById(R.id.txtSensorZValueDown);

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> mSensorList = mSensorManager
				.getSensorList(Sensor.TYPE_ALL);

		for (Sensor sensor : mSensorList) {
			System.out.println(sensor.getName());
		}
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		/*
		 * You could have values defined for x, y and z axes and add these
		 * values to the original values while using it in your app
		 */
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
			return;
		// X-axis
		if (event.values[0] > 0) {
			mSensorValuesLeft.setText("X-axis (+)ve: "
					+ Integer.toString((int) event.values[0]));
		} else if (event.values[0] < 0) {
			mSensorValuesRight.setText("X-axis (-)ve:: "
					+ Integer.toString(((int) event.values[0]) * -1));
		}

		float y = event.values[1];
		if (y > 0) {
			mSensorValuesUp.setText("Y-axis (+)ve: "
					+ Integer.toString((int) y));
		} else {
			mSensorValuesDown.setText("Y-axis (-)ve: "
					+ Integer.toString((int) y * -1));
		}

		float z = event.values[2];
		if (z > 0) {
			mSensorValuesZUp.setText("Z-axis (+)ve: "
					+ Integer.toString((int) z));
		} else {
			mSensorValuesZDown.setText("Z-axis (-)ve: "
					+ Integer.toString((int) z * -1));
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_UI);
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mSensorManager.unregisterListener(this);
	}
}