package com.example.staggeredRecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> names;
    private ArrayList<String> imageUrls;
    private Context context;

    RecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> imageUrls) {
        this.names = names;
        this.imageUrls = imageUrls;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground);

        Glide.with(context)
                .load(imageUrls.get(position))
                .apply(requestOptions)
                .into(holder.imageView);

        holder.textView.setText(names.get(position));

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + names.get(position));
                Toast.makeText(context, names.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.ImageId);
            this.textView = itemView.findViewById(R.id.TextViewId);
        }
    }
}
