package com.example.fooddelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
ActivityIntroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.goBtn.setOnClickListener(v -> startActivity(new Intent(IntroActivity.this,MainActivity.class)));
    }
}