package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.net.URI;

public class MainActivity extends ActionBarActivity {

    final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(LOG_TAG, "This is onCreate");
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v( LOG_TAG, "This is onStart ");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.v(LOG_TAG, "This is onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v(LOG_TAG, "This is onPause");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v(LOG_TAG, "This is onDestroy ");
    }

    protected void onStop(){
        super.onStop();
        Log.v(LOG_TAG, "This is onStop");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if( id == R.id.action_settings ) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if( id == R.id.actio_open_location){
            openPreferredLocationInMap();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap(){

        String parsable = "geo:0,0?";

        final String QUERY_PARAM = "q";
        final String ZOOM_PARAM = "z";

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String location = prefs.getString(getString(R.string.pref_location_key),getString(R.string.pref_location_default));
        String zoom = "10";

        Uri geoLocation = Uri.parse( parsable).buildUpon()
                .appendQueryParameter( ZOOM_PARAM, zoom)
                .appendQueryParameter( QUERY_PARAM, location)
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if(intent.resolveActivity(getPackageManager()) != null ){
            startActivity(intent);
        }
    }
}
