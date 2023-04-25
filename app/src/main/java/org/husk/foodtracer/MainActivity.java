package org.husk.foodtracer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity
        implements NavigationBarView.OnItemSelectedListener{

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

        loadFragment(allListFragment);

        bottomNavigationView.setOnItemSelectedListener(this);
    }

    // Define action based on selection in the bottom nav bar
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.allList) {
            loadFragment(allListFragment);
            return true;
        }

        if (item.getItemId() == R.id.expiredList) {
            loadFragment(expiredListFragment);
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
}


