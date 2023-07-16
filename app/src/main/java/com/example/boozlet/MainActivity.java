package com.example.boozlet;

import static com.example.boozlet.Constants.movingKeys.USERID;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import com.example.boozlet.Objects.Item;
import com.example.boozlet.Objects.Liquid;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.boozlet.databinding.ActivityMainBinding;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); // is it necessary?


        //should i find/accsess the user here?
        //DBUtil.getInstance()   user

        // init the fragments?
       // FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

       // firebaseDatabase.getReference(Constants.DBKeys.ITEMS).setValue(PreDatabaseData.getItemsPre());
        //only up or down
        //firebaseDatabase.getReference(Constants.DBKeys.ITEMS).setValue(PreDatabaseData.getItemsPre().getItemList());

        DBUtil.getInstance().addPreToDB2();
        //only for first time

  // ----------- put again --------
        String currUserKey = getIntent().getStringExtra(USERID);
        getUserFromDB(currUserKey); //passing it to the user db

        //that line is the problem
        //User user1 = UserDataManager.getInstance().getCurrUser(); //need to pass the user?
        //Log.d("tagUser", user1.getUserID());
//        Item item4 = new Liquid()
//                .setAbv(13)
//                .setSourLevel(2)
//                .setSugarLevel(3)
//                .setType()
//                .setName("testing")
//                .setOwned(false);
//
//        DBUtil.getInstance().addItemToCurrUserList(item4);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_inventory, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    public void getUserFromDB(String userID){
      DBUtil.getInstance().getUserByID(userID);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void getUserLogin(String uid){

    }



}