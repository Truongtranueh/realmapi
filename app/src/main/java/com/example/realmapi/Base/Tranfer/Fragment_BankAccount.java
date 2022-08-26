package com.example.realmapi.Base.Tranfer;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.realmapi.Interface.ClickShowDialogRoom;
import com.example.realmapi.Interface.ClickShowPopupRoom;
import com.example.realmapi.Interface.Clickgetpositiondialog;
import com.example.realmapi.Interface.UpdatePicture;
import com.example.realmapi.Model.Room;
import com.example.realmapi.Model.Singleton;
import com.example.realmapi.R;
import com.example.realmapi.databinding.FragmentBankAccountBinding;
import com.example.realmapi.databinding.FragmentTranferDialogBinding;

import java.util.ArrayList;
import java.util.List;

public class Fragment_BankAccount extends Fragment{
//    private List<Room> listroom;
//    private BankAccountAdapter adapter;
    private FragmentBankAccountBinding binding;
//    int positonex = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBankAccountBinding.inflate(inflater,container,false);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
//        binding.rcvroom.setLayoutManager(gridLayoutManager);
//
//
//        adapter = new BankAccountAdapter(Singleton.getListroom(),this,this);
//        binding.rcvroom.setAdapter(adapter);
//        Bundle bundle = this.getArguments();
//        if(bundle != null || positonex != 1){
//            String img = bundle.getString("img");
//            Singleton.getListroom().get(positonex).setImg(img);
//            adapter.UpdateBankAccountAdapter(Singleton.getListroom());
//        }
        return binding.getRoot();
    }

//    @Override
//    public void Showdialogroom(int position) {
//        BankAccountBottomsheet bankAccountBottomsheet = new BankAccountBottomsheet();
//        bankAccountBottomsheet.show(getActivity().getSupportFragmentManager(),"Show sucessfull");
//    }
//
//    @Override
//    public void showPopup(int position) {
//        BankAccountDialog bankAccountDialog = new BankAccountDialog(this);
//        bankAccountDialog.setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
//        bankAccountDialog.show(getActivity().getSupportFragmentManager(),"OK");
//        positonex = position;
//    }
//
//    @Override
//    public void getpositon(String s) {
//        for(int i = 0; i< Singleton.getListroom().size(); i++){
//            Singleton.getListroom().get(positonex).setName(s);
//        }
//        adapter.UpdateBankAccountAdapter(Singleton.getListroom());
//    }
}