package com.example.taandroidjava;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class cone extends Fragment implements chooseAdapter.OnItemClickListener{

    public interface OnPositionChangeListener {
        void onPositionChange(int position);
    }

    int itemselected = 0;

    private int selectedIndex = 0; // Initialize selectedIndex to 0

    chooseAdapter adapter; // Add this line

    public int getCurrentIndex() {
        return adapter != null ? adapter.getCurrentIndex() : 0;
    }

    private ViewPager2 viewPager2;

    Context context;

    private int[] imageItem = {R.drawable.cone, R.drawable.cup};

    private String[] ItemName = {"Cone", "Cup"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cone, container, false);
        View view = inflater.inflate(R.layout.activity_buying_page, container, false);

        context = getContext();
        viewPager2 = rootView.findViewById(R.id.imgitem);
        adapter = new chooseAdapter(context, imageItem, ItemName, viewPager2);
        viewPager2.setAdapter(adapter);

        // int index = adapter.getCurrentIndex(); // Remove this line

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Update the index when the page is changed
                selectedIndex = position;
                // Notify the activity about the selected position
                notifyPositionChange(position);
            }
        });


        return rootView;
    }

    public void onItemClick(int position) {
        itemselected = position;
        viewPager2.setCurrentItem(position);

        // Notify the activity about the selected position
        if (onPositionChangeListener != null) {
            onPositionChangeListener.onPositionChange(position);
        }
    }

    public int getSelectedIndex() {
        return viewPager2.getCurrentItem();
    }

    private OnPositionChangeListener onPositionChangeListener;

    public void setOnPositionChangeListener(OnPositionChangeListener listener) {
        this.onPositionChangeListener = listener;
    }

    private void notifyPositionChange(int position) {
        if (onPositionChangeListener != null) {
            onPositionChangeListener.onPositionChange(position);
        }
    }
}