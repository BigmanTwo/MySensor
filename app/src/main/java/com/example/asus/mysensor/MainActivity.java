package com.example.asus.mysensor;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mTextView;
    private SensorManager sensorManager;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton=(Button)findViewById(R.id.design_but);
        mButton.setOnClickListener(this);
        mTextView=(TextView)findViewById(R.id.text_sensor);
        //获得传感器
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        //得到传感器的总数
        List<Sensor> sensorList=sensorManager.getSensorList(Sensor.TYPE_ALL);
        mTextView.setText("手机中传感器的数量"+sensorList.size());
        for (Sensor s:sensorList){
            String str=mTextView.getText().toString()+"传感器的名字"+s.getName()+"传感器的型号"+
                    s.getVersion()+"传感器的供应商"+s.getVendor();
            switch (s.getType()){
                //加速传感器
                case Sensor.TYPE_ACCELEROMETER:
                    mTextView.setText(str);
                    break;

            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,GroupActivity.class);
        startActivity(intent);
    }
}
