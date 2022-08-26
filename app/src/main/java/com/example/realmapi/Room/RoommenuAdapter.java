package com.example.realmapi.Room;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realmapi.Interface.ClickChooseMenuRoom;
import com.example.realmapi.Model.Room;
import com.example.realmapi.R;
import com.example.realmapi.databinding.ItemRoomMenuBinding;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class RoommenuAdapter extends RecyclerView.Adapter<RoommenuAdapter.Viewholder> {
    private List<String> listmenu;
    private List<Room> listroom;
    private List<Room> listroomfilter;
    private int SelectedItem;
    ClickChooseMenuRoom mclickChooseMenuRoom;
    Realm realm;

    public RoommenuAdapter(List<String> listmenu, ClickChooseMenuRoom clickChooseMenuRoom) {
        this.listmenu = listmenu;
        this.mclickChooseMenuRoom = clickChooseMenuRoom;
        SelectedItem = 0;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        realm = Realm.getDefaultInstance();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room_menu, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.tvnamemenu.setTextColor(Color.parseColor("#094270"));
        holder.binding.imvline.setVisibility(View.INVISIBLE);
        if(SelectedItem == position){
            holder.binding.tvnamemenu.setTextColor(Color.parseColor("#FF5722"));
            holder.binding.imvline.setVisibility(View.VISIBLE);
        }

        holder.binding.tvnamemenu.setText(listmenu.get(position).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int previous = SelectedItem;
                SelectedItem = position;
                notifyItemChanged(previous);
                notifyItemChanged(position);

                listroomfilter = new ArrayList<>();
                listroom = realm.where(Room.class).findAll();
                for(int i = 0; i< listroom.size(); i++){
                    if(listroom.get(i).getFloor().toString().equals(listmenu.get(position).toString())){
                        listroomfilter.add(listroom.get(i));
                    }
                    else if(listmenu.get(position).toString() == "Tất cả tầng"){
                        listroomfilter.add(listroom.get(i));
                    }
                }
                mclickChooseMenuRoom.Clickchoosemenuroom(listroomfilter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listmenu.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ItemRoomMenuBinding binding;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            binding = ItemRoomMenuBinding.bind(itemView);
        }
    }
}
