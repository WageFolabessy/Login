package com.garisas.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button tampilMahasiswaButton, tampilForexButton;
    private Intent tampilMahasiswaIntent, tampilForexIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initTampilMahasiswaButton();
        initTampilForexButton();
    }

    private void initTampilForexButton() {
        tampilForexButton = findViewById(R.id.tampilForexButton);
        tampilForexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tampilForexIntent = new Intent(getApplicationContext(), ForexMainActivity.class);
                startActivity(tampilForexIntent);
            }
        });
    }

    private void initTampilMahasiswaButton() {
        tampilMahasiswaButton = findViewById(R.id.tampilMahasiswaButton);
        tampilMahasiswaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tampilMahasiswaIntent = new Intent(getApplicationContext(), ShowMahasiswaActivity.class);
                startActivity(tampilMahasiswaIntent);
            }
        });
    }
}