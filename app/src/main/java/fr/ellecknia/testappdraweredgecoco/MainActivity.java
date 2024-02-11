package fr.ellecknia.testappdraweredgecoco;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // --- NAVIGATION ---
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // --- HEADER ---
        ImageView headerIcon = navigationView.getHeaderView(0).findViewById(R.id.header_icon);
        headerIcon.setOnClickListener(v -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            new Home())
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

            navigationView.setCheckedItem(R.id.nav_home);
        });

        // --- DRAWER ---
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            uncheckAllMenuItems(navigationView.getMenu());
            menuItem.setChecked(true);

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,
                                    new Home())
                            .commit();
                    menuItem.setChecked(true);
                    break;
                case R.id.nav_act1:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,
                                    new Activity1())
                            .commit();
                    menuItem.setChecked(true);
                    break;
                case R.id.nav_act2:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,
                                    new Activity2())
                            .commit();
                    menuItem.setChecked(true);
                    break;
            }

            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            new Home())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    // Fonction qui permet de checker le bon item visuellement
    public static void uncheckAllMenuItems(Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.hasSubMenu()) {
                uncheckAllMenuItems(item.getSubMenu());
            } else {
                item.setChecked(false);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
