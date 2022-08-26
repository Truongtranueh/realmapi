package com.example.realmapi.Base.Tranfer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.realmapi.R;
import com.example.realmapi.databinding.FragmentTranferBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TranferFragment extends Fragment {
    private FragmentTranferBinding binding;
//    private MenuTranferAdapter menuTranferAdapter;
    private List<String> listmenu;
    TranferFragmentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTranferBinding.inflate(inflater,container,false);

//        createlistmenu();
//        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
//        binding.rcvmenutranfer.setLayoutManager(linearLayout);
//        MenuTranferAdapter menuTranferAdapter = new MenuTranferAdapter(listmenu);
//        binding.rcvmenutranfer.setAdapter(menuTranferAdapter);



        adapter = new TranferFragmentAdapter(getActivity().getSupportFragmentManager(),getLifecycle());
        binding.viewpage2.setAdapter(adapter);
        binding.tablayouttranfer.addTab(binding.tablayouttranfer.newTab().setText("Digital wallet"));
        binding.tablayouttranfer.addTab(binding.tablayouttranfer.newTab().setText("Bank Account"));
        binding.tablayouttranfer.addTab(binding.tablayouttranfer.newTab().setText("Others"));
        binding.tablayouttranfer.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewpage2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.viewpage2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tablayouttranfer.selectTab(binding.tablayouttranfer.getTabAt(position));
            }
        });

        return binding.getRoot();
    }

    private void createlistmenu() {
        listmenu = new ArrayList<>();
        listmenu.add("Digital wallet");
        listmenu.add("Bank Account");
        listmenu.add("Others");
    }
}