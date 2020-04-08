package com.example.moviesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.moviesapp.R;
import com.example.moviesapp.model.MovieInfoModel;
import com.example.moviesapp.model.MovieListModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {

    private Context context;
    ArrayList<MovieListModel> movieListModelArrayList;

    public MovieListAdapter(Context context,ArrayList<MovieListModel> arrayList){
        this.context=context;
        this.movieListModelArrayList=arrayList;
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_view,parent,false);
        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        final MovieListModel movieListModel=movieListModelArrayList.get(position);
        ArrayList<MovieInfoModel> movieInfoModels=movieListModel.getResults();

        MovieInfoAdapter movieInfoAdapter=new MovieInfoAdapter(context,movieInfoModels);

         holder.movieInfoRecyclerView.setHasFixedSize(true);
        holder.movieInfoRecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        holder.movieInfoRecyclerView.setAdapter(movieInfoAdapter);

    }

    @Override
    public int getItemCount() {
        return movieListModelArrayList.size();
    }

    class MovieListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.movie_category_name)
        TextView movieCategoryName;

        @BindView(R.id.movie_info_recyclerview)
        RecyclerView movieInfoRecyclerView;

        public MovieListViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
//            movieCategoryName=itemView.findViewById(R.id.movie_category_name);
//            movieInfoRecyclerView=itemView.findViewById(R.id.movie_info_recyclerview);
//            ButterKnife.bind(movieCategoryName,itemView);
//            ButterKnife.bind(movieInfoRecyclerView,itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}