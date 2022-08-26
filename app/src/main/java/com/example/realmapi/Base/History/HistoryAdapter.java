package com.example.realmapi.Base.History;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realmapi.Base.Category.MenuAdapter;
import com.example.realmapi.Model.History;
import com.example.realmapi.R;
import com.example.realmapi.databinding.ActivityPaymentBillItemBinding;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Viewholder> {
    private List<History> listhistory;

    public HistoryAdapter(List<History> listhistory) {
        this.listhistory = listhistory;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_payment_bill_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        switch (listhistory.get(position).getType().toString()){
            case "Receive":
                holder.binding.imghistory.setImageResource(R.drawable.img_receivemoney);
                holder.binding.moneyhistory.setText("+ " + String.valueOf(listhistory.get(position).getMoney() +" VNĐ"));
                break;
            case "Send":
                holder.binding.imghistory.setImageResource(R.drawable.img_sendmoney);
                holder.binding.moneyhistory.setText("- " + String.valueOf(listhistory.get(position).getMoney() +" VNĐ"));
                break;
            default:
                Log.e("NOPPP","NOPPPP");
        }
        holder.binding.datehistory.setText(String.valueOf(listhistory.get(position).getDate().toString()));
        holder.binding.titlehistory.setText(listhistory.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return listhistory.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ActivityPaymentBillItemBinding binding;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            binding = ActivityPaymentBillItemBinding.bind(itemView);
        }
    }
}
