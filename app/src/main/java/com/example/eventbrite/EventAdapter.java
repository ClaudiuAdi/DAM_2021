package com.example.eventbrite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends BaseAdapter {
    private List<Event> listaEventuri = new ArrayList<>();

    public EventAdapter(List<Event> lista){
        this.listaEventuri = lista;
    }

    @Override
    public int getCount() {
        return this.listaEventuri.size();
    }

    @Override
    public Object getItem(int position) {
        return listaEventuri.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view1 = inflater.inflate(R.layout.event_menu, parent, false);
        TextView textTitle = view1.findViewById(R.id.id_title_event);
        TextView textData = view1.findViewById(R.id.id_data_event);
        TextView textAuthor = view1.findViewById(R.id.id_author_event);

        Event temp = listaEventuri.get(position);
        textTitle.setText(temp.getTitle());
        textData.setText(temp.getData());
        textAuthor.setText(temp.getAuthor());

        return view1;
    }
}
