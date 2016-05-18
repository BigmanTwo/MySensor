package com.example.asus.mysensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class GroupActivity extends AppCompatActivity implements SensorEventListener{
    private TextView mTextView;
    private ImageView mImageView;
    private SensorManager sensorManager;
   private  float num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        mImageView=(ImageView)findViewById(R.id.img);
        mTextView=(TextView)findViewById(R.id.text_view1);
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener((SensorEventListener) this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }
    //传感器变化
    @Override
    public void onSensorChanged(SensorEvent event) {

        float degree=0;
        if (event.sensor.getType()==Sensor.TYPE_ORIENTATION){
            degree= (int) event.values[0];
            RotateAnimation rotateAnimation=new RotateAnimation(num,-degree,1,0.5f,1,0.5f);

            rotateAnimation.setFillAfter(true);
            mImageView.setAnimation(rotateAnimation);
            num+=-degree;
        }
    }

    //传感器精度
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }




}
