package com.amandafarrell.www.volumebuttoncounterfree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int number;
    public int starting_number;
    TextView numberTextView;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberTextView = findViewById(R.id.number_textview);

        //Set variable values from Shared Preferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        getNumber();
        getStartingNumber();

        numberTextView.setText(String.valueOf(number));
    }

    public void getNumber(){
        number = Integer.parseInt(sharedPreferences.getString(
                getString(R.string.settings_number_key),
                getString(R.string.settings_number_default)));
    }

    public void getStartingNumber(){
        starting_number = Integer.parseInt(sharedPreferences.getString(
                getString(R.string.settings_starting_number_key),
                getString(R.string.settings_starting_number_default)));
    }

    public void reset(View view) {
        getStartingNumber();
        numberTextView.setText(String.valueOf(starting_number));
        number = starting_number;

        //Save starting number as number in SharedPreferences
        sharedPreferences.edit()
                .putString(getString(R.string.settings_number_key),
                        String.valueOf(number))
                .apply();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
            number += 1;
            numberTextView.setText(String.valueOf(number));
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            number -= 1;
            numberTextView.setText(String.valueOf(number));
        }

        //Save number to SharedPreferences
        sharedPreferences.edit()
                .putString(getString(R.string.settings_number_key),
                        String.valueOf(number))
                .apply();

        //return true so that it doesn't trigger normal volume button functionality
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_donate:
                Intent intent = new Intent(MainActivity.this, DonateActivity.class);
                startActivity(intent);
            case R.id.action_edit_starting_number:
                Intent intent2 = new Intent(MainActivity.this, EditStartingNumberActivity.class);
                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        getNumber();
        numberTextView.setText(String.valueOf(number));
        super.onResume();
    }
}
