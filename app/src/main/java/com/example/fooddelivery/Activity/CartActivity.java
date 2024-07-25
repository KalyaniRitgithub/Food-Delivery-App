package com.example.fooddelivery.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fooddelivery.Adapter.CartAdapter;
import com.example.fooddelivery.Helper.ChangeNumberItemsListener;
import com.example.fooddelivery.Helper.ManagementCart;
import com.example.fooddelivery.databinding.ActivityCartBinding;

public class CartActivity extends BaseActivity {
ActivityCartBinding binding;
private ManagementCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managmentCart=new ManagementCart(this);
        setVariable();
        calculateCart();
        initCartList();
    }

    private void initCartList() {
        if(managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollViewCart.setVisibility(View.GONE);

        }
        else{
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollViewCart.setVisibility(View.VISIBLE);
        }
        binding.cartView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
   binding.cartView.setAdapter(new CartAdapter(managmentCart.getListCart(),managmentCart , () -> calculateCart()));
    }

    private void calculateCart() {
        double percentTax=0.02;
        double delivery=10;
        double tax=Math.round(managmentCart.getTotalFee()* percentTax* 100.0)/100;
   double total=Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100;
   double itemTotal=Math.round(managmentCart.getTotalFee()*100)/100;
   binding.totalFeeTxt.setText("$"+itemTotal);
   binding.taxTxt.setText("$"+tax);
   binding.deliveryTxt.setText("$"+delivery);
   binding.totalTxt.setText("$"+total);
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
    }
}