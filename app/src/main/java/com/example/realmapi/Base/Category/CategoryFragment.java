package com.example.realmapi.Base.Category;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.realmapi.Base.Home.BottomsheetDialog;
import com.example.realmapi.Interface.ClickFilter;
import com.example.realmapi.Interface.ClickMenu;
import com.example.realmapi.Model.Menu;
import com.example.realmapi.Model.Product;
import com.example.realmapi.Model.Singleton;
import com.example.realmapi.R;
import com.example.realmapi.databinding.BottomSheetFilterLayoutBinding;
import com.example.realmapi.databinding.FragmentCategoryBinding;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment implements ClickMenu, ClickFilter {
    private FragmentCategoryBinding binding;
    private List<Menu> listmenu;
    ProductAdapter productAdapter;
    private List<Product> listproduct;
    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater,container,false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.rcvMenu.setLayoutManager(linearLayoutManager);
        createlistmenu();

        MenuAdapter menuAdapter = new MenuAdapter(getContext(),listmenu,CategoryFragment.this);
        binding.rcvMenu.setAdapter(menuAdapter);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        binding.rcvproduct.setLayoutManager(gridLayoutManager);



        listproduct = Singleton.getListproduct();
        productAdapter = new ProductAdapter(listproduct);
        binding.rcvproduct.setAdapter(productAdapter);
        binding.imgbtfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomsheetFilter bottomsheetFilter = new BottomsheetFilter(CategoryFragment.this);
                bottomsheetFilter.show(getActivity().getSupportFragmentManager(),"OK");
            }
        });

        return binding.getRoot();
    }

    private void createlistmenu(){
        listmenu = new ArrayList<>();
        listmenu.add(new Menu(R.drawable.img_all,"All"));
        listmenu.add(new Menu(R.drawable.img_electric,"Electric"));
        listmenu.add(new Menu(R.drawable.img_water,"Water"));
        listmenu.add(new Menu(R.drawable.img_wifi,"Internet"));
        listmenu.add(new Menu(R.drawable.img_payment,"Payment"));
    }


    @Override
    public void Clickchosemenu(List<Product> listproduct) {
        productAdapter.UpdateCourseAdapter(listproduct);
    }

    @Override
    public void Clickfilter(List<Product> productList) {
        productAdapter.UpdateCourseAdapter(productList);
    }
}