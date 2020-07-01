package com.example.fooddelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private Button clearOrderButton;
    private Button placeOrderButton;
    private RecyclerView recyclerView;
    private DatabaseReference cartDbRef;
    private RecyclerView.LayoutManager layoutManager_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = (RecyclerView) findViewById(R.id.cart_recycler_view);
        layoutManager_ = (RecyclerView.LayoutManager) new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager_);

        FirebaseDatabase firebaseDb = FirebaseDatabase.getInstance();
        cartDbRef = firebaseDb.getReference("cart");

        cartDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList cartItems = new ArrayList<>();
                int index = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    CartItem item = ds.getValue(CartItem.class);
                    cartItems.add(index++, item);
                }
                CartViewAdapter cartViewAdapter = new CartViewAdapter(cartItems);
                recyclerView.setAdapter(cartViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        clearOrderButton = (Button) findViewById(R.id.clearOrder);
        clearOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartDbRef.setValue(null);
            }
        });

        placeOrderButton = (Button) findViewById(R.id.placeOrder);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Your order has been placed", Toast.LENGTH_LONG).show();
                cartDbRef.setValue(null);
                finish();
            }
        });


    }
}
