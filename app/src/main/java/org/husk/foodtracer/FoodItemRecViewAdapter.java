package org.husk.foodtracer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodItemRecViewAdapter extends RecyclerView.Adapter<FoodItemRecViewAdapter.ViewHolder> {

    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private Context context;
    private DatabaseHandler databaseHandler;

    public FoodItemRecViewAdapter(Context context, DatabaseHandler databaseHandler) {

        this.context = context;
        this.databaseHandler = databaseHandler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.foodItemName.setText(foodItems.get(position).getName());
        holder.foodItemExpiryDate.setText(foodItems.get(position).getExpiryDate());
        holder.foodQuantity.setText(String.valueOf(foodItems.get(position).getQuantity()));
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                databaseHandler.deleteCourse(foodItems.get(position).getId());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFoodItems(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView recViewParent;
        private TextView foodItemName, foodItemExpiryDate, foodQuantity;
        private Button deleteButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recViewParent = itemView.findViewById(R.id.rec_view_parent);
            foodItemName = itemView.findViewById(R.id.food_item_name);
            foodItemExpiryDate = itemView.findViewById(R.id.food_item_expiry_date);
            foodQuantity = itemView.findViewById(R.id.food_item_quantity);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}
