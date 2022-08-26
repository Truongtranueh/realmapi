package com.example.realmapi.Base.Category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realmapi.Interface.ClickMenu;
import com.example.realmapi.Model.Menu;
import com.example.realmapi.Model.Product;
import com.example.realmapi.Model.Singleton;
import com.example.realmapi.R;
import com.example.realmapi.databinding.MenuLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter <MenuAdapter.ViewHolder>{
    private List<Menu> listmenu;
    private int selectedItem;
    private Context context;
    private ClickMenu mclickmenu;
    private List<Product> productListfilter;

    public MenuAdapter(Context context, List<Menu> listmenu, ClickMenu clickmenu) {
        this.listmenu = listmenu;
        this.mclickmenu = clickmenu;
        selectedItem = 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_layout, parent, false);
        return new ViewHolder(view);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.tvmenu.setText(listmenu.get(position).getName().toString());
        holder.binding.circleimgmenu.setImageResource(listmenu.get(position).getImg());

        holder.binding.tvmenu.setTextColor(Color.rgb(0,0,0));
        if(selectedItem == position){
            holder.binding.tvmenu.setTextColor(Color.rgb(255,140,0));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int previousItem = selectedItem;
                selectedItem = position;
                notifyItemChanged(previousItem);
                notifyItemChanged(position);

                productListfilter = new ArrayList<>();
                for(int i = 0; i< Singleton.getListproduct().size(); i++){
                    if(listmenu.get(position).getName().toString() == Singleton.getListproduct().get(i).getStyle()){
                        productListfilter.add(Singleton.getListproduct().get(i));
                    }
                    else if(listmenu.get(position).getName().toString() == "All"){
                        productListfilter.add(Singleton.getListproduct().get(i));
                    }
                }
                mclickmenu.Clickchosemenu(productListfilter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listmenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MenuLayoutBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MenuLayoutBinding.bind(itemView);
        }
    }
}
