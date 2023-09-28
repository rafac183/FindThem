package com.rafac183.findthem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

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
    private HomeFragment homeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*--------------Methods------------*/
        MenuLogOut();
        getUserName();

        /*--------------Toolbar------------*/
        setSupportActionBar(binding.appBarNavigation.toolbar);

        /*--------------Navigation Drawer Menu---------------*/
        binding.navView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.appBarNavigation.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_persons, R.id.nav_pets, R.id.nav_profile, R.id.nav_share, R.id.nav_rate_us)//Si no lo coloco aqui no podre abrir otra vez la pestaña
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
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

    public void MenuLogOut(){
        /*--------------LogOut-------------*/
        Menu menu = binding.navView.getMenu();
        menu.findItem(R.id.nav_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent myIntent = new Intent(NavigationActivity.this, LoginActivity.class);
                startActivity(myIntent);
                finish();
                return true;
            }
        });
    }

    public void getUserName(){
        String username = getIntent().getStringExtra("user");
        TextView tvUsername = binding.navView.getHeaderView(0).findViewById(R.id.textViewUser);
        tvUsername.setText(username);
    }
}

//binding.navView.setNavigationItemSelectedListener(this);

        /*binding.appBarNavigation.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            //case R.id.
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }*/