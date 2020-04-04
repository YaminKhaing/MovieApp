package com.example.sampleapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sampleapp.R;
import com.example.sampleapp.common.BaseAdapter;
import com.example.sampleapp.model.MovieInfoModel;
import com.example.sampleapp.model.MovieListModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.sampleapp.util.AppConstant.BASE_IMG_URL;

public class MovieInfoAdapter extends BaseAdapter {

    private Context context;
    ArrayList<MovieInfoModel> movieInfoModelArrayList;

    public MovieInfoAdapter(Context context, ArrayList<MovieInfoModel> singleItem) {
        this.context=context;
        this.movieInfoModelArrayList=singleItem;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_view,parent,false);

        return new MovieInfoViewHolder(view);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {

//        ((MovieAdapter.ViewHolder)holder).bindView((MovieInfoModel)getItemsList().get(position),position);
        final MovieInfoModel movieInfoModel=movieInfoModelArrayList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, movieInfoModel.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindCustomHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return movieInfoModelArrayList.size();
    }

    class MovieInfoViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_movie_poster)
        ImageView ivMoviePoster;

//    private Context context;

        public MovieInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this,itemView);
        }

        public void bindView(MovieInfoModel model, int position) {

            Glide.with(context)
                .load(BASE_IMG_URL+model.getPoster_path())
                .into(ivMoviePoster);

        // mtMovieTitle.setMyanmarText(model.getTitle());
        //this.getBackgroundImage().reuse();
        }
    }
}
