package com.example.eventbrite;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSONRead {
    public void read(String param_url, IResponse response) {
        try {
            URL url = new URL(param_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            StringBuilder result = new StringBuilder();
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            List<Event> lista = Parsare(result.toString());

            response.onSuccess(lista);

            Log.v("rezultatNet", lista.toString());

            bufferedReader.close();
            streamReader.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            response.onError("Nasol");
        }

    }

    private List<Event> Parsare(String jsonString) {
        List<Event> listaJSON = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray array = object.getJSONArray("eventuri");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String title = jsonObject.getString("titlu");
                String data = jsonObject.getString("data");
                String autor = jsonObject.getString("autor");

                Event event = new Event(title, data, autor);
                listaJSON.add(event);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaJSON;
    }
}
