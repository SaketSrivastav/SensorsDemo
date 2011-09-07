package com.sensorsdemo;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
    
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private TextView mSensorValuesLeft;
	private TextView mSensorValuesRight;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mSensorValuesLeft = (TextView) findViewById(R.id.txtSensorValueLeft);
        mSensorValuesRight = (TextView) findViewById(R.id.txtSensorValueRight);
        System.out.println(mSensorValuesRight.getText());
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
		
		Log.d("SENSORCHANGED", Float.toString(event.values[0]));
		if(event.values[0] > 0f){
			mSensorValuesLeft.setText("LEFT: "+Float.toString(event.values[0]));
		}else if(event.values[0] < 0f){
			mSensorValuesRight.setText("RIGHT: "+Float.toString(event.values[0]));
		}
	}
}