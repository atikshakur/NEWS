package com.etherealmobile.rainy.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncNews extends AsyncTask<String, Void, String> {

    private String title;
    public static String titles;
    private Context context;
    public AsyncNews(Context context) {
        this.context = context;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected String doInBackground(String... urls) {

        String result = "";
        URL url;
        HttpURLConnection httpURLConnection = null;

        try {
            url = new URL(urls[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            String response = streamToString(httpURLConnection.getInputStream());
            titles = parseResult(response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    String streamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String data;
        String result = "";

        while((data = bufferedReader.readLine()) != null) {
            result += data;
        }

        if (null != inputStream){
            inputStream.close();
        }

        return result;
    }

    private String parseResult(String result){
        JSONObject response = null;
        try {
            response = new JSONObject(result);
            JSONArray articles = response.optJSONArray("articles");
            for (int i = 0; i < articles.length(); i++) {
                JSONObject article = articles.optJSONObject(i);
                title = article.optString("title");
                return title;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return title;
    }
}
