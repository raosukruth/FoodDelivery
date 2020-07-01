package com.example.fooddelivery;

import android.app.AppComponentFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class open_details_activity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView priceTextView;
    private TextView availabilityTextView;
    private TextView descriptionTextView;
    private TextView amountTextView;
    private Button addItemButton;
    private EditText quantityEditText;

    private DatabaseReference cartDbRef;

    private String itemName;
    private String itemPrice;
    private String itemQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_details);

        nameTextView = (TextView)findViewById(R.id.selectName);
        priceTextView = (TextView)findViewById(R.id.selectPrice);
        availabilityTextView = (TextView)findViewById(R.id.selectAvailability);
        descriptionTextView = (TextView)findViewById(R.id.selectDescription);
        quantityEditText = (EditText)findViewById(R.id.selectQuantity);
        amountTextView = (TextView)findViewById(R.id.selectAmount);

        Bundle bundle = getIntent().getExtras();
        itemName = bundle.getString("name");
        itemPrice = bundle.getString("price");

        nameTextView.setText(itemName);
        priceTextView.setText(itemPrice);
        descriptionTextView.setText(bundle.getString("description"));
        availabilityTextView.setText(bundle.getString( "availability"));

        FirebaseDatabase firebaseDb = FirebaseDatabase.getInstance();
        cartDbRef = firebaseDb.getReference("cart");

        quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                itemQuantity = quantityEditText.getText().toString();
                int numQuantity = Integer.parseInt(itemQuantity);
                float numPrice = Float.parseFloat(itemPrice.substring("Rs".length()));
                String amount = String.format("Rs %.2f", numQuantity * numPrice);
                amountTextView.setText(amount);
            }
        });

        addItemButton = (Button)findViewById(R.id.addItem);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemQuantity = quantityEditText.getText().toString();
                CartItem cartItem = new CartItem(itemName, itemQuantity, itemPrice);
                cartDbRef.child(itemName).setValue(cartItem);
                finish();
            }
        });

    }
}

