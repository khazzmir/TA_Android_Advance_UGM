package com.example.taandroidjava;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Home_fragment extends Fragment {

    private int[] ice_cream_image = {R.drawable.cookies, R.drawable.chocolate, R.drawable.oreo};
    private String[] ice_cream_name = {"Cookies and Cream", "Chocolate", "Oreo"};
    private String[] ice_cream_desc = {
            "Cookies 'n cream is a dessert, often ice cream, with crumbled Oreo cookies mixed into vanilla or alternative flavors like chocolate, coffee, or mint.",
            "Chocolate ice cream, a global classic, blends natural or artificial chocolate flavor and serves as a versatile base. Enjoy it alone or as a foundation for delicious combinations.",
            "Oreo ice cream blends creamy vanilla with crumbled Oreo cookies, offering a sweet and crunchy treat. Capturing the iconic cookie flavor, it's a popular frozen delight."
    };

    int[] listimage = {R.drawable.cookies,
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

    String[] listtitle = {"Cookies n' Cream",
            "Chocolate",
            "Oreo",
            "Blue Raspberry",
            "Bubble Gum",
            "Durian",
            "Mango",
            "Mint",
            "Pistacio Almond",
            "Strawberry",
            "Taro",
            "Vanilla"
    };

    String[] listdesc = {
            "Cookies 'n cream is a dessert, often ice cream, with crumbled Oreo cookies mixed into vanilla or alternative flavors like chocolate, coffee, or mint.",
            "Chocolate ice cream, a worldwide classic, blends natural or artificial chocolate flavor, serving as a versatile base. Enjoy alone or in delightful combinations.",
            "Oreo ice cream blends creamy vanilla with crumbled Oreo cookies, offering a sweet and crunchy treat. Capturing the iconic cookie flavor, it's a popular frozen delight.",
            "Manufactured blue raspberry flavor for candy and drinks is crafted from banana, cherry, and pineapple esters. Despite lacking natural origin, it mimics raspberry's appeal and is sweetened for taste.",
            "Bubble gum flavor varies between companies with synthetic versions including esters like methyl salicylate. A natural alternative blends banana, pineapple, cinnamon, cloves, and wintergreen, with hints of vanilla, cherry, lemon, and orange oil.",
            "Durian, edible fruit of Durio genus, with 30+ species. Durio zibethinus, dominant globally, has diverse varieties in Thailand and Malaysia. Local regions offer various durian species.",
            "The 'Ice Cream' mango is a semi-dwarf mango cultivar that originated in Trinidad and Tobago and was later introduced to Florida.",
            "Mint ice cream blends mint flavor, often from peppermint or spearmint. Green coloring is common, but all-natural versions may be beige or white.",
            "Pistachio ice cream, with its vibrant green hue, derives its rich flavor from pistachio nuts. Versatile, it extends beyond traditional ice cream, featuring in sorbets, gelatos, and as a delectable layer in spumoni.",
            "Strawberry ice cream, a sweet blend of fresh strawberries, eggs, cream, and vanilla, dates back to 1813. Often pink, it's a Neapolitan favorite; a 2022 poll showed 43% enjoy it, with National Strawberry Ice Cream Day on January 15.",
            "Taro (Colocasia esculenta) is a versatile root vegetable cultivated for its corms, leaves, stems, and petioles. A dietary staple in African, Oceanic, and Asian cultures, taro is among the earliest cultivated plants.",
            "Vanilla, a popular ice cream flavor globally, is created by cooling a mix of cream, sugar, and vanilla over ice and salt. Preferences vary; North America/Europe favor a smokier taste, while Ireland leans towards anise-like."
    };

    FloatingActionButton fab;

    ViewPager2 viewPager2;

    RecyclerView recyclerView;

    ImageView imageView;

    Context context;

    private Handler slidehandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home_fragment, container, false);

        context = getContext();

        recyclerView = rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        HorizontalListAdapter adapter = new HorizontalListAdapter(context, listimage, listtitle);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            int selectedItemImg = listimage[position];
            String selectedItemTitle = listtitle[position];

            showPopUpList(position);
        });

        viewPager2 = rootView.findViewById(R.id.viewpager);

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

        rootView.findViewById(R.id.profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfile();
            }
        });

        rootView.findViewById(R.id.cookies).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(0);
            }
        });

        rootView.findViewById(R.id.chocolate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(1);
            }
        });

        rootView.findViewById(R.id.oreo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(2);
            }
        });

        return rootView;
    }

    private void showProfile() {
        View anchorView = getView().findViewById(R.id.profile);
        View popupView = LayoutInflater.from(context).inflate(R.layout.activity_profile_page, null);

        PopupWindow popupWindow = new PopupWindow(
                popupView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        ImageView imageView = popupView.findViewById(R.id.btnbck);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(anchorView);

    }

    private void showPopUpList(int index) {
        View anchorView = getView().findViewById(R.id.cookies);
        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_ice_cream, null);

        PopupWindow popupWindow = new PopupWindow(
                popupView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        ImageView imageView = popupView.findViewById(R.id.ice_cream);
        imageView.setImageResource(listimage[index]);

        TextView name = popupView.findViewById(R.id.ice_cream_name);
        name.setText(listtitle[index]);

        TextView textView = popupView.findViewById(R.id.ice_cream_desc);
        textView.setText(listdesc[index]);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(anchorView);
    }

    private void showPopUp(int index) {
        View anchorView = getView().findViewById(R.id.cookies);
        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_ice_cream, null);

        PopupWindow popupWindow = new PopupWindow(
                popupView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        ImageView imageView = popupView.findViewById(R.id.ice_cream);
        imageView.setImageResource(ice_cream_image[index]);

        TextView name = popupView.findViewById(R.id.ice_cream_name);
        name.setText(ice_cream_name[index]);

        TextView textView = popupView.findViewById(R.id.ice_cream_desc);
        textView.setText(ice_cream_desc[index]);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(anchorView);
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause(){
        super.onPause();
        slidehandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume(){
        super.onResume();
        slidehandler.postDelayed(sliderRunnable, 3000);
    }

}