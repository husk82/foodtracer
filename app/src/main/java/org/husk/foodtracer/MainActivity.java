package org.husk.foodtracer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragment, allListFragment)
                .commit();

        bottomNavigationView.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.allList) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainFragment, allListFragment)
                    .commit();
            return true;
        }

        if (item.getItemId() == R.id.expiredList) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainFragment, expiredListFragment)
                    .commit();
            return true;
        }

        return false;
    }
}