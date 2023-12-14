package com.example.taandroidjava;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class ice_cream extends Fragment implements iceAdapter.OnItemClickListener{

    public interface OnPositionChangeListener {
        void onPositionChangeic(int position);
    }

    int itemselected = 0;

    private ViewPager2 viewPager2;

    iceAdapter adapter;

    Context context;

    private int[] imageItem = {R.drawable.cookies,
            R.drawable.chocolate,
            R.drawable.oreo,
            R.drawable.blue_raspberry,
            R.drawable.bubble_gum,
            R.drawable.durian,
            R.drawable.mangga,
            R.drawable.mint,
            R.drawable.pistacio_almond,
            R.drawable.stroberi,
            R.drawable.taro,
            R.drawable.vanilla
    };

    private ice_cream.OnPositionChangeListener onPositionChangeListeneric;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ice_cream, container, false);

        context = getContext();
        viewPager2 = rootView.findViewById(R.id.imgitemic);

        // Check if the adapter is null, create a new one if needed
        if (adapter == null) {
            adapter = new iceAdapter(context, imageItem, viewPager2);
        }

        viewPager2.setAdapter(adapter);

        int index = adapter.getCurrentIndex();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            int lastPosition = -1;

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position != lastPosition) {
                    itemselected = position;
                    notifyPositionChangeic(position);
                    lastPosition = position;
                }
            }
        });

        return rootView;
    }

    public void onItemClick(int position) {
        if (position != viewPager2.getCurrentItem()) {
            itemselected = position;
            viewPager2.setCurrentItem(position);

            // Notify the activity about the selected position
            if (onPositionChangeListeneric != null) {
                onPositionChangeListeneric.onPositionChangeic(position);
            }
        }
    }

    public int getSelectedIceCreamIndex() {
        return viewPager2.getCurrentItem();
    }

    public void setOnPositionChangeListeneric(OnPositionChangeListener listener) {
        this.onPositionChangeListeneric = listener;
    }

    private void notifyPositionChangeic(int position) {
        if (onPositionChangeListeneric != null) {
            onPositionChangeListeneric.onPositionChangeic(position);
        }
    }
}