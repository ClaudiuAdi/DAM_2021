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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        writeToDatabase();
        readFromDatabase();
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

    public void writeToDatabase(){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Nu stiu ce sa pun aici dar am facut o baza de date cu firebase");
    }

    public void readFromDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Db", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Db", "Failed to read value.", error.toException());
            }
        });
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