package com.example.eventbrite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventsActivity extends AppCompatActivity {
    private ListView lv;
    private EventAdapter eventAdapter;
    private JSONRead jsonRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        lv = findViewById(R.id.listview);
        eventAdapter = new EventAdapter(getList());

        lv.setAdapter(eventAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = (Event) eventAdapter.getItem(position);
                Toast.makeText(EventsActivity.this,event.toString(),Toast.LENGTH_LONG).show();
            }
        });

        jsonRead = new JSONRead();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                jsonRead.read("https://jsonkeeper.com/b/MKG0", new IResponse() {
                    @Override
                    public void onSuccess(List<Event> lista) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                eventAdapter.updateList(lista);

                            }
                        });
                    }

                    @Override
                    public void onError(String errorMessage) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(EventsActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });
        thread.start();

    }

    private List<Event> getList() {
        ArrayList<Event> lista = new ArrayList<>();

        Event event1 = new Event("Titlu1", "20.02.2021", "Andrei");
        Event event2 = new Event("Titlu2", "30.05.2021", "Marian");
        Event event3 = new Event("Titlu3", "15.10.2021", "Cornel");

        lista.add(event1);
        lista.add(event2);
        lista.add(event3);

        return lista;
    }
}