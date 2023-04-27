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

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AllListFragment extends Fragment {

    // Recycler View
    private RecyclerView allListRecView;
    FoodItemRecViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Test data
        ArrayList<FoodItem> foodItems = new ArrayList<>();
        foodItems.add(new FoodItem("Banana", "22/02/2020", 5));
        foodItems.add(new FoodItem("Apple", "22/02/2020", 5));
        foodItems.add(new FoodItem("Carrot", "22/02/2020", 10));


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_all_list, container, false);

        // Setting up Recycle View
        allListRecView = rootView.findViewById(R.id.all_list_rec_view);

        adapter = new FoodItemRecViewAdapter(getContext());
        adapter.setFoodItems(foodItems);

        allListRecView.setAdapter(adapter);
        allListRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }
}