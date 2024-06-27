package com.example.exercisetwenty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;


public class BatteryLevelReceiver extends BroadcastReceiver {
    private static final String TAG = "BatteryLevelReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1); // Lấy phần trăm pin còn lại
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1); // Lấy giá trị tối đa của mức pin (cần thiết để tính toán phần trăm chính xác).
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN); // Lấy trạng thái sạc pin
            int batteryPer = level*100/scale;

            Log.d(TAG, "Battery Level Receiver state changed: " + status);
            Log.d(TAG, "Battery level changing: " + batteryPer + "%");
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    switch (status) {
                        case BatteryManager.BATTERY_STATUS_FULL:
                            Toast.makeText(context, "Battery is full", Toast.LENGTH_LONG).show();
                            break;
                        case BatteryManager.BATTERY_STATUS_CHARGING:
                            Toast.makeText(context, "Battery is Charging with " + batteryPer + "%", Toast.LENGTH_LONG).show();
                            if(batteryPer == 100){
                                Toast.makeText(context, "Battery had full", Toast.LENGTH_LONG).show();
                            }else if(batteryPer == 20){
                                Toast.makeText(context, "Battery is low", Toast.LENGTH_LONG).show();
                            }
                            break;
                        case BatteryManager.BATTERY_STATUS_DISCHARGING:
                            Toast.makeText(context, "Battery is Discharging", Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(context, "Battery state unknown", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            });
        }
    }
}