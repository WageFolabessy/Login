package com.garisas.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import cz.msebera.android.httpclient.Header;

public class CuacaMainActivity extends AppCompatActivity {
    private RecyclerView recyclerView1;
    private CuacaRootModel _rootModel;
    private SwipeRefreshLayout swipeRefreshLayout1;
    private TextView cityTextView, sunriseTextView, sunsetTextView, totalRecordTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuaca_main);

        recyclerView1 = findViewById(R.id.recylerView1);
        swipeRefreshLayout1 = findViewById(R.id.swipeRefreshLayout1);
        cityTextView = findViewById(R.id.cityTextView);
        sunriseTextView = findViewById(R.id.sunriseTextView);
        sunsetTextView = findViewById(R.id.sunsetTextView);
        totalRecordTextView = findViewById(R.id.totalRecordTextView);

        initRecyclerView1();
        initSwipeRefreshLayout();
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRecyclerView1();
                swipeRefreshLayout1.setRefreshing(false);
            }
        });
    }

    private void initRecyclerView1() {
        String url = "https://api.openweathermap.org/data/2.5/forecast?id=1630789&appid=a102c064f03c4cf828e3dbd554530802";
        AsyncHttpClient ahc = new AsyncHttpClient();

        ahc.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                _rootModel = gson.fromJson(new String(responseBody), CuacaRootModel.class);
                CuacaRootModel rm = gson.fromJson(new String(responseBody), CuacaRootModel.class);
                RecyclerView.LayoutManager lm = new LinearLayoutManager(CuacaMainActivity.this);
                CuacaAdapter ca = new CuacaAdapter(CuacaMainActivity.this, _rootModel );
                recyclerView1.setLayoutManager(lm);
                recyclerView1.setAdapter(ca);

                CityModel city = rm.getCityModel();
                cityTextView.setText("Kota: " + city.getName());
                sunriseTextView.setText("Sunrise: " + formatWaktuWib(city.getSunrise()));
                sunsetTextView.setText("Sunset: " + formatWaktuWib(city.getSunset()));
                totalRecordTextView.setText("Total Record: " + ca.getItemCount());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private String formatWaktuWib(long waktuUtc) {
        Date date = new Date(waktuUtc * 1000); // convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta")); // set timezone to WIB
        return sdf.format(date);
    }
}