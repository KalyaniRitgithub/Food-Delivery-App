package com.example.fooddelivery.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.example.fooddelivery.Adapter.CategoryAdapter;
import com.example.fooddelivery.Adapter.SliderAdapter;
import com.example.fooddelivery.Domain.Category;
import com.example.fooddelivery.Domain.SliderItems;
import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    binding=ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    initCategory();
    initBanner();
    
    setVariable();
    }

    private void initBanner() {
        DatabaseReference myRef= database.getReference("Banners");
        binding.progressBarBanner.setVisibility(View.VISIBLE);
        ArrayList<SliderItems> items=new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        items.add(issue.getValue(SliderItems.class));

                    }
                    banners(items);
                    binding.progressBarBanner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void banners(ArrayList<SliderItems> items){
        binding.viewpager2.setAdapter(new SliderAdapter(items,binding.viewpager2));
    binding.viewpager2.setClipChildren(false);
    binding.viewpager2.setClipToPadding(false);
    binding.viewpager2.setOffscreenPageLimit(3);
    binding.viewpager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
    compositePageTransformer.addTransformer(new MarginPageTransformer(40));
binding.viewpager2.setPageTransformer(compositePageTransformer);
    }
    private void setVariable() {
        binding.bottomMenu.setItemEnabled(R.id.home,true);
        binding.bottomMenu.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                if(i==R.id.cart){
                    startActivity(new Intent(MainActivity.this,CartActivity.class));
                }
            }
        });
    }

    private void initCategory() {
        DatabaseReference myRef=database.getReference("Category");
        binding.progressBarCategory.setVisibility(View.VISIBLE);
        ArrayList<Category> list=new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue:snapshot.getChildren()){
                        list.add(issue.getValue(Category.class));
                    }
                    if(list.size()>0){
                        binding.categoryView.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
                        binding.categoryView.setAdapter(new CategoryAdapter(list));
                    }
                    binding.progressBarCategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}