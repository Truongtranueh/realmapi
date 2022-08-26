package com.example.realmapi.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.realmapi.Interface.ClickDialogHumidity;
import com.example.realmapi.Interface.ClickShowBottomsheetCelsius;
import com.example.realmapi.Interface.ClickShowDialogRoom;
import com.example.realmapi.Interface.ClickShowPopupRoom;
import com.example.realmapi.Model.Room;
import com.example.realmapi.R;
import com.example.realmapi.databinding.BottomsheetRoomCelsiusBinding;
import com.example.realmapi.databinding.ItemRoomBinding;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private List<Room> listroom;
    private ClickShowDialogRoom mclickdialogroom;
    private ClickShowPopupRoom mclickshowpopup;
    private ClickShowBottomsheetCelsius mclickShowBottomsheetCelsius;
    private Uri uri;
    public  Context mcontext;

    public RoomAdapter(List<Room> listroom, ClickShowDialogRoom clickdialogroom, ClickShowPopupRoom clickshowpopup, ClickShowBottomsheetCelsius clickShowBottomsheetCelsius) {
        this.listroom = listroom;
        this.mclickdialogroom = clickdialogroom;
        this.mclickshowpopup = clickshowpopup;
        this.mclickShowBottomsheetCelsius = clickShowBottomsheetCelsius;
    }
    public void UpdateRoomAdapter(List<Room> listroom){
        this.listroom = listroom;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        mcontext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.nameroom.setText(listroom.get(position).getName());
        holder.binding.humidity.setText(listroom.get(position).getHumidity()+ " %");
        holder.binding.celsius.setText(listroom.get(position).getCelsius()+ " °C");


        uri = Uri.parse(listroom.get(position).getImg());
        Glide.with(mcontext)
                .asBitmap().
                load(uri).
                into(holder.binding.imgroom);


        holder.binding.idcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mclickdialogroom.Showdialogroom(listroom.get(position).getId());
            }
        });
        holder.binding.btnEditname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mclickshowpopup.showPopup(listroom.get(position).getId());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mclickShowBottomsheetCelsius.Clickshowbottomsheetcelsius(listroom.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listroom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemRoomBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemRoomBinding.bind(itemView);
        }
    }
}
