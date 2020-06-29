package com.edna.quizapptsu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.edna.quizapptsu.view.HistoryFragment;
import com.edna.quizapptsu.view.PlayFragment;
import com.edna.quizapptsu.view.QuestionsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mNavBar;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initFunctional();
    }

    private void initUI() {
        mNavBar = findViewById(R.id.main_nav_bar);
        mFab = findViewById(R.id.main_fab);
        toNext(new HistoryFragment());
    }

    private void initFunctional() {
        mNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    default: throw new IllegalArgumentException("Position not valid");
                    case R.id.home_item:
                        toNext(new HistoryFragment());
                    case R.id.empty_item:
                        break;
                    case R.id.profile_item:
                        toNext(new QuestionsFragment());
                }
                return true;
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNext(new PlayFragment());
            }
        });
    }

    private void toNext(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_left_animation,
                        R.anim.slide_in_right_animation,
                        R.anim.slide_in_left_animation,
                        R.anim.slide_in_right_animation)
                .replace(R.id.main_container, fragment)
                .commit();
    }
}