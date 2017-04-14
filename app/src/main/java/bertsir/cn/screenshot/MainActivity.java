package bertsir.cn.screenshot;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity implements SensorEventListener {

    private ImageView iv;
    private SensorManager sensorManager;
    private Sensor defaultSensor;
    private ShotUtils shotUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        defaultSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        shotUtils = new ShotUtils(getApplicationContext());
        shotUtils.init(MainActivity.this);

    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, defaultSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(Math.abs(event.values[1]) >3){
                shotUtils.startScreenShot(new ShotUtils.ShotListener() {
                    @Override
                    public void OnSuccess(final Bitmap bitmap) {
                                iv.setImageBitmap(bitmap);
                    }
                });
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ShotUtils.REQUEST_MEDIA_PROJECTION:
                shotUtils.setData(data);
                break;
        }
    }

}
