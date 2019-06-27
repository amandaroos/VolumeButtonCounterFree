package com.amandafarrell.www.volumebuttoncounterfree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int number = 1;
    TextView numberTextView;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberTextView = findViewById(R.id.number_textview);

        //Set variable values from Shared Preferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
            number += 1;
            numberTextView.setText(String.valueOf(number));
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            number -= 1;
            numberTextView.setText(String.valueOf(number));
        }
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
        }
        return super.onOptionsItemSelected(item);
    }
}
