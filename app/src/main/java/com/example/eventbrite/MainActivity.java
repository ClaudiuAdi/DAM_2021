package com.example.eventbrite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button btn;
    private Button btn_event;
    private final int MainActivityRequestCode = 100;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intent, MainActivityRequestCode);
            }
        });

        btn_event=findViewById(R.id.button_events);
        btn_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Events",Toast.LENGTH_LONG).show();

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
}