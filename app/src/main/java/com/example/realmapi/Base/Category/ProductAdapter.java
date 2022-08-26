package com.example.realmapi.Base.Category;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realmapi.Model.Product;
import com.example.realmapi.R;
import com.example.realmapi.databinding.CategoryLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> listproduct;

    public void UpdateCourseAdapter(List<Product> productList) {
        this.listproduct = productList;
        notifyDataSetChanged();
    }

    public ProductAdapter(List<Product> listproduct) {
        this.listproduct = listproduct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.tvNameproduct.setText(listproduct.get(position).getName());
        Picasso.get().load(listproduct.get(position).getImg()).into(holder.binding.imgProduct);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listproduct.get(position).getStyle().toString().equals("Electric")){
                    Intent i = new Intent(v.getContext(),PaymentBillActivity.class);
                    v.getContext().startActivity(i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listproduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CategoryLayoutBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CategoryLayoutBinding.bind(itemView);

        }
    }
}
