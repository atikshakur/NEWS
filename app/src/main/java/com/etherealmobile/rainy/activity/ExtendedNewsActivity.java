package com.etherealmobile.rainy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.etherealmobile.rainy.R;

public class ExtendedNewsActivity extends AppCompatActivity {

    TextView title, content;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_news);

        title = findViewById(R.id.titleex);
        content = findViewById(R.id.detailcontent);
        imageView = findViewById(R.id.newsImageex);

        String t = getIntent().getStringExtra("title");
        String im = getIntent().getStringExtra("image");
        String con = getIntent().getStringExtra("content");

        title.setText(t);
        content.setText(con);
        Glide.with(this).load(im).into(imageView);

    }
}
