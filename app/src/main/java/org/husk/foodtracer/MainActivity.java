package org.husk.foodtracer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationBarView.OnItemSelectedListener {

    // Bottom Nav Bar
    private BottomNavigationView bottomNavigationView;

    // Instantiating all fragments
    private AllListFragment allListFragment = new AllListFragment();
    private ExpiredListFragment expiredListFragment = new ExpiredListFragment();

    // Instantiating database
    private DatabaseHandler databaseHandler;

    // Instantiating Calender and dateppicker
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting reference for all instances declared above
        bottomNavigationView = findViewById(R.id.bottom_nav_view);

        // Setting up bottom navigation on select listener
        loadFragment(allListFragment);
        bottomNavigationView.setOnItemSelectedListener(this);

        // Initializing database
        databaseHandler = new DatabaseHandler(this);

        // Initializing calender
        calendar = Calendar.getInstance();
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


    // PRIVATE FUNCTIONS
    // Function to load a fragment
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit();
    }

    // Funtion to show dialog box for adding new item
    private void showAddItemDialog() {
        // Inflate the dialog layout
        View dialogView = LayoutInflater.from(this).inflate(R.layout.add_item_dialog, null);

        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();

        // Set up the UI elements
        final EditText itemNameEditText = dialogView.findViewById(R.id.item_name_edittext);
        final Button expiry_date_picker = dialogView.findViewById(R.id.expiry_date_picker);
        final EditText itemQuantityEditText = dialogView.findViewById(R.id.item_quantity_edittext);
        Button addItemButton = dialogView.findViewById(R.id.add_item_button);

        // Set up the button click listener
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input data
                String itemName = itemNameEditText.getText().toString();
                String itemDate = expiry_date_picker.getText().toString();
                String quantity = itemQuantityEditText.getText().toString();

                // Validating all input field is filled
                if (itemName.isEmpty() || itemDate.isEmpty() || quantity.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseHandler.addNewFoodItem(itemName, itemDate, Integer.parseInt(quantity));

                Toast.makeText(MainActivity.this, "New item has been added.", Toast.LENGTH_SHORT).show();

                // Refreshing page to show new added item
                recreate();

                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        expiry_date_picker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Set the selected date on the button
                                calendar.set(year, monthOfYear, dayOfMonth);
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",
                                        Locale.getDefault());
                                expiry_date_picker.setText(dateFormat.format(calendar.getTime()));
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                        datePickerDialog.show();
            }
        });

        // Show the dialog
        dialog.show();
    }
}




