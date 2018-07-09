package com.example.tan.mtapp;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.API.SearchCallbackListener;
import com.example.tan.mtapp.Adapter.SearchAdapter;
import com.example.tan.mtapp.Model.SearchModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    View view;
    SearchView search;
    SearchAdapter adapter;
    RecyclerView list ;
    ProgressDialog progressDialog;
    GridLayoutManager gridLayoutManager;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        list = (RecyclerView) view.findViewById(R.id.searchList);
        search = (SearchView) view.findViewById(R.id.search);
        setadapter();

        // Inflate the layout for this fragment
        return view;
    }

    private void setadapter(){
        adapter = new SearchAdapter(getContext(), StaticClass.SEARCH_MODEL, new SearchAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(SearchModel searchModel, int position) {
                StaticClass.toast(getContext(),String.valueOf(position));
            }
        });

        list.setAdapter(adapter);
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        list.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

    }

}
