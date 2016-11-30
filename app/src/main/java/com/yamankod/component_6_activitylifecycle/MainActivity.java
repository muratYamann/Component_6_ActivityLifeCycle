package com.yamankod.component_6_activitylifecycle;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button button;
    private TextView textView;
    private LinearLayout layout;
    private EditText editText;
    public static final String myid = "myid001";
    public static final int actMode = Activity.MODE_PRIVATE;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (LinearLayout) findViewById(R.id.myScreen);
        String msg = "Instructions: \n "
                + "0. New instance (onCreate, onStart, onResume) \n "
                + "1. Back Arrow (onPause, onStop, onDestroy) \n "
                + "2. Finish (onPause, onStop, onDestroy) \n "
                + "3. Home (onPause, onStop) \n ";
        textView = (TextView) findViewById(R.id.textView1);
        textView.setText(msg);
        editText = (EditText) findViewById(R.id.editText1);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                chngBacgroundColor(s.toString());
            }
        });
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Log.i("!!!", "CREATE!");
        Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_LONG)
                .show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("!!!", "DESTROY!");
        Toast.makeText(getApplicationContext(), "onDestroy", Toast.LENGTH_LONG)
                .show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        saveDataFromCurrentState();
        Log.i("!!!", "PAUSE!");
        Toast.makeText(getApplicationContext(), "onPause", Toast.LENGTH_LONG)
                .show();

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("!!!", "RESTART!");
        Toast.makeText(getApplicationContext(), "onRestart", Toast.LENGTH_LONG)
                .show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("!!!", "RESUME!");
        Toast.makeText(getApplicationContext(), "onResume", Toast.LENGTH_LONG)
                .show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        updateFromSavedState();
        Log.i("!!!", "START!");
        Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_LONG)
                .show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("!!!", "STOP!");
        Toast.makeText(getApplicationContext(), "onStop", Toast.LENGTH_LONG)
                .show();
    }
    protected void saveDataFromCurrentState() {
        SharedPreferences preferences = getSharedPreferences(myid, actMode);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("mycolor", editText.getText().toString());
        editor.commit();
    }
    protected void updateFromSavedState() {
        SharedPreferences myPrefs = getSharedPreferences(myid, actMode);
        if ((myPrefs != null) && (myPrefs.contains("mycolor"))) {
            String color = myPrefs.getString("mycolor", "");
            editText.setText(color);
            chngBacgroundColor(color);
        }
    }
    protected void clearMyPreferences() {
        SharedPreferences myPrefs = getSharedPreferences(myid, actMode);
        SharedPreferences.Editor myEditor = myPrefs.edit();
        myEditor.clear();
        myEditor.commit();
    }
    private void chngBacgroundColor(String color) {
        if (color.contains("red"))
            layout.setBackgroundColor(Color.RED);
        else if (color.contains("green"))
            layout.setBackgroundColor(Color.GREEN);
        else if (color.contains("blue"))
            layout.setBackgroundColor(Color.BLUE);
        else {
            clearMyPreferences();
            layout.setBackgroundColor(Color.CYAN);
        }
    }
}