package com.example.fooddelivery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CartViewAdapter extends RecyclerView.Adapter {
    ArrayList itemsToShow;

    CartViewAdapter(ArrayList itemsToShow_) {
        itemsToShow = itemsToShow_;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CartViewHolder vh = new CartViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder_, final int position) {
        // set the data in items
        CartViewHolder holder = (CartViewHolder) holder_;
        CartItem item = (CartItem) itemsToShow.get(position);
        holder.itemName.setText(item.getName());
        holder.itemQuantity.setText(item.getQuantity());
        holder.itemPrice.setText(item.getPrice());
    }

    @Override
    public int getItemCount() {
        return itemsToShow.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemPrice;
        TextView itemQuantity;

        public CartViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            itemName = (TextView) itemView.findViewById(R.id.cartItemName);
            itemQuantity = (TextView) itemView.findViewById(R.id.cartItemQuantity);
            itemPrice = (TextView) itemView.findViewById(R.id.cartItemPrice);
        }
    }
}
