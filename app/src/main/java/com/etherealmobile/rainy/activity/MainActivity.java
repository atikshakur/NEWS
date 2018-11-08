package com.etherealmobile.rainy.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.etherealmobile.rainy.adapter.NewsAdapter;
import com.etherealmobile.rainy.model.NewsModel;
import com.etherealmobile.rainy.utils.AsyncNews;
import com.etherealmobile.rainy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

     private String API_KEY = "0ae4474c766f4adfa6e8e9febbbd5ad9";
     private String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=";
     private String google_news_url = "https://newsapi.org/v2/top-headlines?sources=google-news&apiKey=0ae4474c766f4adfa6e8e9febbbd5ad9";
     NewsAdapter newsAdapter;
     RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncNews(this).execute(google_news_url);

        ArrayList<NewsModel> news = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        
        fetchData(news);
    }

    private void fetchData(final ArrayList<NewsModel> news) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        final StringRequest stringRequest = new StringRequest(Request.Method.GET,
                google_news_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("articles");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                NewsModel newsModel = new NewsModel(jsonObject1.getString("title"),
                                        jsonObject1.getString("urlToImage"),
                                        jsonObject1.getString("content"));
                                news.add(newsModel);
                            }
                             newsAdapter = new NewsAdapter(news, MainActivity.this);
                             recyclerView.setAdapter(newsAdapter);
                             newsAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
