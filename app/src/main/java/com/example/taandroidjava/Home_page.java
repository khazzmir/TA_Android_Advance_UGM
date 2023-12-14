package com.example.taandroidjava;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.taandroidjava.databinding.ActivityHomePageBinding;
import com.example.taandroidjava.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home_page extends AppCompatActivity {

    ViewPager2 viewPager2;

    private Handler slidehandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_page);

        viewPager2 = findViewById(R.id.viewpager);

        List<SlideItem> slideItems = new ArrayList<>();
        slideItems.add(new SlideItem(R.drawable.caroussel_1));
        slideItems.add(new SlideItem(R.drawable.caroussel_2));
        slideItems.add(new SlideItem(R.drawable.caroussel_3));

        viewPager2.setAdapter(new SlideAdapter(slideItems, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositionTransform = new CompositePageTransformer();
        compositionTransform.addTransformer(new MarginPageTransformer(25));
        compositionTransform.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

                float r = 1- Math.abs(position);
                page.setScaleY(0.85f + r*0.15f);

            }
        });

        viewPager2.setPageTransformer(compositionTransform);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                slidehandler.removeCallbacks(sliderRunnable);
                slidehandler.postDelayed(sliderRunnable, 3000);
            }
        });

    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause(){
        super.onPause();
        slidehandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume(){
        super.onResume();
        slidehandler.postDelayed(sliderRunnable, 3000);
    }
}