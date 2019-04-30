package com.example.codechallenge043019.View;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codechallenge043019.Model.Movie;
import com.example.codechallenge043019.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomViewAdapter extends RecyclerView.Adapter<CustomViewAdapter.CustomViewHolder> {
    private String TAG = this.getClass().getSimpleName();
    private Context context;
    private List<List<Movie>> movieRowList;

    public CustomViewAdapter(Context context, List<List<Movie>> movieRowList) {
        this.context = context;
        this.movieRowList = movieRowList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = ((MainActivity)context).getLayoutInflater();
        return new CustomViewHolder(layoutInflater.inflate(R.layout.movie_row,null));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int i) {
        Picasso picasso = Picasso.get();
        List<Movie> currentList = movieRowList.get(i);

        Log.d(TAG, movieRowList.size() + " " + i + " ");
        for(Movie m: currentList){
            Log.d(TAG, m.getTitle());
        }
        Movie currentMovie;

        for (int index = 0; index < currentList.size(); index += 1) {
            switch (index) {
                case 0:
                    currentMovie = currentList.get(0);
                    holder.tv0.setText(currentMovie.getTitle() + " " + currentMovie.getYear());
                    picasso.load(currentMovie.getPoster()).into(holder.iv0);
                    break;
                case 1:
                    currentMovie = currentList.get(1);
                    holder.tv1.setText(currentMovie.getTitle() + " " + currentMovie.getYear());
                    picasso.load(currentMovie.getPoster()).into(holder.iv1);
                    break;
                case 2:
                    currentMovie = currentList.get(2);
                    holder.tv2.setText(currentMovie.getTitle() + " " + currentMovie.getYear());
                    picasso.load(currentMovie.getPoster()).into(holder.iv2);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return movieRowList.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder{
        ImageView iv0, iv1, iv2;
        TextView tv0, tv1, tv2;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            iv0 = itemView.findViewById(R.id.iv_0);
            iv1 = itemView.findViewById(R.id.iv_1);
            iv2 = itemView.findViewById(R.id.iv_2);
            tv0 = itemView.findViewById(R.id.tv_0);
            tv1 = itemView.findViewById(R.id.tv_1);
            tv2 = itemView.findViewById(R.id.tv_2);
        }
    }
}
