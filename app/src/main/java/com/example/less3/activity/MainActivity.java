package com.example.less3.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.less3.R;
import com.example.less3.fragment.CartFragment;
import com.example.less3.fragment.HomeFragment;
import com.example.less3.fragment.ProfileFragment;
import com.example.less3.fragment.TypeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

     public BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.framelayout);

        replace(new HomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemid = item.getItemId();

                if(itemid == R.id.navigation_home){
                    HomeFragment homeFragment = new HomeFragment();
                    replace(homeFragment);
                } else if (itemid == R.id.navigation_type) {
                    TypeFragment typeFragment = new TypeFragment();
                    replace(typeFragment);
                } else if (itemid == R.id.navigation_cart) {
                    CartFragment cartFragment = new CartFragment();
                    replace(cartFragment);
                } else if (itemid == R.id.navigation_profile) {
                    ProfileFragment profileFragment = new ProfileFragment();
                    replace(profileFragment);
                }

                return true;
            }
        });
    }
    public void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.addToBackStack(null); // Thêm Fragment vào Back Stack
        transaction.commit();
    }

}