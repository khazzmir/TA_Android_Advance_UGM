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

import org.w3c.dom.Text;

public class chooseAdapter extends RecyclerView.Adapter<chooseAdapter.ViewHolder> {

    private int[] chooseimg;
    private String[] choosename;
    private Context context;
    private ViewPager2 viewPager2;

    chooseAdapter(Context context, int[] chooseimg, String[] choosename, ViewPager2 viewPager2) {
        this.context = context;
        this.chooseimg = chooseimg;
        this.choosename = choosename;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public chooseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.coneitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chooseAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(chooseimg[position]);
        holder.textView.setText(choosename[position]);
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
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.coneimg);
            textView = itemView.findViewById(R.id.conetxt);
        }
    }
}
