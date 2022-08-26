package com.example.realmapi.Base.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.realmapi.Base.Category.CategoryFragment;
import com.example.realmapi.Interface.CLickDelete;
import com.example.realmapi.Interface.ClickAdd;
import com.example.realmapi.Interface.ClickUpdate;
import com.example.realmapi.Interface.OnLongClick;
import com.example.realmapi.Model.DataModel;
import com.example.realmapi.Model.SliderItem;
import com.example.realmapi.R;
import com.example.realmapi.ViewModel.ViewModel;
import com.example.realmapi.databinding.FragmentHomeBinding;
import com.ramotion.circlemenu.CircleMenuView;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class HomeFragment extends Fragment implements CLickDelete, ClickAdd, ClickUpdate, OnLongClick {


    private CourseAdapter courseadapter;
    private FragmentHomeBinding binding;
    private List<DataModel> dataModelList;
    private ViewModel viewModel;
    private Realm realm;
    private Handler sliderhandler = new Handler();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        realm = Realm.getDefaultInstance();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.rcvmain.setLayoutManager(linearLayoutManager);
        dataModelList = realm.where(DataModel.class).findAll();
        courseadapter = new CourseAdapter(dataModelList, this,this,this);
        binding.rcvmain.setAdapter(courseadapter);

        CategoryFragment categoryFragment = new CategoryFragment();


        binding.edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getDatalistObserver().observe(getViewLifecycleOwner(), dataModels -> {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
//                    ADD DATA INTO DATABASE REALM
                    realm.insertOrUpdate(dataModels);
                }
            });
            courseadapter.UpdateCourseAdapter(dataModelList);
        });
        viewModel.getdata();
        binding.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomsheetDialog bottomsheet = new BottomsheetDialog(HomeFragment.this);
                bottomsheet.show(getActivity().getSupportFragmentManager(), "ok");
            }
        });
        binding.circmenuhome.setEventListener(new CircleMenuView.EventListener(){
            @Override
            public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
                super.onMenuOpenAnimationStart(view);
            }

            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int buttonIndex) {
                super.onButtonClickAnimationStart(view, buttonIndex);
                switch (buttonIndex){
                    case 0:
                        Toast.makeText(getContext(),"Home",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(),"Notification",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(getContext(),"Buble",Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(getContext(),"Account",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        List<SliderItem> sliderItemList = new ArrayList<>();
        //sliderItemList.add(new SliderItem(R.drawable.slide1));
        //sliderItemList.add(new SliderItem(R.drawable.slide2));
        //sliderItemList.add(new SliderItem(R.drawable.slide3));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderItemList, binding.viewpagehome);
        binding.viewpagehome.setAdapter(sliderAdapter);
        binding.viewpagehome.setClipToPadding(false);
        binding.viewpagehome.setClipChildren(false);
        binding.viewpagehome.setOffscreenPageLimit(3);
        binding.viewpagehome.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer =  new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        binding.viewpagehome.setPageTransformer(compositePageTransformer);
        binding.viewpagehome.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderhandler.removeCallbacks(sliderRunable);
                sliderhandler.postDelayed(sliderRunable, 3000);
            }
        });

        return binding.getRoot();
    }
    private Runnable sliderRunable = new Runnable() {
        @Override
        public void run() {
            binding.viewpagehome.setCurrentItem(binding.viewpagehome.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderhandler.removeCallbacks(sliderRunable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderhandler.postDelayed(sliderRunable,3000);
    }

    private void filter(String text){
        List<DataModel> itemSearchList = new ArrayList<>();
        for(DataModel dataModel : dataModelList){
            if(dataModel.getTitle().toLowerCase().contains(text.toLowerCase())){
                itemSearchList.add(dataModel);
            }
        }
        courseadapter.filterlist(itemSearchList);
    }

    @Override
    public void onItemClick(List<DataModel> dataModelList) {
        courseadapter.UpdateCourseAdapter(dataModelList);
        Toast.makeText(getContext(),"Delete sucessfull", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClickAdd(List<DataModel> dataModelList) {
        courseadapter.UpdateCourseAdapter(dataModelList);
        Toast.makeText(getContext(),"Add sucessfull", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClickUpdate(List<DataModel> dataModelList) {
        courseadapter.UpdateCourseAdapter(dataModelList);
        Toast.makeText(getContext(),"Update sucessfull", Toast.LENGTH_LONG).show();
    }

    @Override
    public void itemLongClick(int postion) {
        BottomsheetUpdateDialog dialog = new BottomsheetUpdateDialog(HomeFragment.this);
        dialog.show(getActivity().getSupportFragmentManager(),"Show Bottom sucessfull");
    }
}