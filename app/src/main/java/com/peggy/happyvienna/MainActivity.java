package com.peggy.happyvienna;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.peggy.happyvienna.R; // Explicitly import the R class

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_LAST_SELECTED_TAB = "LAST_SELECTED_TAB";

    private BottomNavigationView bottomNavigationView;
    private int currentSelectedTabId = R.id.nav_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        setupBottomNavigation();

        // Defense: Restore Last Known State on Configuration Change
        if (savedInstanceState != null) {
            currentSelectedTabId = savedInstanceState.getInt(KEY_LAST_SELECTED_TAB, R.id.nav_home);
            Log.d(TAG, "Restoring state, last tab ID: " + currentSelectedTabId);
        } else {
            loadFragmentForId(currentSelectedTabId);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_LAST_SELECTED_TAB, currentSelectedTabId);
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == currentSelectedTabId) {
                    return true;
                }

                currentSelectedTabId = itemId;
                return loadFragmentForId(itemId);
            }
        });
    }

    private boolean loadFragmentForId(int itemId) {
        if (itemId == R.id.nav_home) {
            Log.d(TAG, "Navigate to Home Command Center");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
            return true;
        } else if (itemId == R.id.nav_health) {
            Log.d(TAG, "Navigate to Health Intelligence");
            return true;
        } else if (itemId == R.id.nav_finance) {
            Log.d(TAG, "Navigate to Finance Solver");
            return true;
        } else if (itemId == R.id.nav_inventory) {
            Log.d(TAG, "Navigate to Inventory Manager");
            return true;
        } else if (itemId == R.id.nav_vault) {
            Log.d(TAG, "Navigate to Secure Vault");
            return true;
        }

        Log.w(TAG, "Unknown navigation item selected.");
        return false;
    }
}