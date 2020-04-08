package com.example.moviesapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.adapters.SearchAdapter;

public class SearchFragment extends Fragment {

    private SearchView search;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_layout, container, false);
        search = (SearchView) view.findViewById(R.id.search);
        search.setQueryHint("SearchView Fragment");

        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.search_view_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        SearchAdapter searchAdapter=new SearchAdapter(this.getActivity());
        recyclerView.setAdapter(searchAdapter);

        return view;

    }

}
