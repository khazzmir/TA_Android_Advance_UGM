package com.example.taandroidjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class iceAdapter extends RecyclerView.Adapter<iceAdapter.ViewHolder>{

    private int[] chooseimg;
    private Context context;
    private ViewPager2 viewPager2;

    iceAdapter(Context context, int[] chooseimg, ViewPager2 viewPager2) {
        this.context = context;
        this.chooseimg = chooseimg;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public iceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iceitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull iceAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(chooseimg[position]);
    }

    @Override
    public int getItemCount() {
        return chooseimg.length;
    }

    public int getCurrentIndex() {
        return viewPager2.getCurrentItem();
    }

    public interface OnItemClickListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iceimg);
        }
    }
}
