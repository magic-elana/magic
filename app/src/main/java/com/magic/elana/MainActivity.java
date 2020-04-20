package com.magic.elana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text);
//
//        int number = 1;
//        int number2 = 3;
//        double d = 1.0;
//        float f = 1.0f;
//        String text = "Hello!";
//
//        Cup cup1 = new CoffeeCup();
//        Cup cup2 = new TeaCup();
//
//        textView.setText("Pour in: " + cup1.pourIn() + " Pour out: " + cup1.pourOut());

        // ---------- BottomNavigation ----------------
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                openFragment(new HomeFragment());
                                return true;
                            case R.id.navigation_sms:
                                openFragment(new CreateFragment());
                                return true;
                            case R.id.navigation_notifications:
                                openFragment(new SavedFragment());
                                return true;
                        }
                        return false;
                    }

                };
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

//    int add(int n1, int n2) {
//        return n1 + n2;
//    }
//
//    interface Cup {
//        String pourIn();
//        String pourOut();
//    }
//
//    class CoffeeCup implements Cup {
//
//        @Override
//        public String pourIn() {
//            return "Grind Coffee beans, boiled hot water, dripped through coffee powder, put in cup";
//        }
//
//        @Override
//        public String pourOut() {
//            return "comes out coffee";
//        }
//    }
//
//    class TeaCup implements Cup {
//
//        @Override
//        public String pourIn() {
//            return "Put in tea bag, heat up water";
//        }
//
//        @Override
//        public String pourOut() {
//            return "come out tea";
//        }
    }
}