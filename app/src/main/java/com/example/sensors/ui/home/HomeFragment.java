package com.example.sensors.ui.home;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sensors.R;
import com.example.sensors.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    TextView textView ;
    TextView textView1 ;
    TextView textView2 ;
    private SensorManager sensorManager;
    private Sensor sensor;
    SensorEventListener sensorListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        textView = textView.findViewById(R.id.textView);
        textView1 = textView.findViewById(R.id.textView2);
        textView2 = textView.findViewById(R.id.textView3);

        sensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // Valors de l'acceleròmetre en m/s^2
                float xAcc = sensorEvent.values[0];
                float yAcc = sensorEvent.values[1];
                float zAcc = sensorEvent.values[2];
                textView.setText("" + xAcc);
                textView1.setText("" + yAcc);
                textView2.setText("" + zAcc);

                // Processament o visualització de dades...

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };



        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // registrem el Listener per capturar els events del sensor
        if( sensor!=null ) {
            sensorManager.registerListener(sensorListener,sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }


        return root;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}