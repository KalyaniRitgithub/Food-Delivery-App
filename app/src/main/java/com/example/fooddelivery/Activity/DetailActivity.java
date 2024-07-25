package com.example.fooddelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.fooddelivery.Domain.Foods;
import com.example.fooddelivery.Helper.ManagementCart;
import com.example.fooddelivery.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private Foods object;
    private int num = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize binding
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve intent extras
        getIntentExtra();

        // Set up variables and UI components
        setVariable();
    }

    private void setVariable() {
        managementCart = new ManagementCart(this);

        // Set up click listener for back button
        binding.backBtn.setOnClickListener(v -> finish());

        // Load image with Glide
        Glide.with(this)
                .load(object.getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(60))
                .into(binding.pic);

        // Set text views
        binding.priceTxt.setText("$" + object.getPrice());
        binding.titleTxt.setText(object.getTitle());
        binding.description.setText(object.getDescription());
        binding.ratingTxt.setText(object.getStar() + " Rating");
        binding.ratingBar.setRating((float) object.getStar());
        binding.totalTxt.setText(num * object.getPrice() + "$");

        // Set up quantity buttons
        binding.plusBtn.setOnClickListener(v -> {
            num++;
            binding.numTxt.setText(String.valueOf(num));
            binding.totalTxt.setText("$" + (num * object.getPrice()));
        });

        binding.minusBtn.setOnClickListener(v -> {
            if (num > 1) {
                num--;
                binding.numTxt.setText(String.valueOf(num));
                binding.totalTxt.setText("$" + (num * object.getPrice()));
            }
        });

        // Add to cart button
        binding.addBtn.setOnClickListener(v -> {
            object.setNumberInCart(num);
            managementCart.insertFood(object);
        });
    }

    private void getIntentExtra() {
        object = (Foods) getIntent().getSerializableExtra("object");
    }
}
