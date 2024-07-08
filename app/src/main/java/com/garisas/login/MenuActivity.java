package com.garisas.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button tampilMahasiswaButton, tampilForexButton, tampilCuacaButton;
    private Intent tampilMahasiswaIntent, tampilForexIntent, tampilCuacaIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initTampilMahasiswaButton();
        initTampilForexButton();
        initTampilCuacaButton();
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

    private void initTampilCuacaButton() {
        tampilCuacaButton = findViewById(R.id.tampilCuacaButton);
        tampilCuacaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tampilCuacaIntent = new Intent(getApplicationContext(), CuacaMainActivity.class);
                startActivity(tampilCuacaIntent);
            }
        });
    }
}