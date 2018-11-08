package com.etherealmobile.rainy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.etherealmobile.rainy.R;
import com.etherealmobile.rainy.activity.ExtendedNewsActivity;
import com.etherealmobile.rainy.model.NewsModel;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<NewsModel> news = new ArrayList<>();
    private Context context;

    public NewsAdapter(ArrayList<NewsModel> newsModels, Context context){
        news = newsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from( context ).inflate( R.layout.items, parent, false );

        return new NewsViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.NewsViewHolder holder, final int position) {

        holder.title.setText(news.get(position).getTitle());
        Glide.with(context).load(news.get(position).getUrlToImage()).into(holder.imageView);
        holder.content.setText(news.get(position).getContent());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExtendedNewsActivity.class);
                intent.putExtra("title", news.get(position).getTitle());
                intent.putExtra("image", news.get(position).getUrlToImage());
                intent.putExtra("content", news.get(position).getContent());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView imageView;
        TextView content;
        CardView cardView;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.newsTitle);
            imageView = itemView.findViewById(R.id.newsImage);
            content = itemView.findViewById(R.id.content);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
