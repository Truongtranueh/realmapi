package com.example.realmapi.Base.Tranfer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.realmapi.Model.DigitalWallet;
import com.example.realmapi.R;
import com.example.realmapi.databinding.FragmentDigitalWalletBinding;

import java.util.ArrayList;
import java.util.List;

public class Fragment_DigitalWallet extends Fragment {
    private List<DigitalWallet> listwallet;
    private DigitalwalletAdapter adapter;
    private FragmentDigitalWalletBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDigitalWalletBinding.inflate(inflater,container,false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        binding.rcvdigitalwallet.setLayoutManager(gridLayoutManager);
        createlistwallet();
        adapter = new DigitalwalletAdapter(listwallet);
        binding.rcvdigitalwallet.setAdapter(adapter);


        return  binding.getRoot();
    }

    private void createlistwallet() {
        listwallet = new ArrayList<>();
        listwallet.add(new DigitalWallet("Momo",R.drawable.digitalwallet_momo));
        listwallet.add(new DigitalWallet("Moca",R.drawable.digitalwallet_moca));
        listwallet.add(new DigitalWallet("Vnpay",R.drawable.digitalwallet_vnpay));
        listwallet.add(new DigitalWallet("Wepay",R.drawable.digitalwallet_wepay));
        listwallet.add(new DigitalWallet("ViettelPay",R.drawable.digitalwallet_viettelpay));
        listwallet.add(new DigitalWallet("Payoo",R.drawable.digitalwallet_payoo));
        listwallet.add(new DigitalWallet("Vimo",R.drawable.digitalwallet_vimo));
        listwallet.add(new DigitalWallet("VTCpay",R.drawable.digitalwallet_vtcpay));
        listwallet.add(new DigitalWallet("Airpay",R.drawable.digitalwallet_airpay));
    }
}