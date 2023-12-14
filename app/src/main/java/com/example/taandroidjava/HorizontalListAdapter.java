package com.example.taandroidjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalListAdapter extends RecyclerView.Adapter<HorizontalListAdapter.ViewHolder> {

    private Context context;
    private String[] titles;
    private int[] ice_cream_img;
    private OnItemClickListener listener;

    public HorizontalListAdapter(Context context, int[] ice_cream_img, String[] titles) {
        this.titles = titles;
        this.ice_cream_img = ice_cream_img;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = (OnItemClickListener) listener;
    }

    @NonNull
    @Override
    public HorizontalListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_horizontal_listview,parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = titles[position];
        int imageResource = ice_cream_img[position];

        holder.icecream_list.setImageResource(imageResource);
        holder.icecream_title.setText(title);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView icecream_title;
        ImageView icecream_list;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icecream_list = itemView.findViewById(R.id.icecream_list);
            icecream_title = itemView.findViewById(R.id.icecream_title);

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });

        }
    }
}
