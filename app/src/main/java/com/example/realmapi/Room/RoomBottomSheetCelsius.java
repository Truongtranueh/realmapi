package com.example.realmapi.Room;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.realmapi.Base.Home.BottomsheetDialog;
import com.example.realmapi.Model.Room;
import com.example.realmapi.R;
import com.example.realmapi.databinding.BottomsheetRoomCelsiusBinding;

import java.util.List;

import io.realm.Realm;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class RoomBottomSheetCelsius extends BottomsheetDialog {

    private Realm realm;
    private List<Room> listroom;
    int id;

    public RoomBottomSheetCelsius(int id) {
        this.id = id;
    }

    private BottomsheetRoomCelsiusBinding binding;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"Range", "ClickableViewAccessibility"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomsheetRoomCelsiusBinding.inflate(inflater,container,false);


        realm = Realm.getDefaultInstance();
        listroom = realm.where(Room.class).findAll();

        for(int i = 0 ;i< listroom.size(); i++){
            if(listroom.get(i).getId() == id){
                binding.txtcelsius.setText(String.valueOf(listroom.get(i).getCelsius())+ "℃");
                int mt = listroom.get(i).getCelsius();
                float kt = (float) (mt - 16) /16;
                binding.gaugeSeekBar.setProgress(kt);
                binding.tvcelsiusmini.setText(String.valueOf(listroom.get(i).getCelsius())+ "℃");
            }
        }
        binding.gaugeSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String s = String.valueOf((binding.gaugeSeekBar.getProgress() * 16) +16);
                binding.txtcelsius.setText(s + "℃");
                Log.e("vVVV",String.valueOf(binding.gaugeSeekBar.getProgress()));
                return false;
            }
        });

        binding.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = binding.txtcelsius.getText().toString();
                int n = Integer.parseInt(s.substring(0,2));
                int k = n + 1;
                if(k > 32){
                    k = 32;
                }
                float p = (float) (k - 16) /16;
                binding.txtcelsius.setText(String.valueOf(k+ "℃"));
                binding.gaugeSeekBar.setProgress(p);
            }
        });
        binding.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = binding.txtcelsius.getText().toString();
                int m = Integer.parseInt(t.substring(0,2));
                int l = m - 1;
                if(l < 16){
                    l = 16;
                }
                float u = (float) (l -16)/16;
                binding.txtcelsius.setText(String.valueOf(l+ "℃"));
                binding.gaugeSeekBar.setProgress(u);
            }
        });

        return binding.getRoot();
    }
}
