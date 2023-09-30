package com.rafac183.findthem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivityNavigationBinding;
import com.rafac183.findthem.ui.home.HomeFragment;

public class NavigationActivity extends AppCompatActivity {

    /*Variables*/
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationBinding binding;
    private Menu menuNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*--------------Methods------------*/
        MenuBtns();
        getUserName();

        /*--------------Toolbar------------*/
        setSupportActionBar(binding.appBarNavigation.toolbar);

        /*--------------Fragments------------*/
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_people, R.id.nav_pets)//Si no lo coloco aqui no podre abrir otra vez la pestaña
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /*--------------Navigation Drawer Menu---------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, binding.appBarNavigation.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(NavigationActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void MenuBtns(){
        /*--------------LogOut-------------*/
        menuNV = binding.navView.getMenu();
        menuNV.findItem(R.id.nav_logout).setOnMenuItemClickListener(item -> {
            Intent myIntent = new Intent(NavigationActivity.this, LoginActivity.class);
            startActivity(myIntent);
            finish();
            return true;
        });
        menuNV.findItem(R.id.nav_profile).setOnMenuItemClickListener(item -> {
            Intent myIntent = new Intent(NavigationActivity.this, ProfileActivity.class);
            startActivity(myIntent);
            return true;
        });
        menuNV.findItem(R.id.nav_rate_us).setOnMenuItemClickListener(item -> {
            Intent myIntent = new Intent(NavigationActivity.this, RateUsActivity.class);
            startActivity(myIntent);
            return true;
        });
        menuNV.findItem(R.id.nav_share).setOnMenuItemClickListener(item -> {
            Intent myIntent = new Intent(NavigationActivity.this, ShareActivity.class);
            startActivity(myIntent);
            return true;
        });
    }

    public void getUserName(){
        String username = getIntent().getStringExtra("user");
        TextView tvUsername = binding.navView.getHeaderView(0).findViewById(R.id.textViewUser);
        tvUsername.setText(username);
    }


}