package com.example.lab2;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ProgressBar batteryProgressBar;
    TextView level, health, plugged, present, scale, status, technology, temperature, voltage;

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int levelVal = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int healthVal = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
            int pluggedVal = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean presentVal = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);
            int scaleVal = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int statusVal = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            String techVal = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
            int tempVal = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
            int voltVal = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);

            level.setText("EXTRA_LEVEL: " + levelVal);


            health.setText("EXTRA_HEALTH: " + getHealthString(healthVal));
            plugged.setText("EXTRA_PLUGGED: " + getPluggedString(pluggedVal));
            present.setText("EXTRA_PRESENT: " + presentVal);
            scale.setText("EXTRA_SCALE: " + scaleVal);
            status.setText("EXTRA_STATUS: " + getStatusString(statusVal));
            technology.setText("EXTRA_TECHNOLOGY: " + techVal);
            temperature.setText("EXTRA_TEMPERATURE: " + (tempVal / 10.0) + " °C");
            voltage.setText("EXTRA_VOLTAGE: " + voltVal + " mV");
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batteryProgressBar = findViewById(R.id.batteryProgressBar);
        level = findViewById(R.id.level);
        health = findViewById(R.id.health);
        plugged = findViewById(R.id.plugged);
        present = findViewById(R.id.present);
        scale = findViewById(R.id.scale);
        status = findViewById(R.id.status);
        technology = findViewById(R.id.technology);
        temperature = findViewById(R.id.temperature);
        voltage = findViewById(R.id.voltage);

        registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBatInfoReceiver);
    }

    private String getHealthString(int health) {
        switch (health) {
            case BatteryManager.BATTERY_HEALTH_GOOD: return "Dobry";
            case BatteryManager.BATTERY_HEALTH_DEAD: return "Zużyty";
            case BatteryManager.BATTERY_HEALTH_OVERHEAT: return "Przegrzany";
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE: return "Przepięcie";
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE: return "Błąd";
            default: return "Nieznany";
        }
    }

    private String getPluggedString(int plugged) {
        switch (plugged) {
            case BatteryManager.BATTERY_PLUGGED_AC: return "AC";
            case BatteryManager.BATTERY_PLUGGED_USB: return "USB";
            case BatteryManager.BATTERY_PLUGGED_WIRELESS: return "Bezprzewodowe";
            default: return "Niepodłączony";
        }
    }

    private String getStatusString(int status) {
        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING: return "Ładowanie";
            case BatteryManager.BATTERY_STATUS_DISCHARGING: return "Rozładowywanie";
            case BatteryManager.BATTERY_STATUS_FULL: return "Pełna";
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING: return "Nie ładuje";
            default: return "Nieznany";
        }
    }


}

//import androidx.appcompat.app.AppCompatActivity;
//
//public class MainActivity extends AppCompatActivity {
//
//    private TextView batteryLevelText, batteryStatusText, batteryHealthText;
//
//    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
//            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
//            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
//
//            batteryLevelText.setText("Poziom baterii: " + level + "%");
//
//            String statusString = "Nieznany";
//            switch (status) {
//                case BatteryManager.BATTERY_STATUS_CHARGING:
//                    statusString = "Ładowanie";
//                    break;
//                case BatteryManager.BATTERY_STATUS_DISCHARGING:
//                    statusString = "Rozładowywanie";
//                    break;
//                case BatteryManager.BATTERY_STATUS_FULL:
//                    statusString = "Pełna";
//                    break;
//                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
//                    statusString = "Nie ładuje";
//                    break;
//            }
//            batteryStatusText.setText("Status: " + statusString);
//
//            String healthString = "Nieznany";
//            switch (health) {
//                case BatteryManager.BATTERY_HEALTH_GOOD:
//                    healthString = "Dobry";
//                    break;
//                case BatteryManager.BATTERY_HEALTH_OVERHEAT:
//                    healthString = "Przegrzany";
//                    break;
//                case BatteryManager.BATTERY_HEALTH_DEAD:
//                    healthString = "Zużyty";
//                    break;
//                case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
//                    healthString = "Przepięcie";
//                    break;
//                case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
//                    healthString = "Błąd";
//                    break;
//            }
//            batteryHealthText.setText("Stan baterii: " + healthString);
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        batteryLevelText = findViewById(R.id.batteryLevel);
//        batteryStatusText = findViewById(R.id.batteryStatus);
//        batteryHealthText = findViewById(R.id.batteryHealth);
//
//        registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(mBatInfoReceiver);
//    }
//}
