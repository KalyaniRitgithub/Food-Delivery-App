package com.example.fooddelivery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.fooddelivery.Domain.SliderItems;
import com.example.fooddelivery.R;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<SliderItems> SliderItems; // Example data type, adjust as needed
private ViewPager2 viewPager2;
private Context context;
private Runnable runnable=new Runnable() {
    @Override
    public void run() {
        SliderItems.addAll(SliderItems);
        notifyDataSetChanged();
    }
};

    public SliderAdapter(List<com.example.fooddelivery.Domain.SliderItems> sliderItems, ViewPager2 viewPager2) {
        SliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_viewholder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
RequestOptions requestOptions=new RequestOptions();

        requestOptions=requestOptions.transform(new CenterCrop(),new RoundedCorners(60));

    Glide.with(context)
            .load(SliderItems.get(position).getImage())
            .apply(requestOptions)
            .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return SliderItems.size();
    }

    public static class SliderViewHolder extends RecyclerView.ViewHolder {
       private ImageView imageView;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageSlide);
            // Initialize view elements here
        }

        // Example bind method
        // public void bind(String item) {
        //     // Bind the item to the view elements
        // }
    }
}
