package com.garisas.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private Button logginButton;
    private EditText idEditText, passwordEditText;
    private Intent menuIntent;
    private String id, password, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEditText = findViewById(R.id.idEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        logginButton = findViewById(R.id.loginButton);

        logginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = idEditText.getText().toString();
                password = passwordEditText.getText().toString();
                url = "https://stmikpontianak.net/011100862/login.php?id=" + id + "&password=" + password;

                AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
                asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String hasil = new String(responseBody);

                        if(!hasil.equals("[{\"idCount\":\"1\"}]")){
                            Toast.makeText(getApplicationContext(), "ID dan Password anda tidak cocok.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(getApplicationContext(), "Selamat datang, " + id, Toast.LENGTH_SHORT).show();

                        menuIntent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(menuIntent);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}