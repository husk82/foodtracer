package org.husk.foodtracer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.-
 * create an instance of this fragment.
 */
public class AllListFragment extends Fragment {

    // Recycler View
    private RecyclerView allListRecView;
    private FoodItemRecViewAdapter adapter;
    private DatabaseHandler databaseHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        databaseHandler = new DatabaseHandler(requireActivity());

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_all_list, container, false);

        // Setting up Recycle View
        allListRecView = rootView.findViewById(R.id.all_list_rec_view);

        adapter = new FoodItemRecViewAdapter(getContext());
        // Retrieving data from database and setting it on recyclerview
        adapter.setFoodItems(databaseHandler.readFoodItems());

        allListRecView.setAdapter(adapter);
        allListRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }
}