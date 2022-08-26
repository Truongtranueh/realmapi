package com.example.realmapi.Base.Tranfer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realmapi.Base.History.HistoryAdapter;
import com.example.realmapi.Model.DigitalWallet;
import com.example.realmapi.R;
import com.example.realmapi.databinding.FragmentDigitalWalletItemBinding;

import java.util.List;

public class DigitalwalletAdapter extends RecyclerView.Adapter<DigitalwalletAdapter.Viewmodel> {
    private List<DigitalWallet> listwallet;

    public DigitalwalletAdapter(List<DigitalWallet> listwallet) {
        this.listwallet = listwallet;
    }

    @NonNull
    @Override
    public Viewmodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_digital_wallet_item,parent,false);

        return new Viewmodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewmodel holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.img.setImageResource(listwallet.get(position).getImg());
        holder.binding.name.setText(listwallet.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),fragment_digital_wallet_activity.class);
                i.putExtra("name",listwallet.get(position).getName());
                i.putExtra("img", listwallet.get(position).getImg());
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listwallet.size();
    }

    public class Viewmodel extends RecyclerView.ViewHolder {
        private FragmentDigitalWalletItemBinding binding;
        public Viewmodel(@NonNull View itemView) {
            super(itemView);
            binding = FragmentDigitalWalletItemBinding.bind(itemView);
        }
    }
}
