package com.example.moviesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;

    public SearchAdapter(Context context){
        this.context=context;
    }
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_layout,parent,false);

        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
