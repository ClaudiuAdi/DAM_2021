package com.example.eventbrite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Button btn;
    private Button btn_event;
    private final int MainActivityRequestCode = 100;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intent, MainActivityRequestCode);
            }
        });

        btn_event = findViewById(R.id.button_events);
        btn_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Events", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, EventsActivity.class);
                startActivity(intent);
            }
        });

        textView = findViewById(R.id.et_registered);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivityRequestCode) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Bundle bundle = data.getBundleExtra("persoanaBundle");
                    Persoana persoana = bundle.getParcelable("persoanaParsata");
                    textView.setText(persoana.toString());
                }
            }
        }
    }

    public class MyRunnable implements Runnable {
        private PersoanaDao persoanaDao;

        @Override
        public void run() {
            Persoana persoana1 = new Persoana("Maria", "1234567890", "asd@asd.com", "12.03.2000");
            Persoana persoana2 = new Persoana("Carina", "1234567890", "dfg@adfg.com", "22.01.1999");
            Persoana persoana3 = new Persoana("Marcel", "1234567890", "rtasdd@rty.com", "18.12.2001");

            persoanaDao = DataBaseAccess.getInstance(MainActivity.this).getDatabase().persoanaDao();

            persoanaDao.insertAll(persoana1, persoana2, persoana3);

            persoanaDao.delete(persoana1);
            List<Persoana> lista3 = persoanaDao.getAll();

            Log.v("operatii", lista3.toString());
        }
    }
}