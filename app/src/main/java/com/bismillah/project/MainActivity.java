package com.bismillah.project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private EditText usernameInput;
    String userValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameInput = findViewById(R.id.userInput);

        getSupportActionBar().setTitle("GitHub");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void handleRequest(View view) {
        userValue = usernameInput.getText().toString();
        getData();
    }
    public void getData( ) {
    }
}
