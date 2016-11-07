package com.cpsc362.listofstores;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import com.squareup.picasso.Picasso;
import android.widget.ImageView;
import android.media.Image;

import static com.cpsc362.listofstores.R.styleable.View;

public class StoreInfo extends AppCompatActivity {
    ArrayList<Event> arrayOfEvents = new ArrayList<>();
    int sid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_store_info);
        super.onCreate(savedInstanceState);
        ImageView image;
        image = (ImageView)findViewById(R.id.storePhoto);



        String storeName = getIntent().getExtras().getString("text");
        sid = getIntent().getExtras().getInt("storeID");


        String storeAddress1 = getIntent().getExtras().getString("add1");
        String storeAddress2 = getIntent().getExtras().getString("add2");
        String storeLogoUrl = getIntent().getExtras().getString("Logo");

        //test clean string
        storeLogoUrl = storeLogoUrl.replace("\\","");

        setTitle(storeName);


        ((TextView) findViewById(R.id.siAdd1)).setText(storeAddress1);
        ((TextView) findViewById(R.id.siAdd2)).setText(storeAddress2);


        Picasso.with(this).load(storeLogoUrl).placeholder(R.mipmap.ic_launcher) //optimal
                .error(R.mipmap.ic_launcher) // if error
                .into(image, new com.squareup.picasso.Callback() {


                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {

                            }
                        });


        addEvents.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }




    protected void showEvents() {
        ArrayList<Event> narrowEvents = new ArrayList<>();
        Log.i("SID", String.valueOf(sid));
        for (Event e : arrayOfEvents) {
            Log.i("EventLoop", String.valueOf(e.storeID));
            if (e.storeID == sid) {
                narrowEvents.add(e);
                Log.i("test", e.eventName);
            }
        }

        EventAdapter adapter = new EventAdapter(this, narrowEvents);

        final ListView myListView = (ListView) findViewById(R.id.siEventList);
        myListView.setAdapter(adapter);
    }

    protected void toDateAndSort(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",Locale.US);

        for(Event ev : arrayOfEvents){
            try {
                Date date = formatter.parse(ev.eventDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    AsyncTask<Void, Void, Void> addEvents = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... params) {
            URL url;
            InputStream phpReturn;
            BufferedReader buffreader;
            JSONArray obj = null;
            try {
                url = new URL("http://107.184.164.127:8040/eventQuery362.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                phpReturn = urlConnection.getInputStream();
                buffreader = new BufferedReader(new InputStreamReader(phpReturn));
                obj = new JSONArray(buffreader.readLine());
            } catch (MalformedURLException e) {
                setTitle("BadURL");
            } catch (IOException e) {
                setTitle("BadIO");
            } catch (JSONException e) {
                setTitle("BadJSON");
            } catch (NullPointerException e) {
                Log.e("Bad", "No pointer");
            }

            int length = obj.length();

            List<Integer> eventIDs = new ArrayList<>();
            List<Integer> storeIDs = new ArrayList<>();
            List<String> eventNames = new ArrayList<>();
            List<String> eventDates = new ArrayList<>();
            List<String> eventDescs = new ArrayList<>();

            try {
                for (int i = 0; i < length; i++) {
                    int temp = Integer.parseInt(obj.getJSONArray(i).getString(0));
                    eventIDs.add(temp);
                }
                for (int i = 0; i < length; i++) {
                    int temp = Integer.parseInt(obj.getJSONArray(i).getString(1));
                    storeIDs.add(temp);
                }
                for (int i = 0; i < length; i++) {
                    eventNames.add(obj.getJSONArray(i).getString(2));
                }
                for (int i = 0; i < length; i++) {
                    eventDates.add(obj.getJSONArray(i).getString(3));
                }
                for (int i = 0; i < length; i++) {
                    eventDescs.add(obj.getJSONArray(i).getString(4));
                }
            } catch (JSONException e) {
                Log.e("listapp", "exception", e);
            }

            for (int i = 0; i < length; i++) {
                arrayOfEvents.add(new Event(eventIDs.get(i), storeIDs.get(i), eventNames.get(i), eventDates.get(i), eventDescs.get(i)));
                Log.i("Event", String.valueOf(arrayOfEvents.get(i).storeID));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Log.i("PostExecute", "Starting");
            showEvents();
            Log.i("PostExecute", "Done");
        }
    };
}
