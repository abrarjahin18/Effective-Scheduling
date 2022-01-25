package com.example.hp.effectivescheduling;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        setUpToolbar ();
        navigationView = (NavigationView) findViewById (R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener (new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId ()) {
                    case R.id.nav_newnotes:
                        openNotes ();
                        break;
                    case R.id.nav_notes:
                        SaveNote ();

                        break;
                    case R.id.nav_aboutus:
                        aboutUs ();
                        break;
                    case R.id.nav_reminder:
                        Reminder_Activity ();
                        break;
                    case R.id.nav_settings:
                        Setting_Activity ();

                        break;
                }
                return false;
            }
        });


    }

    private void setUpToolbar() {
        drawerLayout = (DrawerLayout) findViewById (R.id.drawerLayout);
        toolbar = (Toolbar) findViewById (R.id.toolbar);
        //setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle (this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener (actionBarDrawerToggle);
        actionBarDrawerToggle.syncState ();
    }

    public void openNotes() {
        Intent intent = new Intent (this, NewNoteActivity.class);
        startActivity (intent);
    }

    public void aboutUs() {
        Intent intent = new Intent (this, aboutUs.class);
        startActivity (intent);
    }

    public void Reminder_Activity() {
        Intent intent = new Intent (this, Reminder_Activity.class);
        startActivity (intent);

    }

    public void SaveNote() {
        Intent intent = new Intent (MainActivity.this, SaveNote.class);


        startActivity (intent);
    }

    public void Setting_Activity() {
        Intent intent = new Intent (MainActivity.this, Setting_Activity.class);

        startActivity (intent);

    }


    }



