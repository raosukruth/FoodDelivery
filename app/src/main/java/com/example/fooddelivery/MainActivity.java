package com.example.fooddelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference menuDbRef;
    private Button cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        recyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);
        layoutManager = (RecyclerView.LayoutManager) new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        FirebaseDatabase firebaseDb = FirebaseDatabase.getInstance();
        menuDbRef = firebaseDb.getReference("menu");
        menuDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList menuItems = new ArrayList<>();
                int index = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    MenuItem item = ds.getValue(MenuItem.class);
                    menuItems.add(index++, item);
                }
                OpenDetailsViewAdapter openDetailsViewAdapter = new OpenDetailsViewAdapter(menuItems);
                recyclerView.setAdapter(openDetailsViewAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cartButton = (Button)findViewById(R.id.cart);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });

    }
}
