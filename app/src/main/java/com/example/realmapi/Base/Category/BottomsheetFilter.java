package com.example.realmapi.Base.Category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.realmapi.Interface.ClickFilter;
import com.example.realmapi.Model.Product;
import com.example.realmapi.Model.Singleton;
import com.example.realmapi.databinding.BottomSheetFilterLayoutBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomsheetFilter extends BottomSheetDialogFragment {

    private BottomSheetFilterLayoutBinding binding;
    private List<Product> listfilter;
    private ClickFilter mclickfilter;

    public BottomsheetFilter(ClickFilter clickfilter) {
        mclickfilter = clickfilter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetFilterLayoutBinding.inflate(inflater,container,false);


        binding.seekbarfilter.setMax(500000);

        binding.seekbarfilter.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(progressChangedValue != 0){
                    binding.tvfilterprice.setText(String.valueOf(progressChangedValue));
                }
            }
        });
        return binding.getRoot();
    }
}
