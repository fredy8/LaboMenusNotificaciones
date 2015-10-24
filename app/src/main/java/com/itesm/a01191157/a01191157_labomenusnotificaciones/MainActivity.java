package com.itesm.a01191157.a01191157_labomenusnotificaciones;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CountryAdapter adapter;
    private List<String> countriesList = new ArrayList<>();

    private static final int MY_NOTIFICATION_ID = 1;
    private static final String TICKER_TEXT = "Notification Message!";
    private static final String CONTENT_TITLE = "Notification";
    private static final String CONTENT_TEXT = "You've been notified.!";

    private int notificationCount;

    private Intent notificationIntent;
    private PendingIntent pendingIntent;
    NotificationManager notificationManager;

    private Uri soundURI = Uri.parse("android.resource://com.itesm.a01191157.a01191157_labomenusnotificaciones/" + R.raw.alarm_rooster);

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        countriesList.add("Alemania");
        countriesList.add("Inglaterra");
        countriesList.add("México");
        countriesList.add("USA");
        countriesList.add("Rusia");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listView);
        adapter = new CountryAdapter(this.getApplicationContext(), R.layout.renglon_layout, countriesList);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        notificationIntent = new Intent(getApplicationContext(), NotificationActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, pendingIntent.FLAG_ONE_SHOT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void nuevaNotificacion() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext());
        notificationBuilder.setContentTitle(CONTENT_TITLE);
        notificationBuilder.setTicker(TICKER_TEXT);
        notificationBuilder.setSmallIcon(android.R.drawable.stat_sys_warning);
        notificationBuilder.setContentText(CONTENT_TEXT + " (" + ++notificationCount + ")");
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setSound(soundURI);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFICATION_ID, notificationBuilder.build());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = item.getItemId();

        if (id == R.id.save) {
            Toast.makeText(getApplicationContext(), "SAVE " + countriesList.get(info.position), Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.edit) {
            Toast.makeText(getApplicationContext(), "EDIT " + countriesList.get(info.position), Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.delete) {
            Toast.makeText(getApplicationContext(), "DELETE " + countriesList.get(info.position), Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), R.string.action_settings, Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.new_notification) {
            nuevaNotificacion();
            Toast.makeText(getApplicationContext(), R.string.new_notification, Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.cancel_notification) {
            Toast.makeText(getApplicationContext(), R.string.cancel_notification, Toast.LENGTH_LONG).show();
            notificationManager.cancel(MY_NOTIFICATION_ID);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
