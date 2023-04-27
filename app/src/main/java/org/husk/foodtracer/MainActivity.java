package org.husk.foodtracer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity
        implements NavigationBarView.OnItemSelectedListener{

    // Bottom Nav Bar
    private BottomNavigationView bottomNavigationView;

    // Instantiating all fragments
    AllListFragment allListFragment = new AllListFragment();
    ExpiredListFragment expiredListFragment = new ExpiredListFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting reference for all instances declared above
        bottomNavigationView = findViewById(R.id.bottom_nav_view);

        // Setting up bottom navigation on select listener
        loadFragment(allListFragment);
        bottomNavigationView.setOnItemSelectedListener(this);
    }

    // Define action based on selection in the bottom nav bar
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.all_list) {
            loadFragment(allListFragment);
            return true;
        }

        if (item.getItemId() == R.id.expired_list) {
            loadFragment(expiredListFragment);
            return true;
        }

        if (item.getItemId() == R.id.add_new_item) {
            showAddItemDialog();
            return true;
        }

        return false;
    }

    // Function to load a fragment
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit();
    }

    // Method to show dialog box for adding new item
    private void showAddItemDialog() {
        // Inflate the dialog layout
        View dialogView = LayoutInflater.from(this).inflate(R.layout.add_item_dialog, null);

        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();

        // Set up the UI elements
        final EditText itemNameEditText = dialogView.findViewById(R.id.item_name_edittext);
        final EditText itemDateEditText = dialogView.findViewById(R.id.item_date_edittext);
        Button addItemButton = dialogView.findViewById(R.id.add_item_button);

        // Set up the button click listener
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input data
                String itemName = itemNameEditText.getText().toString();
                String itemDescription = itemDateEditText.getText().toString();

                // Do something with the input data
                // -------------------------------
                //---------------------------------

                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Show the dialog
        dialog.show();
    }
}


