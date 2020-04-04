package com.example.moviesapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.moviesapp.R;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    //    @BindView(R.id.ah_bottom_navigation)
//    AHBottomNavigation ahBottomNavigation;
//
//    @BindView(R.id.recycler_view_id)
//    RecyclerView recyclerView;
//
//    @BindView(R.id.text_id)
//    TextView textView;
    AHBottomNavigation ahBottomNavigation;
    TextView textView;
    RecyclerView recyclerView;

    //Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initializeView();
        this.createNavigationItems();

    }
    public static Intent getMainActivityIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    public void initializeView(){
        ahBottomNavigation=findViewById(R.id.ah_bottom_navigation);
        textView=findViewById(R.id.text_id);
    }

    private void createNavigationItems(){

// Create items
        AHBottomNavigationItem item1=new AHBottomNavigationItem(R.string.home,R.drawable.ic_home,R.color.color_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.search, R.drawable.ic_search, R.color.color_search);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.profile, R.drawable.ic_profile, R.color.color_profile);

// Add items
        ahBottomNavigation.addItem(item1);
        ahBottomNavigation.addItem(item2);
        ahBottomNavigation.addItem(item3);

//// Set background color
        ahBottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        //  Change colors
        ahBottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        ahBottomNavigation.setInactiveColor(Color.parseColor("#747474"));

// Force to tint the drawable (useful for font with icon for example)
        ahBottomNavigation.setForceTint(true);


// Manage titles
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        ahBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

// Use colored navigation with circle reveal effect
        ahBottomNavigation.setColored(true);

// Set current item programmatically
        ahBottomNavigation.setCurrentItem(0);
//        bindData();

        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
//                cosmicCategory=position;
//                bindData();
                switch (position){
                    case 0:
                        textView.setText("Home");
                        break;
                    case 1:
                        textView.setText("Search");
                        break;
                    case 2:
                        textView.setText("Profile");
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}
