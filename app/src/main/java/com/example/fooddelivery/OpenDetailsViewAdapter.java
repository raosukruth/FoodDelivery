package com.example.fooddelivery;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import android.os.Bundle;

import com.squareup.picasso.Picasso;

import static java.security.AccessController.getContext;

public class OpenDetailsViewAdapter extends RecyclerView.Adapter {
    ArrayList itemsToShow;
    OpenDetailsViewAdapter(ArrayList itemsToShow_) {
        itemsToShow = itemsToShow_;
    }

    @Override
    public OpenDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.opendetails, parent,false);
        // set the view's size, margins, paddings and layout parameters
        OpenDetailsViewHolder vh = new OpenDetailsViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder_, final int position) {
        // set the data in items
        OpenDetailsViewHolder holder = (OpenDetailsViewHolder) holder_;
        MenuItem item = (MenuItem)itemsToShow.get(position);
        holder.displayName.setText(item.getName());
        holder.displayPrice.setText(item.getPrice());
        Picasso.with(holder.imageView.getContext()).load(item.getUrl()).fit().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemsToShow.size();
    }
    public class OpenDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView displayName;
        TextView displayPrice;
        ImageView imageView;

        public OpenDetailsViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            displayName = (TextView) itemView.findViewById(R.id.displayName);
            displayPrice = (TextView) itemView.findViewById(R.id.displayPrice);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    int index = getAdapterPosition();
                    if (index == RecyclerView.NO_POSITION) {
                        return;
                    }
                    MenuItem item = (MenuItem) OpenDetailsViewAdapter.this.itemsToShow.get(index);

                    Bundle bundle = new Bundle();
                    bundle.putString("name", item.getName());
                    bundle.putString("description", item.getDescription());
                    bundle.putString("availability", item.getAvailability());
                    bundle.putString("price", item.getPrice());

                    Intent intent = new Intent(context, open_details_activity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

        }
    }
}