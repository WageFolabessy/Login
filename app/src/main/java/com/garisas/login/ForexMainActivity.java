package com.garisas.login;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ForexMainActivity extends AppCompatActivity {

    private SwipeRefreshLayout _swipeRefreshLayout1;
    private RecyclerView _reRecyclerView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forex_main);

        _reRecyclerView1 = findViewById(R.id.recylerView1);
        initSwipeRefreshLayout();
        bindRecylerView();
    }

    private void bindRecylerView() {
        String url = "https://openexchangerates.org/api/latest.json?app_id=89da3526069e4d7fa60d9f896d8e180f";
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String jsonString = new String(responseBody);
                JSONObject root;

                try {
                    root = new JSONObject(jsonString);
                } catch (JSONException e) {
                    Toast.makeText(ForexMainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                JSONObject rates;
                long timestamp;

                try {
                    rates = root.getJSONObject("rates");
                } catch (JSONException e) {
                    Toast.makeText(ForexMainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }


                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ForexMainActivity.this);
                ForexAdapter adapter = new ForexAdapter(rates);
                _reRecyclerView1.setLayoutManager(layoutManager);
                _reRecyclerView1.setAdapter(adapter);
                _swipeRefreshLayout1.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(ForexMainActivity.this, new String(responseBody), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSwipeRefreshLayout() {
        _swipeRefreshLayout1 = findViewById(R.id.swipeRefreshLayout1);
        _swipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bindRecylerView();
            }
        });
    }

}