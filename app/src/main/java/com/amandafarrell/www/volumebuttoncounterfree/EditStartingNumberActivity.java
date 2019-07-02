package com.amandafarrell.www.volumebuttoncounterfree;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

public class EditStartingNumberActivity extends AppCompatActivity {

    public int starting_number;

    TextView startingNumberTextView;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_starting_number_activity);

        startingNumberTextView = findViewById(R.id.edit_text_starting_number);

        //Set variable values from Shared Preferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        starting_number = Integer.parseInt(sharedPreferences.getString(
                getString(R.string.settings_starting_number_key),
                getString(R.string.settings_starting_number_default)));


        startingNumberTextView.setText(String.valueOf(starting_number));

        //Save and finish EditStartingNumber activity when "done" is pressed on keyboard
        startingNumberTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    saveStartingNumber();
                    setStartingNumberAsNumber();
                    finish();
                    handled = true;
                }
                return handled;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_starting_number:
                saveStartingNumber();
                setStartingNumberAsNumber();
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveStartingNumber (){
        starting_number = Integer.valueOf(startingNumberTextView.getText().toString());

        //Save new starting number to SharedPreferences
        sharedPreferences.edit()
                .putString(getString(R.string.settings_starting_number_key),
                        String.valueOf(starting_number))
                .apply();
    }

    public void setStartingNumberAsNumber(){
        sharedPreferences.edit()
                .putString(getString(R.string.settings_number_key),
                        String.valueOf(starting_number))
                .apply();
    }


}
