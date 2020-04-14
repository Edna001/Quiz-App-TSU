package com.edna.quizapptsu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.edna.quizapptsu.ui.QAHomeFragment;
import com.edna.quizapptsu.ui.QAPlayFragment;
import com.edna.quizapptsu.ui.QAProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mNavBar;
    private QAHomeFragment homeFragment;
    private QAPlayFragment playFragment;
    private QAProfileFragment profileFragment;
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
        homeFragment = new QAHomeFragment();
        playFragment = new QAPlayFragment();
        profileFragment = new QAProfileFragment();
        mNavBar.setSelectedItemId(R.id.home_item);
        replaceFragmentWithSlideAnim(homeFragment);
    }

    private void initFunctional() {
        mNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    default: throw new IllegalArgumentException("Position not valid");
                    case R.id.home_item:
                        replaceFragmentWithSlideAnim(homeFragment);
                    case R.id.empty_item:
                        break;
                    case R.id.profile_item:
                        replaceFragmentWithSlideAnim(profileFragment);
                }
                return true;
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragmentWithSlideAnim(playFragment);
            }
        });
    }

    private void replaceFragmentWithSlideAnim(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_left_animation,
                        R.anim.slide_in_right_animation,
                        R.anim.slide_in_right_animation,
                        R.anim.slide_in_left_animation)
                .replace(R.id.main_fragment_view, fragment)
                .commit();
    }
}
