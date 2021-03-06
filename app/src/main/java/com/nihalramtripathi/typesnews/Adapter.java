package com.nihalramtripathi.typesnews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.interstitial.InterstitialAd;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context ;
    ArrayList<ModelClass> modelClassArrayList ;

    private InterstitialAd mInterstitialAd;

    public Adapter(Context context, ArrayList<ModelClass> modelClassArrayList) {
        this.context = context;
        this.modelClassArrayList = modelClassArrayList;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item_layout,null,false);
       return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {


        holder.cardView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   Intent i = new Intent(context,webViewActivity.class);
                                                   i.putExtra("url",modelClassArrayList.get(position).getUrl());
                                                    context.startActivity(i);
            }
        });

        holder.mtime.setText("Published At:-" + modelClassArrayList.get(position).getPublishedAt());
        holder.mauthor.setText(modelClassArrayList.get(position).getAuthor());
        holder.mainHeading.setText(modelClassArrayList.get(position).getTitle());
        holder.mcontent.setText(modelClassArrayList.get(position).getDescription());
        Glide.with(context).load(modelClassArrayList.get(position).getUrlToImage()).into(holder.imageView);




    }

    @Override
    public int getItemCount() {
        return modelClassArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mainHeading ,mauthor,mcontent ,mtime ;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mainHeading =itemView.findViewById(R.id.mainHeading);
            mauthor =itemView.findViewById(R.id.author);
            mcontent =itemView.findViewById(R.id.content);
            mtime =itemView.findViewById(R.id.time);
            cardView =itemView.findViewById(R.id.cardView);
            imageView =itemView.findViewById(R.id.imageView);

        }
    }
}