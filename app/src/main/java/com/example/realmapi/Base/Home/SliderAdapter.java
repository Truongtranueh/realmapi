package com.example.realmapi.Base.Home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.realmapi.Model.SliderItem;
import com.example.realmapi.R;
import com.example.realmapi.databinding.FragmentHomeImageslideBinding;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.Viewholder> {
    private List<SliderItem> sliderItemList;
    private ViewPager2 viewPager2;

    public SliderAdapter(List<SliderItem> sliderItemList,ViewPager2 viewPager2) {
        this.sliderItemList = sliderItemList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_imageslide, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.binding.imgviewslider.setImageResource(sliderItemList.get(position).getImage());
        if(position == sliderItemList.size() - 2){
            viewPager2.post(runnable);
        }
        holder.binding.imgviewslider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Hahaha","FFF");
            }
        });
    }

    @Override
    public int getItemCount() {
        return sliderItemList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private FragmentHomeImageslideBinding binding;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            binding = FragmentHomeImageslideBinding.bind(itemView);
        }
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItemList.addAll(sliderItemList);
            notifyDataSetChanged();
        }
    };
}
